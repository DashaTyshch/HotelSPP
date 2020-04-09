package com.example.HotelSPP.service.implementation;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import com.example.HotelSPP.repository.interfaces.RoomRepository;
import com.example.HotelSPP.service.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.HotelSPP.entity.RoomType;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

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

    @Override
    public int updateRoomType(RoomType rt) {
        Optional<RoomType> p = roomRepository.updateRoomType(rt);
        return 0;
    }

    @Override
    public Boolean addRoomType(RoomTypeRequest roomType)
    {
        if(roomRepository.roomTypeAvailable(roomType.getName())){
            try{
                roomRepository.addRoomType(RoomType.builder()
                        .name(roomType.getName())
                        .amount(roomType.getAmount())
                        .description(roomType.getDescription())
                        .places(roomType.getPlaces())
                        .price(roomType.getPrice())
                        .build());
            } catch (DuplicateKeyException e) {
                return false;
            }
        } else
            return false;

        return true;
    }
}
