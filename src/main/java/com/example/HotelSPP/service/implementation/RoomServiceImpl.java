package com.example.HotelSPP.service.implementation;
import com.example.HotelSPP.repository.interfaces.RoomRepository;
import com.example.HotelSPP.entity.Comforts;
import com.example.HotelSPP.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.HotelSPP.entity.RoomType;

import java.util.List;
import java.util.UUID;


public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    int createRoomType(String name, String description, int amount, float price, int places, int discount, List<String> comforts){



        if(roomRepository.roomTypeAvailable(name)){
            RoomType rt = new RoomType(0, name,  description,  amount,  price,  places,  discount);
            roomRepository.addRoomType(rt);
            return 1;
        }

        return 0;
    }

    int createComfort(){
        return 0;
    }

//  TODO call Repository addRoomType()
    @Override
    public int createRoomType(String name) {
        return 0;
    }

//  TODO call Repository updateRoomType()
    @Override
    public int updateRoomType(String name) {
        return 0;
    }
}
