package com.example.HotelSPP.service.interfaces;

import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.response.UserResponse;

public interface UserService {
    User getUserById(Long id);
    User getUserByPhone(String userPhone);
    UserResponse getUserInfo(Long id);
}
