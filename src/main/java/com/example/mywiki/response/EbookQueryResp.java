package com.example.mywiki.response;

import lombok.Data;

/**
 * Ebook查询返回对象
 */
@Data
public class EbookQueryResp {

    private Long id;


    private String name;


    private Long category1Id;


    private Long category2Id;


    private String description;


    private String cover;


    private Integer docCount;


    private Integer viewCount;


    private Integer voteCount;

}