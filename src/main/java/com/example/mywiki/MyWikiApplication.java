package com.example.mywiki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.example")
@MapperScan("com.example.mywiki.mapper")
@EnableScheduling
public class MyWikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWikiApplication.class, args);
    }

}
