package com.example.mywiki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
@MapperScan("com.example.mywiki.mapper")
public class MyWikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWikiApplication.class, args);
    }

}
