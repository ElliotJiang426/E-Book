package com.bookstore.demo4spring.repository;

import com.bookstore.demo4spring.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverRepository extends JpaRepository<Receiver, Integer> {

}
