package com.marcusfeitosa.orderservice.repository;

import com.marcusfeitosa.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
