package com.example.HotelSPP.service.implementation;
import com.example.HotelSPP.entity.Image;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import com.example.HotelSPP.entity.response.RoomTypeResponse;
import com.example.HotelSPP.repository.interfaces.RoomRepository;
import com.example.HotelSPP.service.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.HotelSPP.entity.RoomType;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    int createComfort(){
        return 0;
    }

    @Override
    public RoomTypeResponse getRoomType(String name) {
        RoomType roomType = roomRepository.findRoomTypeByName(name).orElse(null);
        if(roomType == null)
            return null;
        List<Image> images = roomRepository.findImagesByIds(new ArrayList<>(Collections.singletonList(roomType.getId())));
        RoomTypeResponse result =  RoomTypeResponse.builder()
                .name(roomType.getName())
                .amount(roomType.getAmount())
                .description(roomType.getDescription())
                .places(roomType.getPlaces())
                .price(roomType.getPrice())
                .images(images)
                .build();
        return result;
    }

    @Override
    public int updateRoomType(RoomType rt) {
        Optional<RoomType> p = roomRepository.updateRoomType(rt);
        return 0;
    }

    @Override
    public RoomType addRoomType(RoomTypeRequest roomType)
    {
        if(roomRepository.roomTypeAvailable(roomType.getName())){
            try{
                RoomType result =  roomRepository.addRoomType(RoomType.builder()
                        .name(roomType.getName())
                        .amount(roomType.getAmount())
                        .description(roomType.getDescription())
                        .places(roomType.getPlaces())
                        .price(roomType.getPrice())
                        .build());
                return result;
            } catch (DuplicateKeyException e) {
                return null;
            }
        } else
            return null;
    }

    @Override
    public Boolean addImages(int id, List<String> images)
    {
        try{
            for (String image: images) {
                roomRepository.addImage(id, image);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<RoomTypeResponse> getAllRoomTypes() {
        List<RoomType> roomTypes = roomRepository.getAllRoomTypes();
        if(roomTypes.isEmpty())
            return null;
        List<Image> images = roomRepository.findImagesByIds(roomTypes.stream().map(RoomType::getId).collect(Collectors.toList()));
        List<RoomTypeResponse> result = new ArrayList<>();
        for (RoomType roomType :
                roomTypes) {
            result.add(RoomTypeResponse.builder()
                    .name(roomType.getName())
                    .amount(roomType.getAmount())
                    .description(roomType.getDescription())
                    .places(roomType.getPlaces())
                    .price(roomType.getPrice())
                    .images(images.stream().filter(image -> image.getRoom_Type_Id() == roomType.getId()).collect(Collectors.toList()))
                    .build());
        }
        return result;
    }
}
