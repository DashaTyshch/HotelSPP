package com.example.HotelSPP.controller.interfaces;

import com.example.HotelSPP.entity.RoomType;
import com.example.HotelSPP.entity.request.FilterDates;
import com.example.HotelSPP.entity.request.RoomTypeRequest;
import com.example.HotelSPP.entity.response.RoomTypeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/room_type")
public interface RoomTypeController {

    @GetMapping("/get")
    ResponseEntity<RoomTypeResponse> getRoomType(@RequestParam String name);

    @GetMapping("/all")
    ResponseEntity<List<RoomTypeResponse>> getAllRoomTypes();

    @PostMapping("/create")
    ResponseEntity<String> postRoomType(@RequestBody RoomTypeRequest roomType);

    @PostMapping("/all_free_on_dates")
    ResponseEntity<List<RoomTypeResponse>> getAllFreeRoomTypes(@RequestBody FilterDates dates);
}
