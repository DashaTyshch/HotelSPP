package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@ToString
@Builder
public class Orders {
    private int id;
    private @NotNull String name;
    private @NotNull Date date_created;
    private @NotNull long guest_Id;
    private @NotNull long booking_Id;
    private @NotNull long state_Id;
}
