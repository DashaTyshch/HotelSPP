package com.example.HotelSPP.controller.interfaces;

import com.example.HotelSPP.entity.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/api/room_type")
public interface RoomTypeController {

    @PutMapping("/update")
    ResponseEntity<UserResponse> getUserInfo();

    @GetMapping()
    ResponseEntity<UserResponse> getUserInfo();
}
