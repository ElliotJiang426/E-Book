package com.bookstore.demo4spring.service;

import com.bookstore.demo4spring.entity.Order;
import com.bookstore.demo4spring.entity.OrderItem;

import java.util.List;

public interface OrderService {
    Order findOrderById(Integer id);

    Order addToOrders(int user_id, int receiver_id);

    OrderItem addOrderItem(int order_id, int book_id, int quantity, double price);

    List<Order> getOrders(int user_id);

    List<Order> getAllOrders();
}
