package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@ToString
@Builder
public class Booking {
    private int id;
    private @NotNull Date start_date;
    private @NotNull Date end_date;
    private @NotNull float price;
    private @NotNull float period_price;
    private @NotNull boolean is_canceled;
    private @NotNull boolean is_edited;
    private @NotNull String comment;
    private @NotNull long old_booking_id;
    private @NotNull long room_type_id;
    private @NotNull long order_id;
}
