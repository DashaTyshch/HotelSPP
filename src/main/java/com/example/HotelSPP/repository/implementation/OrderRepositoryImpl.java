package com.example.HotelSPP.repository.implementation;

import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.repository.interfaces.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@PropertySource("classpath:/config/order.properties")
public class OrderRepositoryImpl implements OrderRepository {

    @Value("${orders.id}")
    private String paramOrdersId;
    @Value("${orders.date_created}")
    private String paramOrdersDateCreated;
    @Value("${orders.guest_id}")
    private String paramOrdersGuestId;
    @Value("${orders.state}")
    private String paramOrdersState;

    @Value("${sql.insert.orders}")
    private String ADD_ORDER;

    @Value("${sql.select.ordersById}")
    private String GET_ORDER_BY_ID;

    @Value("${sql.select.ordersByState}")
    private String GET_ORDER_BY_STATE;

    @Value("${sql.select.ordersByDate}")
    private String GET_ORDER_BY_DATE;

    @Value("${sql.select.orders}")
    private String GET_ALL_ORDERS;

    @Value("${order_state.id}")
    private String paramOrderStateId;
    @Value("${order_state.name}")
    private String paramOrderStateName;

    @Value("${sql.insert.order_state}")
    private String ADD_ORDER_STATE;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;


    @Override
    public boolean ordersAvailable(long id) {
        return false;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findOrderById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findOrderByState(String state) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findOrdersByDate(Date date) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> updateOrder(Order o) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }
}
