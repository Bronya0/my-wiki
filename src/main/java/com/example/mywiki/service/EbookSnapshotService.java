package com.example.mywiki.service;

import com.example.mywiki.mapper.EbookSnapshotMapper;
import com.example.mywiki.response.StatisticResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tangssst@qq.com on 2021/07/17
 */
@Service
public class EbookSnapshotService {

    @Resource
    private EbookSnapshotMapper ebookSnapshotMapper;

    public void genSnapshot() {
        ebookSnapshotMapper.genSnapshot();
    }

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
    public List<StatisticResp> getStatistic() {
        return ebookSnapshotMapper.getStatistic();
    }

    /**
     * 30天数值统计
     */
    public List<StatisticResp> get30Statistic() {
        return ebookSnapshotMapper.get30Statistic();
    }

}

