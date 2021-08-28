package com.bookstore.demo4spring.daoimpl;

import com.bookstore.demo4spring.dao.CartDao;
import com.bookstore.demo4spring.entity.Cart;
import com.bookstore.demo4spring.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.PushBuilder;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart getCartItem(int user_id, int book_id){
        return cartRepository.getCartItem(user_id, book_id);
    }

    @Override
    public Cart addToCart(int user_id, int book_id, int quantity){
        Cart cart = new Cart();
        cart.setUserId(user_id);
        cart.setBookId(book_id);
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCart(int user_id){
        return cartRepository.getCart(user_id);
    }

    @Override
    public void deleteFromCart(int user_id, int book_id) { cartRepository.deleteFromCart(user_id, book_id);}
}
