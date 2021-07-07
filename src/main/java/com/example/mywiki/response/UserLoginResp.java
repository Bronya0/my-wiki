package com.example.mywiki.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录输出，保留id、登录名、昵称
 * Created by tangssst@qq.com on 2021/07/06
 */
@Data
public class UserLoginResp implements Serializable {

    private Long id;

    private String token;

    private String loginName;

    private String name;


}
