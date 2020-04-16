package com.example.HotelSPP.controller.interfaces;

import com.example.HotelSPP.entity.request.LoginRequest;
import com.example.HotelSPP.entity.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/signIn")
    ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

    @PostMapping("/signUp")
    ResponseEntity<String> registerUser(HttpServletRequest httpServletRequest,
                                        @Valid @RequestBody RegisterRequest signUpRequest);

}