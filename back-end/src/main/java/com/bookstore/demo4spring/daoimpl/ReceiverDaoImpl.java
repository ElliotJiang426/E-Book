package com.bookstore.demo4spring.daoimpl;

import com.bookstore.demo4spring.dao.ReceiverDao;
import com.bookstore.demo4spring.entity.Receiver;
import com.bookstore.demo4spring.entity.User;
import com.bookstore.demo4spring.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReceiverDaoImpl implements ReceiverDao {
    @Autowired
    ReceiverRepository receiverRepository;

    @Override
    public Receiver getReceiver(int receiver_id){
        return receiverRepository.getOne(receiver_id);
    }

    @Override
    public Receiver addToReceiver(User user, String name, String tel, String address){
        Receiver r = new Receiver();
        r.setName(name);
        r.setAddress(address);
        r.setTel(tel);
        r.setUser(user);
        receiverRepository.save(r);
        return r;
    }
}
