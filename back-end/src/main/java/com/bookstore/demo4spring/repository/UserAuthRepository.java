package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAuthRepository extends JpaRepository<UserAuth,Integer> {

    @Query(value = "from UserAuth where username = :username and password = :password")
    UserAuth checkUser(@Param("username") String username, @Param("password") String password);

    @Query(value = "from UserAuth where username = :username")
    UserAuth getUserByName(@Param("username") String username);
}
