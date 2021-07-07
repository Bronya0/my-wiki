package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 文档
 * @TableName doc
 */
@Data
public class DocSaveReq implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 电子书id
     */
    @NotEmpty(message = "电子书id不能为空")
    private Long ebookId;

    /**
     * 父文档id
     */
    @NotEmpty(message = "父文档id不能为空")
    private Long parent;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 文档内容html字符串
     */
    @NotEmpty(message = "内容不能为空")
    private String content;

    /**
     * 顺序
     */
    @NotEmpty(message = "顺序不能为空")
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