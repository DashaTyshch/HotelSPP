package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@Builder
public class RoomImage {
    private int id;
    private @NotNull String image;
    private @NotNull int roomId;
}
