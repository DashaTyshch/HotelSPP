package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Setter
@Getter
@ToString
@Builder
public class Booking {
    private int id;
    private @NotNull Date start_date;
    private @NotNull Date end_date;
    private @NotNull float price;
    private @NotNull float period_price;

    public boolean isIs_canceled() {
        return is_canceled;
    }

    public boolean isIs_edited() {
        return is_edited;
    }

    private @NotNull boolean is_canceled;
    private @NotNull boolean is_edited;
    private @NotNull String comment;
    private @Null Long old_booking_id;
    private @NotNull int room_type_id;
    private @NotNull long order_id;
}
