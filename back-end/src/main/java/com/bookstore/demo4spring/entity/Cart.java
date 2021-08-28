package com.bookstore.demo4spring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@IdClass(Cart_key.class)
@Table(name = "cart")
public class Cart {
    @Id
    private int userId;
    @Id
    private int bookId;
    private int quantity;

    public Cart(){};
    public Cart(int userId, int bookId, int quantity) {
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() { return this.userId;}
    public void setUserId(int userId) { this.userId = userId;}

    @Id
    @Column(name = "book_id")
    public int getBookId() { return this.bookId;}
    public void setBookId(int bookId) { this.bookId = bookId;}

    @Basic
    @Column(name = "quantity")
    public int getQuantity() { return this.quantity;}

    public void setQuantity(int quantity) { this.quantity = quantity;}


//    private User user;
//
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
//    @JoinColumn(name = "user_id")
//    public User getUser() { return this.user;}
//
//    public void setUser(User user) { this.user = user;}
}
