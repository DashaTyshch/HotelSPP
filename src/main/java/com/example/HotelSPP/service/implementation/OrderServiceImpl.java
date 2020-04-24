package com.example.HotelSPP.service.implementation;


import com.example.HotelSPP.entity.Booking;
import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;
import com.example.HotelSPP.repository.interfaces.BookingRepository;
import com.example.HotelSPP.repository.interfaces.OrderRepository;
import com.example.HotelSPP.security.UserPrincipal;
import com.example.HotelSPP.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private BookingRepository bookingRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, BookingRepository bookingRepository){
        this.orderRepository = orderRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public OrderResponse getOrder(long id) {
        Order order = orderRepository.findOrderById(id).orElse(null);
        if(order == null)
            return null;
        OrderResponse result =  OrderResponse.builder()
                .date_created(order.getDate_created())
                .guest_Id(order.getGuest_Id())
                .state_Id(order.getState_Id())
                .build();
        return result;
    }

    @Override
    public Order addOrder(List<OrderRequest> bookings) {
        try {
            Order order = orderRepository.addOrder(Order.builder()
                    .date_created(new Date())
                    .guest_Id(getCurrentUser().getId())
                    .state_Id(1)
                    .build());

            for (OrderRequest or: bookings){
                bookingRepository.addBooking(Booking.builder()
                .start_date(or.getStart_date())
                .end_date(or.getEnd_date())
                .price(or.getPrice())
                .period_price(or.getPeriod_price())
                .is_canceled(false)
                .is_edited(false)
                .comment(or.getComment())
                .old_booking_id(null)
                .room_type_id(or.getRoom_type_id())
                .order_id(order.getId())
                .build());
            }
            return order;
        } catch (DuplicateKeyException e) {
            return null;
        }
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> Orders = orderRepository.getAllOrders();
        if(Orders.isEmpty())
            return null;

        List<OrderResponse> result = new ArrayList<>();
        for (Order order :
                Orders) {
            result.add(OrderResponse.builder()
                    .date_created(order.getDate_created())
                    .guest_Id(order.getGuest_Id())
                    .state_Id(order.getState_Id())
                    .build());
        }
        return result;
    }

    @Override
    public List<Order> getOrdersForUser(long guest_Id) {
        List<Order> all_orders = orderRepository.getAllOrders();
        for (int i=0; i<all_orders.size(); i++) {
            if(all_orders.get(i).getGuest_Id() != guest_Id){
                all_orders.remove(i);
            }
        }
        return all_orders;
    }
    private User getCurrentUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
