package com.brokerage.service;

import com.brokerage.domain.Asset;
import com.brokerage.domain.Customer;
import com.brokerage.domain.Order;
import com.brokerage.domain.enums.OrderStatus;
import com.brokerage.domain.enums.Role;
import com.brokerage.repository.AssetRepository;
import com.brokerage.repository.CustomerRepository;
import com.brokerage.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AssetRepository assetRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, AssetRepository assetRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.assetRepository = assetRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        order.setCreateDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(String currentUser, LocalDate startDate, LocalDate endDate) {
        if(Role.ADMIN.equals(currentUser)){
            return orderRepository.findAll();
        }else {
            return orderRepository.findByUsernameAndCreateDateBetween(currentUser, startDate.atStartOfDay(), endDate.atTime(23, 59));
        }
    }
    @Transactional
    public void cancelAllUserOrder(String currentUser) {
        if(Role.ADMIN.equals(currentUser)){
            List<Order> allOrders = orderRepository.findAll();
            if(!allOrders.isEmpty()){
                allOrders.stream().forEach((order)-> {
                    if(OrderStatus.PENDING.equals(order.getStatus())){
                        order.setStatus(OrderStatus.CANCELED);
                    }
                });
                orderRepository.saveAll(allOrders);
            }
        }else {
            throw new RuntimeException("You can only cancel your own orders.");
        }
    }

    @Transactional
    public void cancelUserOrder(Long orderId, String currentUser) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Optional<Customer> customer = customerRepository.findByUsername(currentUser);
        if(customer.isPresent()){
            if(Role.ADMIN.equals(customer.get().getRole())){
                if(OrderStatus.PENDING.equals(order.getStatus())){
                    order.setStatus(OrderStatus.CANCELED);
                    orderRepository.save(order);


                    Asset asset = assetRepository.findByCustomerIdAndAssetName(customer.get().getId(), order.getAssetName())
                            .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

                    asset.setSize(asset.getUsableSize() + (order.getSize() * order.getPrice()));
                    assetRepository.save(asset);
                }else {
                    throw new RuntimeException("Only PENDING orders can be canceled.");
                }
            }
        }else {
            if (!order.getUsername().equals(currentUser)) {
                throw new RuntimeException("You can only cancel your own orders.");
            }
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new RuntimeException("Only PENDING orders can be canceled.");
            }
            if(OrderStatus.PENDING.equals(order.getStatus())){
                order.setStatus(OrderStatus.CANCELED);
                orderRepository.save(order);

                Asset asset = assetRepository.findByCustomerIdAndAssetName(customer.get().getId(), order.getAssetName())
                        .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

                asset.setSize(asset.getUsableSize() + (order.getSize() * order.getPrice()));
                assetRepository.save(asset);
            }
        }
    }

    public List<Order> getAllOrders(LocalDate startDate, LocalDate endDate, String currentUser) {
        Optional<Customer> customer = customerRepository.findByUsername(currentUser);
        if(customer.isPresent()){
            if(Role.ADMIN.equals(customer.get().getRole())){
                return orderRepository.findAll();
            }
        }
        throw new RuntimeException("You'r not authorized to see !!");

    }
}