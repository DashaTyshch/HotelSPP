package com.example.HotelSPP.repository.interfaces;

import com.example.HotelSPP.entity.User;

import java.util.Optional;

public interface UserRepository {

    User addUser(User user);

    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhone(String phone);

    boolean isSignedUp(String phone, String email);
}
