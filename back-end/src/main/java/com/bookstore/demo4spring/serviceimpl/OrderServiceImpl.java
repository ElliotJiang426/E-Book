package com.bookstore.demo4spring.serviceimpl;

import com.bookstore.demo4spring.dao.BookDao;
import com.bookstore.demo4spring.dao.OrderDao;
import com.bookstore.demo4spring.dao.ReceiverDao;
import com.bookstore.demo4spring.dao.UserDao;
import com.bookstore.demo4spring.entity.*;
import com.bookstore.demo4spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReceiverDao receiverDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Order findOrderById(Integer id) {
        return orderDao.findOne(id);
    }

    @Override
    public Order addToOrders(int user_id, int receiver_id) {
        User user = userDao.getUserById(user_id);
        Receiver receiver = receiverDao.getReceiver(receiver_id);
        return orderDao.addToOrders(user, receiver);
    }

    @Override
    public OrderItem addOrderItem(int order_id, int book_id, int quantity, double price) {
        Order order = orderDao.findOne(order_id);
        BriefBook book = bookDao.findOneBriefBook(book_id);
        Integer q = -1*quantity;
        Book b = bookDao.updateStock(book_id, q);
        return orderDao.addOrderItem(order, book, quantity, price);
    }

    @Override
    public List<Order> getOrders(int user_id){
        User user = userDao.getUserById(user_id);
        List<Order> l = user.getOrderList();

        for(Order o : l){
            for(OrderItem oi : o.getOrderItemList()){
                BriefBook b = bookDao.findOneBriefBook(oi.getBookId());
                oi.setBook(b);
            }
        }
        return l;
    }

    @Override
    public List<Order> getAllOrders(){
        List<Order> ol = orderDao.getAllOrders();

        for(Order o : ol){
            for(OrderItem oi : o.getOrderItemList()){
                BriefBook b = bookDao.findOneBriefBook(oi.getBookId());
                oi.setBook(b);
            }
        }

        return ol;
    }
}
