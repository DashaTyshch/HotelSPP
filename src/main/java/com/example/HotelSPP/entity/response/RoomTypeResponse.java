package com.example.HotelSPP.entity.response;

import com.example.HotelSPP.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Builder
public class RoomTypeResponse {
    private @NotNull int response;
}
