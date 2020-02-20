package com.example.HotelSPP.service.implementation;

import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.response.UserResponse;
import com.example.HotelSPP.repository.interfaces.UserRepository;
import com.example.HotelSPP.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElse(null);
    }

    @Override
    public User getUserByPhone(String userPhone) {
        return userRepository.findUserByPhone(userPhone).orElse(null);
    }

    @Override
    public UserResponse getUserInfo(Long id) {
        User user = this.getUserById(id);

        UserResponse response = UserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .role(user.getRole())
                .build();
        return response;
    }
}
