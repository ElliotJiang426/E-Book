package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderitemRepository extends JpaRepository<OrderItem, Integer> {
}
