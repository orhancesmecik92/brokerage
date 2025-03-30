package com.brokage.controller;

import com.brokage.domain.Order;
import com.brokage.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user/orders")
public class UserCtrl {

    private final OrderService orderService;

    public UserCtrl(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        String currentUser = getCurrentUser();
        order.setUsername(currentUser);
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> listUserOrders(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        String currentUser = getCurrentUser();
        return ResponseEntity.ok(orderService.getOrdersByUser(currentUser, startDate, endDate));
    }

    private String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}