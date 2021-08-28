package com.bookstore.demo4spring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
public class UserAuth {
    @Id
    @Column(name = "user_id")
    private int userId;
    private String username;
    private String password;
    private int userType;

    public UserAuth(){};

    public int getUserId() { return this.userId;}

    public void setUserId(int userId) { this.userId = userId;}

    @OneToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() { return user;}

    public void setUser(User user) { this.user = user;}

    @Basic
    @Column(name = "username")
    public String getUsername() { return username;}

    public void setUsername(String username) {this.username = username;}

    @Basic
    @Column(name = "password")
    public String getPassword() { return password;}

    public void setPassword(String password) {this.password = password;}

    @Basic
    @Column(name = "user_type")
    public int getUserType() { return userType;}

    public void setUserType(int userType) {this.userType = userType;}

}
