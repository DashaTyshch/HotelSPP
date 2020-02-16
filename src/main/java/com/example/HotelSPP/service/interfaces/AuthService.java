package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.request.LoginRequest;

public interface AuthService {

    Boolean isUserPhoneUnique(String phone);
    String authenticateUser(LoginRequest loginRequest);
    //boolean registerUser(RegisterRequest registerRequest);

}
