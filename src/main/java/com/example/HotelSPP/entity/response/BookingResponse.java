package com.example.HotelSPP.entity.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@ToString
@Getter
@Setter
@Builder
public class BookingResponse {
    private @NotNull Date start_date;
    private @NotNull Date end_date;
    private @NotNull float price;
    private @NotNull float period_price;
    private @NotNull String comment;
    private @NotNull String room_type;
}
