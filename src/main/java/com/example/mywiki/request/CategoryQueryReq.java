package com.example.mywiki.request;

import lombok.Data;

/**
 * Created by tangssst@qq.com on 2021/06/15
 */
@Data
public class CategoryQueryReq extends PageReq{
    private Long id;

    private Long parent;

    private String name;


}
