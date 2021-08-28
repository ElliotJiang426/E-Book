package com.bookstore.demo4spring.dao;

import com.bookstore.demo4spring.entity.*;

import java.util.List;

public interface UserDao {
    User getUserById(int user_id);

    UserAuth checkUser(String username, String password);

    User updateBalance(int user_id, double bills);

    double getBalance(int user_id);

    UserAuth getUserByName(String username);

    User addUser(String username, String password, String nickname, String name, String email, String tel, String address);

    void updateUser(User user);

    User updateUser(int user_id, String avatar, String nickname, String email, String tel, String address, String password);

    List<UserAuth> getAllUsers();

    UserAuth blockUser(int user_id);

    UserAuth unBlockUser(int user_id);
}
