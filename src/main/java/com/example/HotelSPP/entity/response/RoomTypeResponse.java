package com.example.HotelSPP.entity.response;

import com.example.HotelSPP.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Builder
public class RoomTypeResponse {
    private @NotNull int id;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull int amount;
    private @NotNull float price;
    private @NotNull int places;
    private List<Image> images;
}
