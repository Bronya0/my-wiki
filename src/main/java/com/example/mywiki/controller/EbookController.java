package com.example.mywiki.controller;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(Long id){
        CommonResp<Ebook> response = new CommonResp<>();
        Ebook list = ebookService.list(id);
        response.setContent(list);
        return response;
    }

}
