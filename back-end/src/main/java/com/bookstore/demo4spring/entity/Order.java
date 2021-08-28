package com.bookstore.demo4spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderId")
public class Order {
    private int orderId;
    private Timestamp orderTime;

    public Order(){};

    @Id
    @Column(name = "order_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getOrderId() { return orderId;}

    public void setOrderId(int id) { this.orderId = id;}

    @Basic
    @Column(name = "order_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Timestamp getOrderTime() {return orderTime;}

    public void setOrderTime(Timestamp orderTime) {this.orderTime = orderTime;}

    private Receiver receiver;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "receiver_id", referencedColumnName = "receiver_id")
    public Receiver getReceiver() { return receiver;}

    public void setReceiver(Receiver receiver) { this.receiver = receiver;}

    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToMany(mappedBy = "myOrder",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<OrderItem> getOrderItemList() { return orderItemList;}

    public void setOrderItemList(List<OrderItem> orderItemList) { this.orderItemList = orderItemList;}

    private User user;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JSONField(serialize = false)
    public User getUser() { return this.user;}

    public void setUser(User user) { this.user = user;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            sb.append("\r\n");
            for(Field field : fields){
                sb.append(field.getName().substring(field.getName().lastIndexOf(".")+1)+" : ")
                        .append(this.getClass().getMethod("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)).invoke(this))
                        .append("\r\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

}
