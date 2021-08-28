package com.bookstore.demo4spring.controller;

import com.bookstore.demo4spring.entity.Book;
import com.bookstore.demo4spring.entity.BriefBook;
import com.bookstore.demo4spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping("/getBriefBooks")
    public List<BriefBook> getBriefBooks() {
        return bookService.getBriefBooks();
    }

    @RequestMapping("/getBook/{id}")
    public Book getBook(@PathVariable("id") Integer id){
        System.out.println("Searching Book: " + id);
        return bookService.findBookById(id);
    }

    @RequestMapping("/getBookByName/{name}")
    public List<Book> getBookByName(@PathVariable("name") String name){
        return bookService.findByNameLike("%"+name+"%");
    }

    @RequestMapping("/deleteBook/{id}")
    public Book deleteBook(@PathVariable("id") int id){
        return bookService.deleteBook(id);
    }

    @RequestMapping("/updateBook")
    public Book updateBook(@RequestParam("bookId") int bookId, @RequestParam("cover") String cover, @RequestParam("bookName") String bookName, @RequestParam("author") String author, @RequestParam("press") String press, @RequestParam("isbn") int isbn, @RequestParam("price") double price, @RequestParam("rate") double rate, @RequestParam("stock") int stock, @RequestParam("description") String description, @RequestParam("categories") String categories){
        return bookService.updateBook(bookId, cover, bookName, author, press, isbn, price, rate, stock, description, categories);
    }

    @RequestMapping("/addBook")
    public Book addBook(@RequestParam("cover") String cover, @RequestParam("bookName") String bookName, @RequestParam("author") String author, @RequestParam("press") String press, @RequestParam("isbn") int isbn, @RequestParam("price") double price, @RequestParam("rate") double rate, @RequestParam("stock") int stock, @RequestParam("description") String description, @RequestParam("categories") String categories){
        return bookService.addBook(cover, bookName, author, press, isbn, price, rate, stock, description, categories);
    }
}
