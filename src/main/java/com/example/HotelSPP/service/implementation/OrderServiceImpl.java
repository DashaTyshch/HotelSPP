package com.example.HotelSPP.service.implementation;


import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;
import com.example.HotelSPP.repository.interfaces.OrderRepository;
import com.example.HotelSPP.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Order addOrder(OrderRequest order) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return null;
    }

    @Override
    public List<Order> getOrdersForUser(long guest_id) {
        return null;
    }
}
