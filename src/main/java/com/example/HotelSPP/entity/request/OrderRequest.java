package com.example.HotelSPP.entity.request;

import com.example.HotelSPP.entity.Booking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ToString
@Setter
@Getter
@Builder
public class OrderRequest {
    private @NotNull Date start_date;
    private @NotNull Date end_date;
    private @NotNull float price;
    private @NotNull float period_price;
    private @NotNull String comment;
    private @NotNull int room_type_id;
}
