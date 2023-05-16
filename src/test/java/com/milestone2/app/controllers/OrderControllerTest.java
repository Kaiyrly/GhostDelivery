package com.milestone2.app.controllers;

import com.milestone2.app.controllers.OrderController;
import com.milestone2.app.enums.OrderStatus;
import com.milestone2.app.models.MenuItem;
import com.milestone2.app.models.Order;
import com.milestone2.app.repositories.MenuItemRepository;
import com.milestone2.app.repositories.OrderRepository;
import com.milestone2.app.security.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_ShouldReturnCreatedStatusAndOrder() {
        // Arrange
        Order order = new Order();
        order.setStatus(OrderStatus.PLACED);
        when(orderRepository.save(order)).thenReturn(order);

        Authentication authentication = mock(Authentication.class);
        CustomUserDetails userDetails = mock(CustomUserDetails.class);
        when(userDetails.getUserId()).thenReturn("user123");
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Act
        ResponseEntity<Order> response = orderController.placeOrder(order, authentication);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void takeOrder_WithExistingOrderId_ShouldReturnOrderWithInProgressStatus() {
        // Arrange
        String orderId = "order123";
        Order order = new Order();
        order.setStatus(OrderStatus.PLACED);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Authentication authentication = mock(Authentication.class);
        CustomUserDetails userDetails = mock(CustomUserDetails.class);
        when(userDetails.getUserId()).thenReturn("deliverer123");
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Act
        ResponseEntity<Order> response = orderController.takeOrder(orderId, authentication);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(OrderStatus.IN_PROGRESS, response.getBody().getStatus());
        assertEquals("deliverer123", response.getBody().getDelivererId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void takeOrder_WithNonexistentOrderId_ShouldReturnNotFoundStatus() {
        // Arrange
        String orderId = "nonexistent";

        Authentication authentication = mock(Authentication.class);

        // Act
        ResponseEntity<Order> response = orderController.takeOrder(orderId, authentication);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void completeOrder_WithExistingOrderId_ShouldReturnOrderWithDeliveredStatus() {
        // Arrange
        String orderId = "order123";
        Order order = new Order();
        order.setStatus(OrderStatus.IN_PROGRESS);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        ResponseEntity<Order> response = orderController.completeOrder(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(OrderStatus.DELIVERED, response.getBody().getStatus());
        verify(orderRepository, times(1)).save(order);
    }
}