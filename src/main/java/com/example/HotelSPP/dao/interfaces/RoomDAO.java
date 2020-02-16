package com.example.HotelSPP.dao.interfaces;

import com.example.HotelSPP.entity.RoomType;

public interface RoomDAO {
    boolean roomTypeAvailable(String name);
    void addRoomType(RoomType roomType);
}
