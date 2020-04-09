package com.example.HotelSPP.controller.interfaces;

import com.example.HotelSPP.entity.request.RoomTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/api/room_type")
public interface RoomTypeController {

    @PostMapping("/create")
    ResponseEntity<String> postRoomType(@RequestBody RoomTypeRequest roomType);

}
