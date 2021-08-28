package com.bookstore.demo4spring.entity;

import java.io.Serializable;

public class Cart_key implements Serializable {
    private int userId;
    private int bookId;

    public Cart_key(){};

    public int getUserId() {return this.userId;}

    public void setUserId(int userId) { this.userId = userId;}

    public int getBookId() { return this.bookId;}

    public void setBookId(int bookId) { this.bookId = bookId;}
}
