package com.example.mywiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangssst@qq.com on 2021/06/02
 */
@RestController
public class demo {
    @GetMapping("/demo")
    public String demo(){
        return "demo123";
    }
}
