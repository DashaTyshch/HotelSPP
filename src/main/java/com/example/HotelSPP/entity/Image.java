package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@Builder
public class Image {
    private int id;
    private @NotNull String image;
    private @NotNull long room_Type_Id;
}
