package com.example.HotelSPP.controller.implementation;

import com.example.HotelSPP.controller.interfaces.RoomTypeController;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import com.example.HotelSPP.service.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/room_type")
public class RoomTypeControllerImpl implements RoomTypeController {
    @Autowired
    private RoomService service;

    @Override
    public ResponseEntity<String> postRoomType(RoomTypeRequest roomType) {
        if (!service.addRoomType(roomType))
            return new ResponseEntity<>("Such room type already exists!",
                    HttpStatus.CONFLICT);
        return ResponseEntity.ok("Room type created successfully!");
    }

}
