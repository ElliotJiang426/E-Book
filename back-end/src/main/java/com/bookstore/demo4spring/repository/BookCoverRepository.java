package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.BookCover;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookCoverRepository extends MongoRepository<BookCover, Integer> {
}
