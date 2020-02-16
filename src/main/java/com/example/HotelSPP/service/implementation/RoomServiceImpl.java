package com.example.HotelSPP.service.implementation;
import com.example.HotelSPP.dao.interfaces.RoomDAO;
import com.example.HotelSPP.entity.Comforts;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.HotelSPP.entity.RoomType;

import java.util.List;
import java.util.UUID;

public class RoomServiceImpl {
    private final RoomDAO roomDAO;
    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO){
        this.roomDAO = roomDAO;
    }
        int createRoomType(String name, String description, int amount, float price, int places, int discount, List<String> comforts){



            if(roomDAO.roomTypeAvailable(name)){
                RoomType rt = new RoomType();

                int id = UUID.randomUUID().hashCode();
                rt.setId(id);
                rt.setAmount(amount);
                rt.setDescription(description);
                rt.setName(name);
                rt.setDiscount(discount);
                rt.setPlaces(places);
                rt.setPrice(price);
                roomDAO.addRoomType(rt);
                return 1;
            }

            return 0;
        }

        int createComfort(){
            return 0;
        }


}
