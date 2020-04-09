package com.example.HotelSPP.repository.interfaces;

import com.example.HotelSPP.entity.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    boolean roomTypeAvailable(String name);
    RoomType addRoomType(RoomType roomType);

    Optional<RoomType> findRoomTypeById(long id);
    Optional<RoomType> findRoomTypeByName(String name);
    Optional<RoomType> findRoomTypesByPrice(float price);
    Optional<RoomType> findRoomTypesByPlaces(int places);
    Optional<RoomType> updateRoomType(RoomType rt);

    List<RoomType> getAllRoomTypes();
}
