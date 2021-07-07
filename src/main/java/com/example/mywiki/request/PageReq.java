package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 分页请求参数封装
 */
@Data
public class PageReq {

    //页码
    @NotNull(message = "页码不能为空")
    private int page;

    //条数，限制非空、最大数
    @NotNull(message = "条数不能为空")
    @Max(value = 1000,message = "最大查询数：1000")
    private int size;


}