package com.example.mywiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
public class MyWikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWikiApplication.class, args);
    }

}
