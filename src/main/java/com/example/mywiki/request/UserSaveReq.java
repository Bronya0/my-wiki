package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "登录名不能为空")
    private String loginName;

    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空")
    private String name;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【密码】至少包含 数字和英文，长度6-32")
    private String password;

}