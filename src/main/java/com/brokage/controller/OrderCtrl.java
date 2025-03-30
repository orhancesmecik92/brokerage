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
@RequestMapping("/orders")
public class OrderCtrl {

    private final OrderService orderService;

    public OrderCtrl(OrderService orderService) {
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

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        String currentUser = getCurrentUser();
        orderService.cancelUserOrder(orderId, currentUser);
        return ResponseEntity.ok("Order canceled successfully.");
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