package com.example.HotelSPP.util;

public class SQLQueries {
    public static final String IS_ROOM_TYPE_OCCUPIED = "SELECT COUNT(*) " +
            "FROM room_type " +
            "WHERE room_type.name = ?";
    public static final String ADD_ROOM_TYPE = "INSERT INTO " +
            "room_type(id, name, description, amount, price, places, discount) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ) ";

}
