package com.milestone2.app.models;

import com.milestone2.app.enums.OrderStatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;



@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userId;
    private String restaurant;
    private Map<String, Integer> itemQuantities;  
    private OrderStatus status; 
    private String delivererId;
    private String comments;
    private String address;
    private Date orderTime;
    private Integer fee;

    public Order() {
        this.itemQuantities = new HashMap<>();
    }

    public Order(String userId, String restaurant) {
        this.userId = userId;
        this.restaurant = restaurant;
        this.itemQuantities = new HashMap<>();
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdress() {
        return this.address;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() {
        return this.orderTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public Map<String, Integer> getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(Map<String, Integer> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public void addItem(String item, int quantity) {
        itemQuantities.put(item, quantity);
    }

    public void removeItem(MenuItem item) {
        itemQuantities.remove(item);
    }

    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getFee() {
        return this.fee;
    }

    public String getDelivererId() {
        return delivererId;
    }
    
    public void setDelivererId(String delivererId) {
        this.delivererId = delivererId;
    }

    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
