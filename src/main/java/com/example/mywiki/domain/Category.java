package com.example.mywiki.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类
 * @TableName category
 */
@Data
public class Category implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 父id
     */
    private Long parent;

    /**
     * 名字
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;
}