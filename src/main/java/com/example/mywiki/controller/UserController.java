package com.example.mywiki.controller;

import com.example.mywiki.request.UserQueryReq;
import com.example.mywiki.request.UserSaveReq;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.response.UserQueryResp;
import com.example.mywiki.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询
     * @param req
     * @return
     */
    @GetMapping("/search")
    public CommonResp search(@Valid UserQueryReq req){
        CommonResp<PageResp<UserQueryResp>> commonResp = new CommonResp<>();
        PageResp<UserQueryResp> user = userService.search(req);
        commonResp.setContent(user);
        return commonResp;
    }


    /**
     * 用户新增/保存
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req){
        CommonResp commonResp = new CommonResp<>();
        userService.save(req);
        return commonResp;
    }

    /**
     * 用户删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp commonResp = new CommonResp<>();
        userService.delete(id);
        return commonResp;
    }



}
