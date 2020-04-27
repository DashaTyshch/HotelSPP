package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import com.example.HotelSPP.entity.response.RoomTypeResponse;

import java.util.Date;
import java.util.List;

public interface RoomService {
    RoomType getRoomTypeById(int id);
    RoomTypeResponse getRoomType(String name);
    int updateRoomType(RoomType rt);
    RoomType addRoomType (RoomTypeRequest roomType);
    Boolean addImages (int id, List<String> images);
    List<RoomTypeResponse> getAllRoomTypes ();
    List<RoomTypeResponse> getFreeRoomTypes(Date start, Date end);
}
