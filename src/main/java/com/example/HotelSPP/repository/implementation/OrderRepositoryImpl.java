package com.example.HotelSPP.repository.implementation;

import com.example.HotelSPP.entity.Booking;
import com.example.HotelSPP.entity.Order;
import com.example.HotelSPP.exceptions.ResourceNotFoundException;
import com.example.HotelSPP.repository.interfaces.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.*;
import java.util.Date;

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
        return !findOrderById(id).isPresent();
    }

    @Override
    public Order addOrder(Order order) {
        int id;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(paramOrdersDateCreated, order.getDate_created());
        paramMap.put(paramOrdersGuestId, order.getGuest_Id());
        paramMap.put(paramOrdersState, order.getState_Id());
        //namedTemplate.update(ADD_ORDER, paramMap);

        try {
            id = namedTemplate.queryForObject(ADD_ORDER, paramMap,  (resultSet, i) -> resultSet.getInt("id"));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }

        Optional<Order> createdOrder = findOrderById(id);
        return createdOrder.orElseThrow(
                () -> new ResourceNotFoundException("Orders", "id", id));

    }

    @Override
    public Optional<Order> findOrderById(long id) {
        Order res;
        try {
            res = namedTemplate.queryForObject(GET_ORDER_BY_ID, new MapSqlParameterSource(
                    paramOrdersId, id), new OrderMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find order by id: %s", id));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<Order> findOrderByState(String state) {
        Order res;
        try {
            res = namedTemplate.queryForObject(GET_ORDER_BY_STATE, new MapSqlParameterSource(
                    paramOrdersState, state), new OrderMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find order by state: %s", state));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public List<Order> findOrdersByDate(Date date) {
        try {
            return Collections.unmodifiableList(namedTemplate.query(GET_ORDER_BY_DATE, new MapSqlParameterSource(
                    paramOrdersDateCreated, date), new OrderMapper()));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    public Optional<Order> updateOrder(Order o) {
        Order current;
        String updateSql = "UPDATE orders SET ";
        Optional<Order> db_res;
        try {
            db_res = findOrderById(o.getId());
            if (db_res.isPresent()) {

                ArrayList<Object> params = new ArrayList<>();
                ArrayList<Integer> types = new ArrayList<>();
                current = db_res.get();
                if (!o.getDate_created().equals(current.getDate_created())){
                    updateSql += " date_created= " + o.getDate_created();
                    params.add(o.getDate_created());
                    types.add(Types.DATE);
                }
                if (o.getGuest_Id() != (current.getGuest_Id())) {
                    updateSql += " guest_id= " + o.getGuest_Id() + ", ";
                    params.add(o.getGuest_Id());
                    types.add(Types.INTEGER);
                }
                if (o.getState_Id() != (current.getState_Id())) {
                    updateSql += " state_id= " + o.getState_Id() + ", ";
                    params.add(o.getState_Id());
                    types.add(Types.INTEGER);
                }
                updateSql += " WHERE id =" + current.getId();

                int rows = namedTemplate.update(updateSql,(SqlParameterSource) params,(KeyHolder) types);
                System.out.println(rows + " row(s) updated.");
                db_res = findOrderById(o.getId());
                return db_res;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            return Collections.unmodifiableList(namedTemplate.query(GET_ALL_ORDERS, new OrderMapper()));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
    }
    private class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int arg1) throws SQLException {
            return Order.builder()
                    .id(rs.getInt(paramOrdersId))
                    .date_created(rs.getDate(paramOrdersDateCreated))
                    .guest_Id(rs.getLong(paramOrdersGuestId))
                    .state_Id(rs.getInt(paramOrderStateId))
                    .build();
        }
    }
}
