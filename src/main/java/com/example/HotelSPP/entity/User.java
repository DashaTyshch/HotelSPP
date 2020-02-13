package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@Builder
public class User {
    private int id;
    private @NotNull String phone;
    private @NotNull String surname;
    private @NotNull String name;
    private @NotNull String email;
    private @NotNull String password;
    private @NotNull UserRole role;
}
