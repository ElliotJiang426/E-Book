package com.bookstore.demo4spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "bookstore")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId")
public class BriefBook {
    private int bookId;
    private String bookName;
    private String author;
    private String categories;
    private String press;
    private double price;
    private double rate;
    private BookCover cover;

    public BriefBook() {}

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getBookId() { return bookId;}

    public void setBookId(int id) { this.bookId = id;}

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

    private List<User> userLikeList = new ArrayList<User>();

    @JsonIgnore
    @ManyToMany(mappedBy = "likeBookList")
    @JSONField(serialize = false)
    public List<User> getUserLikeList() { return this.userLikeList;}

    public void setUserLikeList(List<User> userLikeList) { this.userLikeList = userLikeList;}

//    List<OrderItem> orderItemList;

//    @JsonBackReference
//    @OneToMany(mappedBy = "book")
//    public List<OrderItem> getOrderItemList() { return orderItemList;}
//
//    public void setOrderItemList(List<OrderItem> orderItemList) { this.orderItemList = orderItemList;}

    @Override
    public String toString() {
        return "bookId:"+bookId+" bookName:"+bookName;
    }

    @Transient
    public BookCover getCover(){
        return this.cover;
    }

    public void setCover(BookCover cover){
        this.cover = cover;
    }
}
