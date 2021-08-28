package com.bookstore.demo4spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "order_items")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "itemId")
public class OrderItem {
    private int itemId;
    private int orderId;
    private int bookId;
    private int quantity;
    private double price;

    public OrderItem(){};

    @Id
    @Column(name = "item_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getItemId() { return itemId;}

    public void setItemId(int id) { this.itemId = id;}

    @Basic
    @Column(name = "order_id",insertable=false,updatable=false)
    public int getOrderId() { return orderId;}

    public void setOrderId(int id) { this.orderId = id;}

    @Basic
    @Column(name = "book_id",insertable=false,updatable=false)
    public int getBookId() { return bookId;}

    public void setBookId(int id) { this.bookId = id;}

    @Basic
    @Column(name = "quantity")
    public int getQuantity() { return quantity;}

    public void setQuantity(int quantity) { this.quantity = quantity;}

    @Basic
    @Column(name = "price")
    public double getPrice() { return price;}

    public void setPrice(double price) { this.price = price;}


    private Order myOrder;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "order_id")
    @JSONField(serialize = false)
    public Order getMyOrder() {
        return myOrder;
    }

    public void setMyOrder(Order myOrder) {
        this.myOrder = myOrder;
    }



    private BriefBook book;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch=FetchType.EAGER)
    @JoinColumn(name = "book_id")
    public BriefBook getBook() { return book;}

    public void setBook(BriefBook book) { this.book = book;}

//    @Override
//    public String toString() {
//        return "orderId:"+orderId+" book:"+book;
//    }
}


