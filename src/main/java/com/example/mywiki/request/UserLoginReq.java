package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户登录，保留用户名、密码
 */
@Data
public class UserLoginReq {

    /**
     * 登录名
     */
    @NotEmpty(message = "登录名不能为空")
    // @Length(min = 6, max = 20, message = "【密码】6~20位")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【密码】不符合规则！")
    private String loginName;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

}