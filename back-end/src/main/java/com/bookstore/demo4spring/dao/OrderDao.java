package com.bookstore.demo4spring.dao;

import com.bookstore.demo4spring.entity.*;

import java.util.List;

public interface OrderDao {
    Order findOne(int id);

    Order addToOrders(User user, Receiver receiver);

    OrderItem addOrderItem(Order order, BriefBook book, int quantity, double price);

    List<Order> getAllOrders();
}
