package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Ebook实体类，id、name、category1Id、category2Id、description、cover、docCount、viewCount、voteCount
 */
@Data
public class UserSaveReq {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录名
     */
    @NotEmpty(message = "登录名不能为空")
    private String loginName;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String name;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

}