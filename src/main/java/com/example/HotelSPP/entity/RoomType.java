package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Getter
@Setter
@ToString
@Builder
public class RoomType {
    private int id;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull int amount;
    private @NotNull float price;
    private @NotNull int places;
    private @NotNull int discount;
}
