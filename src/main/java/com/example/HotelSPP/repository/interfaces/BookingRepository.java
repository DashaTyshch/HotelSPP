package com.example.HotelSPP.repository.interfaces;
import com.example.HotelSPP.entity.Booking;
import com.example.HotelSPP.entity.Image;

import java.util.Date;
import java.util.Optional;
import java.util.List;

public interface BookingRepository {
    boolean bookingsAvailable(String name);
    void addBooking(Booking booking);

    Optional<Booking> findBookingById(long id);
    Optional<Booking> findBookingByOldBookingId(long id);
    Optional<Booking> findBookingsByRoomTypeId(int id);
    List<Booking> findBookingsByOrdersIds(List<Integer> ids);
    Optional<Booking> updateBooking(Booking b);

    List<Booking> getAllBookings();

    int amountOfBooked(Date start, Date end, int room_type_id);
}
