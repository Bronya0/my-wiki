package com.example.mywiki.response;

import lombok.Data;

import java.util.List;

/**
 * 分页返回参数封装
 */
@Data
public class PageResp<T> {
    //总行数
    private long total;
    //返回的列表数据
    private List<T> list;

}