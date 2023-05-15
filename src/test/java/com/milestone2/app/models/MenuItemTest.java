package com.milestone2.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MenuItemTest {

    @Test
    public void testGetRestaurant() {
        MenuItem menuItem = new MenuItem("Restaurant", "Item", 10.99);
        assertEquals("Restaurant", menuItem.getRestaurant());
    }

    @Test
    public void testSetRestaurant() {
        MenuItem menuItem = new MenuItem();
        menuItem.setRestaurant("Restaurant");
        assertEquals("Restaurant", menuItem.getRestaurant());
    }

    @Test
    public void testGetItem() {
        MenuItem menuItem = new MenuItem("Restaurant", "Item", 10.99);
        assertEquals("Item", menuItem.getItem());
    }

    @Test
    public void testSetItem() {
        MenuItem menuItem = new MenuItem();
        menuItem.setItem("Item");
        assertEquals("Item", menuItem.getItem());
    }

    @Test
    public void testGetPrice() {
        MenuItem menuItem = new MenuItem("Restaurant", "Item", 10.99);
        assertEquals(10.99, menuItem.getPrice(), 0.001);
    }

    @Test
    public void testSetPrice() {
        MenuItem menuItem = new MenuItem();
        menuItem.setPrice(10.99);
        assertEquals(10.99, menuItem.getPrice(), 0.001);
    }

    @Test
    public void testGetId() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId("123");
        assertEquals("123", menuItem.getId());
    }

    @Test
    public void testSetId() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId("123");
        assertEquals("123", menuItem.getId());
    }

    @Test
    public void testConstructor() {
        MenuItem menuItem = new MenuItem("Restaurant", "Item", 10.99);
        assertEquals("Restaurant", menuItem.getRestaurant());
        assertEquals("Item", menuItem.getItem());
        assertEquals(10.99, menuItem.getPrice(), 0.001);
    }

}
