package com.example.HotelSPP.entity.request;

import com.example.HotelSPP.entity.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Setter
@Getter
public class RoomTypeRequest {
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull int amount;
    private @NotNull float price;
    private @NotNull int places;
}
