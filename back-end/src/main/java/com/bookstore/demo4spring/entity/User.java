package com.bookstore.demo4spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class User {
    private int userId;
    private String nickname;
    private String name;
    private String tel;
    private String address;
    private double balance;
    private String email;

    public User(){};

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getUserId() { return userId;}

    public void setUserId(int id) { this.userId = id;}

    @Basic
    @Column(name = "nickname")
    public String getNickname() { return nickname;}

    public void setNickname(String nickname) { this.nickname = nickname;}

    @Basic
    @Column(name = "balance")
    public double getBalance() { return balance;}

    public void setBalance(double balance) { this.balance = balance;}

    @Basic
    @Column(name = "name")
    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    @Basic
    @Column(name = "tel")
    public String getTel() { return tel;}

    public void setTel(String tel) { this.tel = tel;}

    @Basic
    @Column(name = "address")
    public String getAddress() { return address;}

    public void setAddress(String address) { this.address = address;}

    @Basic
    @Column(name = "email")
    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    private List<Order> orderList;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {this.orderList = orderList;}

    private List<Receiver> receivers;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<Receiver> getReceivers() { return  receivers;}

    public void setReceivers(List<Receiver> receivers) { this.receivers = receivers;}

    private UserAuth userAuth;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @JSONField(serialize = false)
    public UserAuth getUserAuth() { return userAuth;}

    public void setUserAuth(UserAuth userAuth) { this.userAuth = userAuth;}

    private List<BriefBook> likeBookList = new ArrayList<BriefBook>();

    @ManyToMany
    @JoinTable(name = "user_likes",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    public List<BriefBook> getLikeBookList() {
        return likeBookList;
    }

    public void setLikeBookList(List<BriefBook> likeBookList){ this.likeBookList = likeBookList;}

    private UserAvatar avatar;
    @Transient
    public UserAvatar getAvatar(){
        return this.avatar;
    }

    public void setAvatar(UserAvatar avatar){
        this.avatar = avatar;
    }
}

