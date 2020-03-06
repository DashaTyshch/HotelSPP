package com.example.HotelSPP.controller.implementation;

import com.example.HotelSPP.controller.interfaces.RoomTypeController;
import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.response.UserResponse;
import com.example.HotelSPP.security.UserPrincipal;
import com.example.HotelSPP.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class RoomTypeControllerImpl implements RoomTypeController {
    @Autowired
    private UserService service;

    @Override
    public ResponseEntity<UserResponse> getUserInfo() {
        User user = getCurrentUser();
        log.error("User ", user.getPhone());
        return ResponseEntity.ok(service.getUserInfo(user.getId()));
    }

    @Override
    public ResponseEntity<UserResponse> putUserInfo() {
        return null;
    }

    private User getCurrentUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
