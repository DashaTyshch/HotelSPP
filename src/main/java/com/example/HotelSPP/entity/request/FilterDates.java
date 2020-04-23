package com.example.HotelSPP.entity.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@ToString
@Setter
@Getter
public class FilterDates {
    private @NotBlank Timestamp start;
    private @NotBlank Timestamp end;
}
