package com.example.mywiki.controller;

import com.example.mywiki.request.UserLoginReq;
import com.example.mywiki.request.UserQueryReq;
import com.example.mywiki.request.UserResetPasswordReq;
import com.example.mywiki.request.UserSaveReq;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.response.UserLoginResp;
import com.example.mywiki.response.UserQueryResp;
import com.example.mywiki.service.UserService;
import com.example.mywiki.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;

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
        //对密码做md5加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp commonResp = new CommonResp<>();
        userService.save(req);
        return commonResp;
    }

    /**
     * 重置密码
     * @param passwordReq
     * @return
     */
    @PostMapping("/resetPassword")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq passwordReq){
        //对密码做md5加密
        passwordReq.setPassword(DigestUtils.md5DigestAsHex(passwordReq.getPassword().getBytes()));
        CommonResp commonResp = new CommonResp<>();
        userService.resetPassword(passwordReq);
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

    /**
     * 登录login接口
     * @param
     * @return
     */
    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq userLoginReq){
        userLoginReq.setPassword(DigestUtils.md5DigestAsHex(userLoginReq.getPassword().getBytes()));
        CommonResp<Object> commonResp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(userLoginReq);
        Long token = snowFlake.nextId();
        userLoginResp.setToken(token.toString());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(token,userLoginResp,24, TimeUnit.HOURS);
        log.info("生成了token：{}, 并放入redis", token);
        commonResp.setContent(userLoginResp);
        return commonResp;
    }

    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        CommonResp resp = new CommonResp<>();
        redisTemplate.delete(token);
        log.info("从redis中删除token: {}", token);
        return resp;
    }


}
