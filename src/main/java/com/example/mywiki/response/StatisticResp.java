package com.example.mywiki.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 电子书快照返回实体
 * Created by tangssst@qq.com on 2021/07/17
 */
@Data
public class StatisticResp {

    //格式化日期，避免显示太长
    @JsonFormat(pattern="MM-dd", timezone = "GMT+8")
    private Date date;

    private int viewCount;

    private int voteCount;

    private int viewIncrease;

    private int voteIncrease;

}

