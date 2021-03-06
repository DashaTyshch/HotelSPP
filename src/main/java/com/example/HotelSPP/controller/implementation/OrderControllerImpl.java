package com.example.HotelSPP.controller.implementation;


import com.example.HotelSPP.controller.interfaces.OrderController;
import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;
import com.example.HotelSPP.security.UserPrincipal;
import com.example.HotelSPP.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderControllerImpl implements OrderController {
    @Autowired
    private OrderService service;

    @Override
    public ResponseEntity<OrderResponse> getOrder(long id) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @Override
    public ResponseEntity<String> postOrder(List<OrderRequest> bookings) {
        Order o = service.addOrder(bookings);
        if (o==null)
            return new ResponseEntity<>("Таке замовлення вже існує", HttpStatus.CONFLICT);
        return ResponseEntity.ok(Long.toString(o.getId()));
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersForUser() {
        User user = getCurrentUser();
        return ResponseEntity.ok(service.getOrdersForUser(user.getId()));
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getOrdersByDate(Date date) {
        return ResponseEntity.ok(service.getOrdersByDate(date));
    }

    private User getCurrentUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
