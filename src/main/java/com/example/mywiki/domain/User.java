package com.example.mywiki.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录名
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

    private static final long serialVersionUID = 1L;
}