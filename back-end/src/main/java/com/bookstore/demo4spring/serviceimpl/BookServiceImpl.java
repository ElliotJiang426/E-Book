package com.bookstore.demo4spring.serviceimpl;

import com.bookstore.demo4spring.dao.BookDao;
import com.bookstore.demo4spring.entity.Book;
import com.bookstore.demo4spring.entity.BriefBook;
import com.bookstore.demo4spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public List<BriefBook> getBriefBooks() {return bookDao.getBriefBooks();}

    @Override
    public List<Book> findByNameLike(String name) { return bookDao.findOneByNameLike(name);}

    @Override
    public Book deleteBook(int id) { return bookDao.deleteBook(id);}

    @Override
    public Book updateBook(int bookId, String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories){
        return bookDao.updateBook(bookId, cover, bookName, author, press, isbn, price, rate, stock, description, categories);
    }

    @Override
    public Book addBook(String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories){
        return bookDao.addBook(cover, bookName, author, press, isbn, price, rate, stock, description, categories);
    }
}
