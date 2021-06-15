package com.example.mywiki.controller;

import com.example.mywiki.request.CategoryQueryReq;
import com.example.mywiki.request.CategorySaveReq;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.response.CategoryQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 查询
     * @param req
     * @return
     */
    @GetMapping("/search")
    public CommonResp search(@Valid CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> commonResp = new CommonResp<>();
        PageResp<CategoryQueryResp> category = categoryService.search(req);
        commonResp.setContent(category);
        return commonResp;
    }


    /**
     * 电子书新增/保存
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req){
        CommonResp commonResp = new CommonResp<>();
        categoryService.save(req);
        return commonResp;
    }

    /**
     * 电子书删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp commonResp = new CommonResp<>();
        categoryService.delete(id);
        return commonResp;
    }

}
