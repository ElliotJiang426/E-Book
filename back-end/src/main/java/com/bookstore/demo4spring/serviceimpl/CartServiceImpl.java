package com.bookstore.demo4spring.serviceimpl;

import com.bookstore.demo4spring.dao.BookDao;
import com.bookstore.demo4spring.dao.CartDao;
import com.bookstore.demo4spring.entity.Cart;
import com.bookstore.demo4spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Override
    public Cart addToCart(int user_id, int book_id, int quantity){
        Cart cart = cartDao.getCartItem(user_id, book_id);
        int q = quantity;
        if(cart != null){
            q = q + cart.getQuantity();
            return cartDao.addToCart(user_id, book_id, q);
        }
        else 
            return cartDao.addToCart(user_id, book_id, quantity);
    };

    @Override
    public List<Cart> getCart(int user_id){
        return cartDao.getCart(user_id);
    }

    @Override
    public void deleteFromCart(int user_id, int book_id) { cartDao.deleteFromCart(user_id, book_id);}
}
