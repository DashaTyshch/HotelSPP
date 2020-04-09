package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.RoomTypeRequest;

public interface RoomService {
    int updateRoomType(RoomType rt);
    Boolean addRoomType (RoomTypeRequest roomType);
}
