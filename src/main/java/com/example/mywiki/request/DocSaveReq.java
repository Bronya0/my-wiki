package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "电子书id不能为空")
    private Long ebookId;

    /**
     * 父文档id
     */
    @NotNull(message = "父文档id不能为空")
    private Long parent;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;

    /**
     * 文档内容html字符串
     */
    @NotNull(message = "内容不能为空")
    private String content;

    /**
     * 顺序
     */
    @NotNull(message = "顺序不能为空")
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