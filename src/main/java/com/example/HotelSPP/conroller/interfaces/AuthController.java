package com.example.HotelSPP.conroller.interfaces;

import com.example.HotelSPP.entity.request.LoginRequest;
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

}