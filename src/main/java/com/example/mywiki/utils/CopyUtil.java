package com.example.mywiki.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性复制工具类
 * Created by tangssst@qq.com on 2021/06/06
 */
public class CopyUtil {
    /**
     * 单体复制，参数为源对象和目标class，通过newInstance方法生成对象，并将源对象的属性复制到到新对象里，返回新对象。
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    /**
     * 列表复制，参数为源List和目标class，通过上面的单体复制将List里的每个对象复制到新对象里，都加进一个ArrayList里，并返回。
     */
    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }
}
