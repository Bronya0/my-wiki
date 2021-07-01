package com.example.mywiki.response;

import lombok.Data;

/**
 * user查询返回对象
 */
@Data
public class UserQueryResp {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}