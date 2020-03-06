package com.example.HotelSPP.entity.request;

import com.example.HotelSPP.entity.RoomType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
public class RoomTypeRequest {
    private @NotBlank String login;
    private @NotBlank String password;
    private @NotBlank RoomType roomtype;
}
