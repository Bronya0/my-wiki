package com.example.mywiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 电子书快照表
 * @TableName ebook_snapshot
 */
@TableName(value ="ebook_snapshot")
@Data
public class EbookSnapshot implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 电子书id
     */
    private Long ebookId;

    /**
     * 快照日期
     */
    private Date date;

    /**
     * 阅读量
     */
    private Integer viewCount;

    /**
     * 点赞量
     */
    private Integer voteCount;

    /**
     * 阅读增长
     */
    private Integer viewIncrease;

    /**
     * 点赞增长
     */
    private Integer voteIncrease;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}