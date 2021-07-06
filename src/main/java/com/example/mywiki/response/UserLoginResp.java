package com.example.mywiki.response;

import lombok.Data;

/**
 * 用户登录输出，保留id、登录名、昵称
 * Created by tangssst@qq.com on 2021/07/06
 */
@Data
public class UserLoginResp {

    private Long id;

    private String loginName;

    private String name;

}
