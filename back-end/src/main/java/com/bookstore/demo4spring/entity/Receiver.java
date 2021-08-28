package com.bookstore.demo4spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "receivers")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "receiverId")
public class Receiver {
    private int receiverId;
    private String name;
    private String tel;
    private String address;

    public Receiver(){};

    @Id
    @Column(name = "receiver_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getReceiverId() { return receiverId;}

    public void setReceiverId(int receiverId) { this.receiverId = receiverId;}

    @Column(name = "name")
    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    @Column(name = "tel")
    public String getTel() { return tel;}

    public void setTel(String tel) { this.tel = tel;}

    @Column(name = "address")
    public String getAddress() { return address;}

    public void setAddress(String address) { this.address = address;}

    private User user;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JSONField(serialize = false)
    public User getUser() { return this.user;}

    public void setUser(User user) { this.user = user;}
}
