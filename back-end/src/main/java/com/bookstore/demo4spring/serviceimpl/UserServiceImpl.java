package com.bookstore.demo4spring.serviceimpl;

import com.bookstore.demo4spring.dao.BookDao;
import com.bookstore.demo4spring.dao.ReceiverDao;
import com.bookstore.demo4spring.dao.UserDao;
import com.bookstore.demo4spring.entity.*;
import com.bookstore.demo4spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReceiverDao receiverDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public UserAuth checkUser(String username, String password){
        UserAuth ua = userDao.checkUser(username,password);
        ua.setUser(userDao.getUserById(ua.getUserId()));
        return ua;
    }

    @Override
    public Receiver addToReceiver(int user_id, String name, String tel, String address) {
        User user = userDao.getUserById(user_id);
        return receiverDao.addToReceiver(user, name, tel, address);
    }

    @Override
    public User updateBalance(int user_id, double bills) {
        return userDao.updateBalance(user_id, bills);
    }

    @Override
    public double getBalance(int user_id){
        return userDao.getBalance(user_id);
    }

    @Override
    public UserAuth getUserByName(String username) { return userDao.getUserByName(username);}

    @Override
    public User addUser(String username, String password, String nickname, String name, String email, String tel, String address){
        return userDao.addUser(username, password, nickname, name, email, tel, address);
    }

    @Override
    public User addToLikeList(int user_id, int book_id){
        User u = userDao.getUserById(user_id);
        List<BriefBook> bl = u.getLikeBookList();
        BriefBook b = bookDao.findOneBriefBook(book_id);

        if(!bl.contains(b)){
            bl.add(b);
        }

        u.setLikeBookList(bl);
        userDao.updateUser(u);
        return u;
    }

    @Override
    public List<BriefBook> getFavorites(int user_id){
        User u = userDao.getUserById(user_id);
        List<BriefBook> bl = u.getLikeBookList();

        for(BriefBook book : bl) {
            book = bookDao.findOneBriefBook(book.getBookId());
        }

        return bl;
    }

    @Override
    public User recharge(int user_id, double bills){
        User u = userDao.getUserById(user_id);
        u.setBalance(u.getBalance() + bills);
        userDao.updateUser(u);
        return u;
    }

    @Override
    public User updateUser(int user_id, String avatar, String nickname, String email, String tel, String address, String password) { return userDao.updateUser(user_id, avatar, nickname, email, tel, address, password); }

    @Override
    public List<UserAuth> getAllUsers(){
        return userDao.getAllUsers();
    }

    @Override
    public UserAuth blockUser(int user_id){
        return userDao.blockUser(user_id);
    }

    @Override
    public UserAuth unBlockUser(int user_id){
        return userDao.unBlockUser(user_id);
    }
}
