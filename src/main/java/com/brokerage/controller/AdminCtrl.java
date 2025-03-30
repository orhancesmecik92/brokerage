package com.brokerage.controller;

import com.brokerage.domain.Order;
import com.brokerage.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class AdminCtrl {

    private final OrderService orderService;

    public AdminCtrl(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> listAllOrders(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        String currentUser = getCurrentUser();
        return ResponseEntity.ok(orderService.getAllOrders(startDate, endDate, currentUser));
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        String currentUser = getCurrentUser();

        orderService.cancelUserOrder(orderId, currentUser);
        return ResponseEntity.ok("Order canceled successfully.");
    }

    @DeleteMapping("/cancelAllOrders")
    public ResponseEntity<String> deleteAllOrders() {
        String currentUser = getCurrentUser();
        orderService.cancelAllUserOrder(currentUser);
        return ResponseEntity.ok("Order canceled successfully.");
    }

    @PutMapping("/match")
    public ResponseEntity<String> matchOrders() {
        // Implement order matching logic here
        return ResponseEntity.ok("Orders matched successfully.");
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