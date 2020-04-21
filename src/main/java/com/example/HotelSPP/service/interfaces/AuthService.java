package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.request.LoginRequest;
import com.example.HotelSPP.entity.request.RegisterRequest;

public interface AuthService {

    Boolean isUserPhoneUnique(String phone);
    String authenticateUser(LoginRequest loginRequest);

    boolean registerUser(RegisterRequest signUpRequest);

}
