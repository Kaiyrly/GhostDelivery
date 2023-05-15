package com.milestone2.app.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.milestone2.app.enums.OrderStatus;

class OrderTest {

    @Test
    public void testGetId() {
        Order order = new Order();
        order.setId("123");
        assertEquals("123", order.getId());
    }

    @Test
    public void testSetId() {
        Order order = new Order();
        order.setId("123");
        assertEquals("123", order.getId());
    }

    @Test
    public void testGetUserId() {
        Order order = new Order("user123", "Restaurant");
        assertEquals("user123", order.getUserId());
    }

    @Test
    public void testSetUserId() {
        Order order = new Order();
        order.setUserId("user123");
        assertEquals("user123", order.getUserId());
    }

    @Test
    public void testGetRestaurant() {
        Order order = new Order("user123", "Restaurant");
        assertEquals("Restaurant", order.getRestaurant());
    }

    @Test
    public void testSetRestaurant() {
        Order order = new Order();
        order.setRestaurant("Restaurant");
        assertEquals("Restaurant", order.getRestaurant());
    }

    @Test
    public void testGetItemQuantities() {
        Order order = new Order();
        Map<String, Integer> itemQuantities = new HashMap<>();
        itemQuantities.put("Item 1", 2);
        order.setItemQuantities(itemQuantities);
        assertEquals(itemQuantities, order.getItemQuantities());
    }

    @Test
    public void testSetItemQuantities() {
        Order order = new Order();
        Map<String, Integer> itemQuantities = new HashMap<>();
        itemQuantities.put("Item 1", 2);
        order.setItemQuantities(itemQuantities);
        assertEquals(itemQuantities, order.getItemQuantities());
    }

    @Test
    public void testAddItem() {
        Order order = new Order("user123", "Restaurant");
        order.addItem("Item 1", 2);

        Map<String, Integer> expectedQuantities = new HashMap<>();
        expectedQuantities.put("Item 1", 2);

        assertEquals(expectedQuantities, order.getItemQuantities());
    }

    @Test
    public void testRemoveItem() {
        Order order = new Order("user123", "Restaurant");
        order.addItem("Item 1", 2);

        order.removeItem("Item 1");

        assertNull(order.getItemQuantities().get("Item 1"));
    }

    @Test
    public void testGetDelivererId() {
        Order order = new Order();
        order.setDelivererId("deliverer123");
        assertEquals("deliverer123", order.getDelivererId());
    }

    @Test
    public void testSetDelivererId() {
        Order order = new Order();
        order.setDelivererId("deliverer123");
        assertEquals("deliverer123", order.getDelivererId());
    }

}
