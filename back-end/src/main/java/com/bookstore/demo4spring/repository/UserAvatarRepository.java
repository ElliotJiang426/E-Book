package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.UserAvatar;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAvatarRepository extends MongoRepository<UserAvatar, Integer> {
}
