package com.bookstore.demo4spring.daoimpl;

import com.bookstore.demo4spring.dao.BookDao;
import com.bookstore.demo4spring.entity.*;
import com.bookstore.demo4spring.repository.BookCoverRepository;
import com.bookstore.demo4spring.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCoverRepository bookCoverRepository;

    @Override
    public Book findOne(Integer id){
        Book b = bookRepository.getOne(id);
        Optional<BookCover> cover = bookCoverRepository.findById(id);

        if(cover.isPresent()) b.setCover(cover.get());
        else b.setCover(null);

        bookRepository.save(b);
        return b;
    }

    @Override
    public BriefBook findOneBriefBook(Integer id){
        BriefBook b = bookRepository.getBriefBook(id);
        Optional<BookCover> cover = bookCoverRepository.findById(id);

        if(cover.isPresent()) b.setCover(cover.get());
        else b.setCover(null);

        return b;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> bl = bookRepository.getBooks();
        return pairedWithCovers(bl);
    }

    @Override
    public List<BriefBook> getBriefBooks() {
        List<BriefBook> bl = bookRepository.getBriefBooks();
        for(BriefBook book : bl) {
            Optional<BookCover> cover = bookCoverRepository.findById(book.getBookId());
            if(cover.isPresent()) book.setCover(cover.get());
            else book.setCover(null);
        }

        return bl;
    }

    @Override
    public Book updateStock(Integer id, Integer quantity){
        Book b = bookRepository.getOne(id);
        int oldStock = b.getStock();
        b.setStock(oldStock+quantity);
        bookRepository.save(b);
        return b;
    }

    @Override
    public List<Book> findOneByNameLike(String name) {
        List<Book> bl = bookRepository.findByNameLike(name);
        return pairedWithCovers(bl);
    }

    public List<Book> pairedWithCovers(List<Book> bl){
        for(Book book : bl) {
            Optional<BookCover> cover = bookCoverRepository.findById(book.getBookId());
            if(cover.isPresent()) book.setCover(cover.get());
            else book.setCover(null);
        }
        return bl;
    }

    @Override
    public Book deleteBook(int id){
        Book b = bookRepository.getOne(id);
        bookRepository.delete(b);
        Optional<BookCover> bc = bookCoverRepository.findById(id);
        bc.ifPresent(bookCover -> bookCoverRepository.delete(bookCover));
        return b;
    }

    @Override
    public Book updateBook(int bookId, String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories){
        Book b = bookRepository.getOne(bookId);
        b.setBookName(bookName);
        b.setStock(stock);
        b.setAuthor(author);
        b.setPress(press);
        b.setPrice(price);
        b.setRate(rate);
        b.setDescription(description);
        b.setCategories(categories);
        b.setIsbn(Integer.toString(isbn));

        Optional<BookCover> bc = bookCoverRepository.findById(bookId);
        if(bc.isPresent()) b.setCover(bc.get());
        else b.setCover(null);

        bookRepository.save(b);
        return b;
    }

    @Override
    public Book addBook(String cover, String bookName, String author, String press, int isbn, double price, double rate, int stock, String description, String categories){
        Book b = new Book();
        b.setBookName(bookName);
        b.setAuthor(author);
        b.setPrice(price);
        b.setPress(press);
        b.setIsbn(Integer.toString(isbn));
        b.setRate(rate);
        b.setStock(stock);
        b.setCategories(categories);
        b.setDescription(description);
        bookRepository.save(b);

        BookCover bc = new BookCover(b.getBookId(), cover);
        bookCoverRepository.save(bc);

        b.setCover(bc);
        bookRepository.save(b);
        return b;
    }
}
