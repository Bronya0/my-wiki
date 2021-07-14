package com.example.mywiki.service;

import com.example.mywiki.WebSocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tangssst@qq.com on 2021/07/13
 */
@Service
public class WebSocketService {

    @Resource
    public WebSocketServer webSocketServer;

    /**
     * 推送消息
     * @param message
     * @param logId
     */
    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);
    }
}
