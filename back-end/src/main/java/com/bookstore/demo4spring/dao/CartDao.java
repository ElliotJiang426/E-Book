package com.bookstore.demo4spring.dao;

import com.bookstore.demo4spring.entity.Cart;

import java.util.List;

public interface CartDao {
    Cart getCartItem(int user_id, int book_id);

    Cart addToCart(int user_id, int book_id, int quantity);

    List<Cart> getCart(int user_id);

    void deleteFromCart(int user_id, int book_id);
}
