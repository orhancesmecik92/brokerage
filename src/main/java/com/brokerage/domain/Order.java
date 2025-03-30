package com.brokerage.domain;

import com.brokerage.domain.enums.OrderSide;
import com.brokerage.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long customerId;
    private String assetName;
    @Enumerated(EnumType.STRING)
    private OrderSide orderSide;
    private double size;
    private double price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime createDate;
}