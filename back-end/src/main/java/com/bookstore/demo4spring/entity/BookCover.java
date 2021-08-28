package com.bookstore.demo4spring.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "books")
public class BookCover {
    @Id
    private int id;
    private String cover;

    public BookCover(int id, String cover){
        this.id = id;
        this.cover = cover;
    }

    public int getBookId() {
        return id;
    }

    public void setBookId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
