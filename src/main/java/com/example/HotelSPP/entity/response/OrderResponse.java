package com.example.HotelSPP.entity.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@Builder
public class OrderResponse {
    private long id;
    private @NotNull Date date_created;
    private @NotNull long guest_Id;
    private @NotNull long state_Id;
}
