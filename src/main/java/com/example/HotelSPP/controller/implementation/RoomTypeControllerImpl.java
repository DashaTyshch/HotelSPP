package com.example.HotelSPP.controller.implementation;

import com.example.HotelSPP.controller.interfaces.RoomTypeController;
import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.FilterDates;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import com.example.HotelSPP.entity.response.RoomTypeResponse;
import com.example.HotelSPP.service.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/room_type")
public class RoomTypeControllerImpl implements RoomTypeController {
    @Autowired
    private RoomService service;

    @Override
    public ResponseEntity<RoomTypeResponse> getRoomType(String name) {
        return ResponseEntity.ok(service.getRoomType(name));
    }

    @Override
    public ResponseEntity<List<RoomTypeResponse>> getAllRoomTypes() {
        return ResponseEntity.ok(service.getAllRoomTypes());
    }

    @Override
    public ResponseEntity<String> postRoomType(RoomTypeRequest roomType) {
        RoomType rt = service.addRoomType(roomType);
        if (rt == null)
            return new ResponseEntity<>("Такий тип номеру вже створений.",
                    HttpStatus.CONFLICT);
        service.addImages(rt.getId(), roomType.getImages());
        return ResponseEntity.ok(rt.getName());
    }

    @Override
    public ResponseEntity<List<RoomType>> getAllFreeRoomTypes(FilterDates dates) {
        return ResponseEntity.ok(service.getFreeRoomTypes(dates.getStart(), dates.getEnd()));
    }

}
