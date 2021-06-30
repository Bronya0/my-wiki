package com.example.mywiki.controller;

import com.example.mywiki.request.DocQueryReq;
import com.example.mywiki.request.DocSaveReq;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.response.DocQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;

    /**
     * 查询
     * @param req
     * @return
     */
    @GetMapping("/search")
    public CommonResp search(@Valid DocQueryReq req){
        CommonResp<PageResp<DocQueryResp>> commonResp = new CommonResp<>();
        PageResp<DocQueryResp> doc = docService.search(req);
        commonResp.setContent(doc);
        return commonResp;
    }

    /**
     * 查询文本内容
     * @param id
     * @return
     */
    @GetMapping("/getContent/{id}")
    public CommonResp getContent(@PathVariable Long id){
        CommonResp<String> commonResp = new CommonResp<>();
        String content = docService.getContent(id);
        commonResp.setContent(content);
        return commonResp;
    }


    /**
     * 电子书新增/保存
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req){
        CommonResp commonResp = new CommonResp<>();
        docService.save(req);
        return commonResp;
    }

    /**
     * 电子书删除
     *
     * 注解@PathVariable: 接收请求路径中占位符的值
     *
     * @param idsStr
     * @return
     */
    @DeleteMapping("/delete/{idsStr}")
    public CommonResp delete(@PathVariable String idsStr){
        CommonResp commonResp = new CommonResp<>();
        List<String> list = Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return commonResp;
    }

    /**
     * 一次查全部分类，用search的话多了一次分页sql查询
     */
    @GetMapping("/all/{ebookId}")
    public CommonResp all(@PathVariable Long ebookId){
        CommonResp commonResp = new CommonResp<>();
        List<DocQueryResp> doc = docService.all(ebookId);
        commonResp.setContent(doc);
        return commonResp;
    }

}
