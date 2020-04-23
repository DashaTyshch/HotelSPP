package com.example.HotelSPP.controller.interfaces;


import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/order")
public interface OrderController {
    @GetMapping("/get")
    ResponseEntity<OrderResponse> getOrder(@RequestParam long id);

    @GetMapping("/all")
    ResponseEntity<List<OrderResponse>> getAllOrders();

    @PostMapping("/create")
    ResponseEntity<String> postOrder(@RequestBody List<OrderRequest> Order);

    @GetMapping("/by_user")
    ResponseEntity<List<Order>> getOrdersForUser(@RequestParam long guest_id);
}
