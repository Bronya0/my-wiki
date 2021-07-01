package com.example.mywiki.request;

import lombok.Data;

/**
 * 请求查询参数封装
 */
@Data
public class UserQueryReq extends PageReq{

    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录名
     */
    private String loginName;


}