package com.bookstore.demo4spring.controller;

import com.bookstore.demo4spring.entity.Order;
import com.bookstore.demo4spring.entity.OrderItem;
import com.bookstore.demo4spring.entity.Receiver;
import com.bookstore.demo4spring.repository.ReceiverRepository;
import com.bookstore.demo4spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/addToOrders")
    public Order addToOrders(@RequestParam("user_id") int user_id, @RequestParam("receiver_id") int receiver_id) {
        return orderService.addToOrders(user_id, receiver_id);
    }

    @RequestMapping("/addOrderItem")
    public OrderItem addOrderItem(@RequestParam("order_id") int order_id, @RequestParam("book_id") int book_id, @RequestParam("quantity") int quantity, @RequestParam("price") double price) {
        return orderService.addOrderItem(order_id, book_id, quantity, price);
    }

    @RequestMapping("/getOrders/{id}")
    public List<Order> getOrders(@PathVariable("id") int user_id){

        List<Order> l = orderService.getOrders(user_id);
                for(Order o : l){
            System.out.println("AAA:\n");
            System.out.println(o);
        }
        return l;
    }

    @RequestMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
