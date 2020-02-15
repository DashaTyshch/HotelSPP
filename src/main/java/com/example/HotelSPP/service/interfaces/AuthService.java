package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.request.LoginRequest;

public interface AuthService {

    //Boolean isUserPhoneNumberUnique(String phoneNumber);
    String authenticateUser(LoginRequest loginRequest);
    //boolean registerUser(RegisterRequest registerRequest);

}
