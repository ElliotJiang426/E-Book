package com.bookstore.demo4spring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "bookstore")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId")
public class Book {
    private int bookId;
    private String isbn;
    private String bookName;
    private String author;
    private String categories;
    private String press;
    private double price;
    private double rate;
    private int stock;
    private String description;
    private BookCover cover;

    public Book() {}

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getBookId() { return bookId;}

    public void setBookId(int id) { this.bookId = id;}

    @Basic
    @Column(name = "isbn")
    public String getIsbn() { return isbn;}

    public void setIsbn(String isbn) {this.isbn = isbn;}

    @Basic
    @Column(name = "name")
    public String getBookName() { return bookName;}

    public void setBookName(String name) {this.bookName = name;}

    @Basic
    @Column(name = "author")
    public String getAuthor() { return author;}

    public void setAuthor(String author) {this.author = author;}

    @Basic
    @Column(name = "categories")
    public String getCategories() { return categories;}

    public void setCategories(String categories) { this.categories =categories;}

    @Basic
    @Column(name = "press")
    public String getPress() { return press;}

    public void setPress(String press) { this.press = press;}

    @Basic
    @Column(name = "price")
    public double getPrice() { return price;}

    public void setPrice(double price) { this.price = price;}

    @Basic
    @Column(name = "rate")
    public double getRate() { return rate;}

    public void setRate(double rate) { this.rate = rate;}

    @Basic
    @Column(name = "description")
    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description;}

    @Basic
    @Column(name = "stock")
    public int getStock() { return stock;}

    public void setStock(int stock) { this.stock = stock;}

    @Transient
    public BookCover getCover(){
        return this.cover;
    }

    public void setCover(BookCover cover){
        this.cover = cover;
    }
}
