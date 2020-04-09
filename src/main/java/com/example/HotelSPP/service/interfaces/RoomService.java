package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.Image;
import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.RoomTypeRequest;

import java.util.List;

public interface RoomService {
    RoomType getRoomType(String name);
    int updateRoomType(RoomType rt);
    RoomType addRoomType (RoomTypeRequest roomType);
    Boolean addImages (int id, List<Image> images);
    List<RoomType> getAllRoomTypes ();
}
