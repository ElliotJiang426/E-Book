package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "from User where userId = :userId")
    User getUser(@Param("userId") int userId);

    @Query(value = "select balance from User where userId = :userId")
    double getBalance(@Param("userId") int userId);

}
