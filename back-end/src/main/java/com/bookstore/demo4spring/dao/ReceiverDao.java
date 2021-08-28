package com.bookstore.demo4spring.dao;

import com.bookstore.demo4spring.entity.Receiver;
import com.bookstore.demo4spring.entity.User;

public interface ReceiverDao {
    Receiver getReceiver(int receiver_id);

    Receiver addToReceiver(User user, String name, String tel, String address);
}
