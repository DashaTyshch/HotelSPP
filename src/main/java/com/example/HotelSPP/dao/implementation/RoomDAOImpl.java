package com.example.HotelSPP.dao.implementation;
import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.util.SQLQueries;
import com.example.HotelSPP.dao.interfaces.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class RoomDAOImpl implements RoomDAO{

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public RoomDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public boolean roomTypeAvailable(String name){
        int amountOfRooms;
        amountOfRooms =  jdbcTemplate.queryForObject(SQLQueries.IS_ROOM_TYPE_OCCUPIED, Integer.class, name);
        return amountOfRooms == 0;
    }
    public void addRoomType(RoomType roomType){
        jdbcTemplate.update(SQLQueries.ADD_ROOM_TYPE,
                roomType.getId(),
                roomType.getName(),
                roomType.getDescription(),
                roomType.getAmount(),
                roomType.getPrice(),
                roomType.getPlaces(),
                roomType.getDiscount());
    }

}
