package com.brokage.repository;

import com.brokage.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsernameAndCreateDateBetween(String username, LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findAll();
}