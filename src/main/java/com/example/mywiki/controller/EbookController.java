package com.example.mywiki.controller;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.request.EbookReq;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.response.EbookResp;
import com.example.mywiki.response.PageResp;
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
    public CommonResp list(EbookReq req){
        CommonResp<Ebook> commonResp = new CommonResp<>();
        //通用返回对象
        Ebook list = ebookService.list(req);
        commonResp.setContent(list);
        return commonResp;
    }

    @GetMapping("/search")
    public CommonResp search(EbookReq req){
        CommonResp<PageResp<EbookResp>> commonResp = new CommonResp<>();
        PageResp<EbookResp> ebook = ebookService.search(req);
        commonResp.setContent(ebook);
        return commonResp;
    }

}
