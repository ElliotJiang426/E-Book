package com.bookstore.demo4spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Demo4springApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo4springApplication.class, args);
    }

}
