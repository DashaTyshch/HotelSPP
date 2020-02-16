package com.example.HotelSPP.conroller.implementation;

import com.example.HotelSPP.conroller.interfaces.AuthController;
import com.example.HotelSPP.entity.request.LoginRequest;
import com.example.HotelSPP.service.interfaces.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<String> authenticateUser(LoginRequest loginRequest) {
        String token = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }
}