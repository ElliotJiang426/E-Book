package com.bookstore.demo4spring.daoimpl;

import com.bookstore.demo4spring.dao.OrderDao;
import com.bookstore.demo4spring.entity.*;
import com.bookstore.demo4spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderitemRepository orderitemRepository;

    @Override
    public Order findOne(int id){
        return orderRepository.getOne(id);
    }

    @Override
    public Order addToOrders(User user, Receiver receiver){
        Order o = new Order();
        o.setReceiver(receiver);
        o.setUser(user);

        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());

        o.setOrderTime(nousedate);

        orderRepository.save(o);
        return o;
    }

    @Override
    public OrderItem addOrderItem(Order order, BriefBook book, int quantity, double price){
        OrderItem oi = new OrderItem();
        oi.setMyOrder(order);
        oi.setQuantity(quantity);
        oi.setPrice(price);
        oi.setBook(book);
        orderitemRepository.save(oi);
        return oi;
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
