package com.milestone2.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menuItems")
public class MenuItem {

    @Id
    private String id;

    private String restaurant;
    private String item;
    private double price;

    public MenuItem() {
    }

    public MenuItem(String restaurant, String item, double price) {
        this.restaurant = restaurant;
        this.item = item;
        this.price = price;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
