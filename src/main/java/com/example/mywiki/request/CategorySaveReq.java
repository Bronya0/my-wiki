package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by tangssst@qq.com on 2021/06/15
 */
@Data
public class CategorySaveReq {

    private Long id;

    private Long parent;

    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(message = "顺序不能为空")
    private Integer sort;


}
