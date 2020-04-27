package com.example.HotelSPP.repository.implementation;

import com.example.HotelSPP.entity.Booking;
import com.example.HotelSPP.repository.interfaces.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Repository
@PropertySource("classpath:/config/booking.properties")
public class BookingRepositoryImpl implements BookingRepository {

    @Value("${bookings.id}")
    private String paramBookingsId;
    @Value("${bookings.start_date}")
    private String paramBookingsStartDate;
    @Value("${bookings.end_date}")
    private String paramBookingsEndDate;
    @Value("${bookings.price}")
    private String paramBookingsPrice;
    @Value("${bookings.period_price}")
    private String paramBookingsPeriodPrice;
    @Value("${bookings.is_canceled}")
    private String paramBookingsIsCanceled;
    @Value("${bookings.is_edited}")
    private String paramBookingsIsEdited;
    @Value("${bookings.comment}")
    private String paramBookingsComment;
    @Value("${bookings.old_booking_id}")
    private String paramBookingsOldBookingId;
    @Value("${bookings.room_type_id}")
    private String paramBookingsRoomTypeId;
    @Value("${bookings.order_id}")
    private String paramBookingsOrderId;

    @Value("${const.booking.orders_ids}")
    private String paramBookingOrdersIds;

    @Value("${sql.insert.bookings}")
    private String ADD_BOOKING;

    @Value("${sql.select.bookingsById}")
    private String GET_BOOKING_BY_ID;

    @Value("${sql.select.bookingByOldBookingId}")
    private String GET_BOOKING_BY_OLD_BOOKING_ID;

    @Value("${sql.select.bookingByRoomTypeId}")
    private String GET_BOOKING_BY_ROOM_TYPE_ID;

    @Value("${sql.select.bookingByOrderId}")
    private String GET_BOOKING_BY_ORDER_ID;

    @Value("${sql.select.bookings}")
    private String GET_ALL_BOOKINGS;

    @Value("${sql.select.amount_of_booked_for_room_type_on_date_span}")
    private String GET_AMOUNT_OF_BOOKED_ON_DATE_SPAN;

    @Value("${sql.select.bookingsByOrdersId}")
    private String GET_BOOKINGS_BY_ORDERS_IDS;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;


    @Override
    public boolean bookingsAvailable(String name) {
        return false;
    }

    @Override
    public void addBooking(Booking booking) {
        int id;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(paramBookingsStartDate, booking.getStart_date());
        paramMap.put(paramBookingsEndDate, booking.getEnd_date());
        paramMap.put(paramBookingsPrice, booking.getPrice());
        paramMap.put(paramBookingsPeriodPrice, booking.getPeriod_price());
        paramMap.put(paramBookingsIsCanceled, booking.isIs_canceled());
        paramMap.put(paramBookingsIsEdited, booking.isIs_edited());
        paramMap.put(paramBookingsComment, booking.getComment());
        paramMap.put(paramBookingsOldBookingId, booking.getOld_booking_id());
        paramMap.put(paramBookingsRoomTypeId, booking.getRoom_type_id());
        paramMap.put(paramBookingsOrderId, booking.getOrder_id());
        //namedTemplate.update(ADD_BOOKING, paramMap);
        try {
            id = namedTemplate.queryForObject(ADD_BOOKING, paramMap,  (resultSet, i) -> resultSet.getInt("id"));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
//        Optional<Booking> createdBooking = findBookingById(id);
//        return createdBooking.orElseThrow(
//                () -> new ResourceNotFoundException("Bookings", "id", id));

    }

    @Override
    public Optional<Booking> findBookingById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Booking> findBookingByOldBookingId(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Booking> findBookingsByRoomTypeId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Booking> findBookingsByOrdersIds(List<Integer> ids) {
        try {
            return Collections.unmodifiableList(namedTemplate.query(GET_BOOKINGS_BY_ORDERS_IDS,
                    new MapSqlParameterSource(paramBookingOrdersIds, ids), new BookingMapper()));
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    public Optional<Booking> updateBooking(Booking b) {
        return Optional.empty();
    }

    @Override
    public List<Booking> getAllBookings() {
        return null;
    }

    //amount of booked for a specific room type on a start-end date span
    @Override
    public int amountOfBooked(Date start, Date end, int room_type_id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(paramBookingsRoomTypeId, room_type_id);
        paramMap.put(paramBookingsStartDate, "'" + new SimpleDateFormat("yyyy-MM-dd").format(start) + "'");
        paramMap.put(paramBookingsEndDate, "'" + new SimpleDateFormat("yyyy-MM-dd").format(end) + "'");

        try{
            return namedTemplate.queryForObject(GET_AMOUNT_OF_BOOKED_ON_DATE_SPAN,
                    paramMap, (resultSet, i) -> resultSet.getInt("count"));
        }catch(DataAccessException e){
            log.error("Error: ", e);
            throw e;
        }
    }

    private class BookingMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet rs, int arg1) throws SQLException {
            return Booking.builder()
                    .id(rs.getInt(paramBookingsId))
                    .start_date(rs.getDate(paramBookingsStartDate))
                    .end_date(rs.getDate(paramBookingsEndDate))
                    .price(rs.getFloat(paramBookingsPrice))
                    .period_price(rs.getFloat(paramBookingsPeriodPrice))
                    .is_canceled(rs.getBoolean(paramBookingsIsCanceled))
                    .is_edited(rs.getBoolean(paramBookingsIsEdited))
                    .comment(rs.getString(paramBookingsComment))
                    .old_booking_id(rs.getLong(paramBookingsOldBookingId))
                    .room_type_id(rs.getInt(paramBookingsRoomTypeId))
                    .order_id(rs.getLong(paramBookingsOrderId))
                    .build();
        }
    }
}
