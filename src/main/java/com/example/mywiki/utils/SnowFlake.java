package com.example.mywiki.utils;


import org.springframework.stereotype.Component;

/**
 * Twitter的分布式自增ID雪花算法
 * Created by tangssst@qq.com on 2021/06/13
 *
 * 0000000000 0 - 0000000000 0000000000 0000000000 0 - 00 0000000000 - 0000000000
 * 11位标识 -              31位时间戳 -                 12位服务序列号 -  10位顺序位
 * 注：
 * 1.生成ID缩短至16位
 * 2.31位精确到秒时间戳可使用68年，生成16位ID时保证使用60.5年
 * 3.12位服务序列号，可以支持部署4096个节点
 * 4.10位顺序位，每秒生成1024个ID
 * <p>
 * 16位极值               对应时间戳（1365367696）（2013-04-08 04:48:16）   对应数据中心ID    对应顺序位
 * 最大值：9007199254740991         2147483647（2081-04-26 08:02:23）             4095          1023
 * 16位值：1000000000000000          238418579（2020-10-27 16:11:15）              416             0
 * 最小值：         4195329                  1（2013-04-08 04:48:17）               1             1
 * <p>
 * 1111111111111111111111111111111 111111111111 1111111111
 * 0001110001101011111101010010011 000110100000 0000000000
 * 0000000000000000000000000000001 000000000001 0000000001
 *
 */
@Component
public class SnowFlake {

    /**
     * 服务序列号
     */
    private long serviceSequence = 1L;

    /**
     * 时间戳开始计时时间 2013-04-08 04:48:16
     */
    private final long startTimestamp = 1365367696L;

    /**
     * 默认顺序位的初始值
     */
    private long sequence = 1L;
    /**
     * 顺序位2进制长度
     */
    private final long sequenceBits = 10L;

    /**
     * 服务序列号2进制长度
     */
    private final long serviceSequenceBits = 12L;


    /**
     * 服务序列号右移位数
     */
    private final long serviceSequenceShift = sequenceBits;

    /**
     * 时间戳右移位数
     */
    private final long timestampShift = sequenceBits + serviceSequenceBits;

    /**
     * 生成序列的掩码(10位所对应的最大整数值)，这里为1023 (0b1111111111=1023)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public SnowFlake() {
    }

    /**
     * 生成唯一的趋势递增的id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1L) & sequenceMask;
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - startTimestamp) << timestampShift) | (serviceSequence << serviceSequenceShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis() / 1000L;
    }
}
