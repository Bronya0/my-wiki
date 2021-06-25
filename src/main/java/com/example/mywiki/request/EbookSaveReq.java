package com.example.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Ebook实体类，id、name、category1Id、category2Id、description、cover、docCount、viewCount、voteCount
 */
@Data
public class EbookSaveReq {

    private Long id;

    @NotNull(message = "名称不能为空")
    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;


}