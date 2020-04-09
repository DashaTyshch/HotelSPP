package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.RoomTypeRequest;

import java.util.List;

public interface RoomService {
    RoomType getRoomType(String name);
    int updateRoomType(RoomType rt);
    Boolean addRoomType (RoomTypeRequest roomType);
    List<RoomType> getAllRoomTypes ();
}
