package com.example.HotelSPP.controller.implementation;

import com.example.HotelSPP.controller.interfaces.AuthController;
import com.example.HotelSPP.entity.request.LoginRequest;
import com.example.HotelSPP.entity.request.RegisterRequest;
import com.example.HotelSPP.service.interfaces.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Override
    public ResponseEntity<String> registerUser(HttpServletRequest httpServletRequest, @Valid RegisterRequest signUpRequest) {
        String url = String.valueOf(httpServletRequest.getRequestURL());
        String link = url.replace(httpServletRequest.getRequestURI(), "");
        boolean success = authService.registerUser(signUpRequest, link);
        if (success) {
            return new ResponseEntity<>("New user was created.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User with such phone number already exists!",
                HttpStatus.CONFLICT);
    }


}