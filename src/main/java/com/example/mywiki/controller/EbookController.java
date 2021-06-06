package com.example.mywiki.controller;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
        CommonResp<Ebook> commonResp = new CommonResp<>();
        //通用返回对象
        Ebook list = ebookService.list(id);
        commonResp.setContent(list);
        return commonResp;
    }

    @GetMapping("/search")
    public CommonResp search(String name){
        CommonResp<List<Ebook>> commonResp = new CommonResp<>();
        List<Ebook> ebook = ebookService.search(name);
        commonResp.setContent(ebook);
        return commonResp;
    }

}
