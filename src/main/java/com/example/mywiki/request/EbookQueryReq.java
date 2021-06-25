package com.example.mywiki.request;

import lombok.Data;

/**
 * 请求查询参数封装
 */
@Data
public class EbookQueryReq extends PageReq{

    private Long id;

    //查二级分类用
    private Long category2Id;

    private String name;


}