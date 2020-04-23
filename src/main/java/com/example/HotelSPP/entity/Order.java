package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class Order {
    private long id;
    private @NotNull Date date_created;
    private @NotNull long guest_Id;
    private @NotNull long state_Id;
}
