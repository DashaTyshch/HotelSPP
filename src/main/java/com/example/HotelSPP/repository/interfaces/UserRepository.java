package com.example.HotelSPP.repository.interfaces;

import com.example.HotelSPP.entity.User;

import java.util.Optional;

public interface UserRepository {

    User addUser(User user);

    Optional<User> findUserById(int id);
    Optional<User> findUserByEmail(String searchStr);
    Optional<User> findUserByPhone(String searchStr);
}
