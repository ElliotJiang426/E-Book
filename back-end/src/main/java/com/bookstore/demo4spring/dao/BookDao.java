package com.bookstore.demo4spring.dao;

import com.bookstore.demo4spring.entity.Book;
import com.bookstore.demo4spring.entity.BriefBook;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    BriefBook findOneBriefBook(Integer id);

    List<Book> getBooks();

    List<BriefBook> getBriefBooks();

    List<Book> findOneByNameLike(String name);

    Book updateStock(Integer id, Integer quantity);

    Book deleteBook(int id);

    Book updateBook(int bookId, String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories);

    Book addBook(String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories);

}
