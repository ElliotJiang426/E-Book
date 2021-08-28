package com.bookstore.demo4spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.demo4spring.entity.*;
import com.bookstore.demo4spring.service.CartService;
import com.bookstore.demo4spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/getUser/{username}")
    public UserAuth getUserByName(@PathVariable("username") String username){
        return userService.getUserByName(username);
    }

    @RequestMapping(value = "/checkUser")
    public UserAuth checkUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.checkUser(username, password);
    }

    @RequestMapping(value = "/addToCart")
    public Cart addToCart(@RequestParam("user_id") int user_id, @RequestParam("book_id") int book_id, @RequestParam("quantity") int quantity){
        return cartService.addToCart(user_id, book_id, quantity);
    }

    @RequestMapping(value = "/getCart/{id}")
    public List<Cart> getCart(@PathVariable("id") int user_id){
        return cartService.getCart(user_id);
    }

    @RequestMapping(value = "/deleteFromCart/userId={user_id}&bookId={book_id}")
    public void deleteFromCart(@PathVariable("user_id") int user_id, @PathVariable("book_id") int book_id) { cartService.deleteFromCart(user_id, book_id);}

    @RequestMapping(value = "/addToReceivers")
    public Receiver addToReceiver(@RequestParam("user_id") int user_id, @RequestParam("name") String name, @RequestParam("tel") String tel, @RequestParam("address") String address) {
        return userService.addToReceiver(user_id, name, tel, address);
    }

    @RequestMapping(value = "/updateBalance")
    public User updateBalance(@RequestParam("user_id") int user_id, @RequestParam("bills") double bills){
        return userService.updateBalance(user_id, bills);
    }

    @RequestMapping(value = "/getBalance/{id}")
    public double getBalance(@PathVariable("id") int user_id) {
        return userService.getBalance(user_id);
    }

    @RequestMapping(value = "/addUser")
    public User addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nickname") String nickname, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("tel") String tel, @RequestParam("address") String address){
        return userService.addUser(username, password, nickname, name, email, tel, address);
    }

    @RequestMapping(value = "/addToLikeList")
    public User addToLikeList(@RequestParam("user_id") int user_id, @RequestParam("book_id") int book_id){
        return userService.addToLikeList(user_id, book_id);
    }

    @RequestMapping("/getFavorites/{id}")
    public List<BriefBook> getFavorites(@PathVariable("id") int user_id){
        return userService.getFavorites(user_id);
    }

    @RequestMapping(value = "/recharge")
    public User recharge(@RequestParam("user_id") int user_id, @RequestParam("bills") double bills){
        return userService.recharge(user_id, bills);
    }

    @RequestMapping(value = "/updateUser")
    public User updateUser(@RequestParam("user_id") int user_id, @RequestParam("avatar") String avatar, @RequestParam("nickname") String nickname, @RequestParam("email") String email, @RequestParam("tel") String tel, @RequestParam("address") String address, @RequestParam("password") String password){
        return userService.updateUser(user_id, avatar, nickname, email, tel, address, password);
    }

    @RequestMapping(value = "/getAllUsers")
    public List<UserAuth> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/blockUser/{id}")
    public UserAuth blockUser(@PathVariable("id") int user_id){
        return userService.blockUser(user_id);
    }

    @RequestMapping(value = "/unBlockUser/{id}")
    public UserAuth unBlockUser(@PathVariable("id") int user_id){
        return userService.unBlockUser(user_id);
    }
}
