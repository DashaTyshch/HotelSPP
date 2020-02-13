package com.example.HotelSPP.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    HEAD(1), ADMINISTRATOR(2), USER(3);
    private int userId;

    UserRole(int id) {
        userId = id;
    }

    @JsonValue
    public int getId() {
        return userId;
    }

    public static UserRole getRoleById(int id) {
        for (UserRole r : UserRole.values()) {
            if (r.userId == id) {
                return r;
            }
        }
        throw new IllegalArgumentException("No appropriate ban status for id !");
    }
}
