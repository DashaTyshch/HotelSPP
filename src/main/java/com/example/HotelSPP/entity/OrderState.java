package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Getter
@ToString
@Builder
public class OrderState {
    private int id;
    private @NotNull String name;
}
