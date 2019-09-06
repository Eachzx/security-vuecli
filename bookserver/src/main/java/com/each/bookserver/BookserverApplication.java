package com.each.bookserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.each.bookserver.mapper")
public class BookserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookserverApplication.class, args);
    }

}
