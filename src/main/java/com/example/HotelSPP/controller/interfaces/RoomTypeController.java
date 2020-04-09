package com.example.HotelSPP.controller.interfaces;

import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/room_type")
public interface RoomTypeController {

    @GetMapping("/all")
    ResponseEntity<List<RoomType>> getAllRoomTypes();

    @PostMapping("/create")
    ResponseEntity<String> postRoomType(@RequestBody RoomTypeRequest roomType);

}
