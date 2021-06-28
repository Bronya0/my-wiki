package com.example.mywiki.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 文档内容
 * @TableName content
 */
@Data
public class Content implements Serializable {
    /**
     * 文档id
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}