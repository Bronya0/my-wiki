package com.example.mywiki.mq;

//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * rocketmq消费端
// * Created by tangssst@qq.com on 2021/07/14
// */
// @Service
// @RocketMQMessageListener(consumerGroup = "default", topic = "VOTE_TOPIC")
// public class VoteTopicConsumer implements RocketMQListener<MessageExt> {
//
//     private static final Logger LOG = LoggerFactory.getLogger(VoteTopicConsumer.class);
//
//     @Resource
//     public WebSocketServer webSocketServer;
//
//     @Override
//     public void onMessage(MessageExt messageExt) {
//         byte[] body = messageExt.getBody();
//         LOG.info("ROCKETMQ收到消息：{}", new String(body));
//         webSocketServer.sendInfo(new String(body));
//     }
// }

