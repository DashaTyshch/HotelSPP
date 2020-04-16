package com.example.HotelSPP.entity.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Setter
@Getter
public class RegisterRequest {
    @NotNull
    private String phone;
    private String name;
    private String surname;
    @NotNull
    private String password;
    private String email;
    private int role;
}