package com.example.HotelSPP.controller.implementation;


import com.example.HotelSPP.controller.interfaces.OrderController;
import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;
import com.example.HotelSPP.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderControllerImpl implements OrderController {
    @Autowired
    private OrderService service;

    @Override
    public ResponseEntity<OrderResponse> getOrder(long id) {

        return null;
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return null;
    }

    @Override
    public ResponseEntity<String> postOrder(OrderRequest Order) {
        return null;
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersForUser(long guest_id) {
        return null;
    }
}
