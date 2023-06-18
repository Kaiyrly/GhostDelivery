package com.milestone2.app.controllers;

import com.milestone2.app.models.Order;
import com.milestone2.app.models.MenuItem;
import com.milestone2.app.repositories.OrderRepository;
import com.milestone2.app.repositories.MenuItemRepository;
import com.milestone2.app.security.CustomUserDetails;
import com.milestone2.app.util.JwtUtil;
import com.milestone2.app.enums.OrderStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderController(OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order, Authentication authentication) {
        System.out.println(order);
        System.out.println(order.getUserId());

        order.setStatus(OrderStatus.PLACED);
        order.setOrderTime(new Date()); 

        Order createdOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }


    @PostMapping("/{orderId}/take/{userId}")
    public ResponseEntity<Order> takeOrder(@PathVariable String orderId,@PathVariable String userId, Authentication authentication) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(OrderStatus.IN_PROGRESS);
            System.out.println(userId);
            order.setDelivererId(userId); // Sets delivererId to current authenticated user
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/{orderId}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable String orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(OrderStatus.DELIVERED);
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/menu/{restaurant}")
    public ResponseEntity<List<MenuItem>> getMenu(@PathVariable String restaurant) {
        List<MenuItem> menu = menuItemRepository.findByRestaurant(restaurant);
        if (menu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(menu);
    }
    
    private String getUserIdFromAuthentication(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUserId();
        }

        // Return null or an appropriate value if the user ID cannot be retrieved
        return null;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurant/{restaurant}")
    public ResponseEntity<List<Order>> getOrdersByRestaurant(@PathVariable String restaurant) {
        List<Order> orders = orderRepository.findByRestaurant(restaurant);
        return ResponseEntity.ok(orders);
    }

}
