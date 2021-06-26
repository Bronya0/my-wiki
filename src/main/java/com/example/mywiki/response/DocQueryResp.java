package com.example.mywiki.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 文档
 * @TableName doc
 */
@Data
public class DocQueryResp implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 电子书id
     */
    private Long ebookId;

    /**
     * 父id
     */
    private Long parent;

    /**
     * 名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 阅读数
     */
    private Integer viwCount;

    /**
     * 点赞数
     */
    private Integer voteCount;

    private static final long serialVersionUID = 1L;
}