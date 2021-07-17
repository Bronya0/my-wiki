package com.example.mywiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywiki.domain.EbookSnapshot;
import com.example.mywiki.response.StatisticResp;

import java.util.List;

/**
 * 快照收集定时器mapper接口
 * @Entity com.example.mywiki.domain.EbookSnapshot
 */
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {

    public void genSnapshot();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();

}




