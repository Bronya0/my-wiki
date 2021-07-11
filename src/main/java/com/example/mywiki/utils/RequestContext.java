package com.example.mywiki.utils;

import java.io.Serializable;

/**
 * 存放用户远程ip地址
 * Created by tangssst@qq.com on 2021/07/11
 */
public class RequestContext implements Serializable {

    //远程ip。使用线程的本地变量，只在当前线程有效，线程间不会互相干扰
    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return remoteAddr.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }

}
