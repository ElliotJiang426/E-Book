package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "from Cart where userId = :userId and bookId = :bookId")
    Cart getCartItem(@Param("userId") int userId, @Param("bookId") int bookId);

    @Query(value = "select c from Cart c where c.userId = :userId")
    List<Cart> getCart(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(value = "delete from Cart c where c.userId = :userId and c.bookId = :bookId")
    void deleteFromCart(@Param("userId") int userId, @Param("bookId") int bookId);
}
