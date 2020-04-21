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
    public ResponseEntity<String> registerUser(@Valid RegisterRequest signUpRequest) {
        boolean success = authService.registerUser(signUpRequest);
        if (success) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setLogin(signUpRequest.getPhone());
            loginRequest.setPassword(signUpRequest.getPassword());
            return authenticateUser(loginRequest);
        }
        return new ResponseEntity<>("Користувач з таким номером або поштою вже зареєстрований.",
                HttpStatus.CONFLICT);
    }


}