package com.bookstore.demo4spring.service;

import com.bookstore.demo4spring.entity.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    UserAuth checkUser(String username, String password);

    Receiver addToReceiver(int user_id, String name, String tel, String address);

    User updateBalance(int user_id, double bills);

    double getBalance(int user_id);

    UserAuth getUserByName(String username);

    User addUser(String username, String password, String nickname, String name, String email, String tel, String address);

    User addToLikeList(int user_id, int book_id);

    List<BriefBook> getFavorites(int user_id);

    User recharge(int user_id, double bills);

    User updateUser(int user_id, String avatar, String nickname, String email, String tel, String address, String password);

    List<UserAuth> getAllUsers();

    UserAuth blockUser(int user_id);

    UserAuth unBlockUser(int user_id);
}
