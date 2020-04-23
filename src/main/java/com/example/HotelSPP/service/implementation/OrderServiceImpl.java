package com.example.HotelSPP.service.implementation;


import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;
import com.example.HotelSPP.repository.interfaces.OrderRepository;
import com.example.HotelSPP.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse getOrder(long id) {
        Order order = orderRepository.findOrderById(id).orElse(null);
        if(order == null)
            return null;
        OrderResponse result =  OrderResponse.builder()
                .date_created(order.getDate_created())
                .guest_id(order.getGuest_Id())
                .state_id(order.getState_Id())
                .build();
        return result;
    }

    @Override
    public Order addOrder(OrderRequest order) {
        if(orderRepository.ordersAvailable(order.getId())){
            try{
                Order result =  orderRepository.addOrder(order.builder()
                        .date_created(order.getDate_created())
                        .guest_id(order.getGuest_Id())
                        .state_id(order.getState_Id())
                        .build());
                return result;
            } catch (DuplicateKeyException e) {
                return null;
            }
        } else
            return null;    }

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
                    .guest_id(order.getGuest_Id())
                    .state_id(order.getState_Id())
                    .build());
        }
        return result;
    }

    @Override
    public List<Order> getOrdersForUser(long guest_id) {
        List<Order> all_orders = orderRepository.getAllOrders();
        for (int i=0; i<all_orders.size(); i++) {
            if(all_orders.get(i).getGuest_Id != guest_id){
                all_orders.remove(i);
            }
        }
        return all_orders;
    }
}
