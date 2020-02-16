package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@Builder
public class RoomComforts {
    private int id;
    private @NotNull Comforts comfort;
    private @NotNull RoomType roomType;
}
