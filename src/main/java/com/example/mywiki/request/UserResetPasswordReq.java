package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by tangssst@qq.com on 2021/07/03
 */
@Data
public class UserResetPasswordReq {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
}
