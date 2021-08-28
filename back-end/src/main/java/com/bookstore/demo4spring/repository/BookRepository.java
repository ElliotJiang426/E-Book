package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.Book;
import com.bookstore.demo4spring.entity.BriefBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> getBooks();

    @Query("select b from Book b where b.bookName like ?1")
    List<Book> findByNameLike(String name);

    @Query("select b from BriefBook b")
    List<BriefBook> getBriefBooks();

    @Query("select b from BriefBook b where b.bookId = :bookId")
    BriefBook getBriefBook(@Param("bookId")int bookId);
}