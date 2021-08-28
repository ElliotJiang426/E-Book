package com.bookstore.demo4spring.service;

import com.bookstore.demo4spring.entity.Book;
import com.bookstore.demo4spring.entity.BriefBook;

import java.util.List;


public interface BookService {

    Book findBookById(Integer id);

    List<Book> getBooks();

    List<Book> findByNameLike(String name);

    List<BriefBook> getBriefBooks();

    Book deleteBook(int id);

    Book updateBook(int bookId, String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories);

    Book addBook(String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories);
}

