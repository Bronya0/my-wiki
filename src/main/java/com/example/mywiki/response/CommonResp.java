package com.example.mywiki.response;

import lombok.Data;

/**
 * 通用的返回对象
 * Created by tangssst@qq.com on 2021/06/04
 */
@Data
public class CommonResp<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 不确定的数据用泛型，类里也加，自定义类型
     */
    private T content;


}
