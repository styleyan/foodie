package com.iswn.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaofei.yan
 * @Create 2020-08-25 13:47
 * @Descript bean和map相互转换工具
 */
public class BeanUtils {
    /**
     * 将 bean 转换为 map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T>Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();

        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将Map转换为 javaBean 对象
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
