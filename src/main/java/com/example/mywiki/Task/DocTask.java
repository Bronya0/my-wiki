package com.example.mywiki.Task;

import com.example.mywiki.service.DocService;
import com.example.mywiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 电子书信息更新定时器
 * Created by tangssst@qq.com on 2021/07/11
 */
@Component
public class DocTask {

    private static final Logger LOG = LoggerFactory.getLogger(DocTask.class);

    @Resource
    private DocService docService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 每30秒更新电子书信息
     */
    @Scheduled(cron = "0 0 6/1 * * ?")
    public void cron() {
        // 增加日志流水号
//        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }

}
