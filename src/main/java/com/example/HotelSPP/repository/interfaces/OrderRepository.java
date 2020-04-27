package com.example.HotelSPP.repository.interfaces;

import com.example.HotelSPP.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    boolean ordersAvailable(long id);
    Order addOrder(Order order);
    Optional<Order> findOrderById(long id);
    Optional<Order> findOrderByState(String state);
    List<Order> findOrdersByDate(Date date);
    Optional<Order> updateOrder(Order o);

    List<Order> getAllOrders();

}
