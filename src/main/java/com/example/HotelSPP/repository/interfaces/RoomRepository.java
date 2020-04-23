package com.example.HotelSPP.repository.interfaces;

import com.example.HotelSPP.entity.Image;
import com.example.HotelSPP.entity.RoomType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    boolean roomTypeAvailable(String name);
    RoomType addRoomType(RoomType roomType);
    void addImage(int id, String image);

    Optional<RoomType> findRoomTypeById(long id);
    Optional<RoomType> findRoomTypeByName(String name);
    Optional<RoomType> findRoomTypesByPrice(float price);
    Optional<RoomType> findRoomTypesByPlaces(int places);
    Optional<RoomType> updateRoomType(RoomType rt);

    List<RoomType> getAllRoomTypes();

    List<Image> findImagesByIds(List<Integer> ids);
}
