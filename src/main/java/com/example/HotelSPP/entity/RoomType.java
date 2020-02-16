package com.example.HotelSPP.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Getter
@ToString
@Builder
public class RoomType {
    private int id;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull int amount;
    private @NotNull float price;
    private @NotNull int places;
    private @NotNull int discount;

    public RoomType(int id,String name, String description, int amount, float price, int places, int discount){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setAmount(amount);
        this.setPrice(price);
        this.setPlaces(places);
        this.setDiscount(discount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int quantity) {
        this.amount = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


}
