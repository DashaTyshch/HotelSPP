package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.entity.request.OrderRequest;
import com.example.HotelSPP.entity.response.OrderResponse;


import java.util.List;

public interface OrderService {
    OrderResponse getOrder(long id);
    Order addOrder (OrderRequest order);
    List<OrderResponse> getAllOrders ();
    List<Order> getOrdersForUser(long guest_id);
}
