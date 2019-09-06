package com.each.bookserver.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.util
 * @Description: java类作用描述
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 11:11
 * @UpdateUser: zhangxu
 * Copyright: Copyright (c) 2019
 */
public class ObjectMapperUtil {
    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Include.ALWAYS  是序列化对像所有属性
     * Include.NON_NULL 只有不为null的字段才被序列化
     * Include.NON_EMPTY 如果为null或者 空字符串和空集合都不会被序列化
     */
    public static ObjectMapper getObjectMapper(){
        /** 将接收到的数据，转换为格式化的json，并序列化*/
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        /**通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化，Include.ALWAYS为默认*/
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        /**如果是空对象的时候,不抛异常,也就是对应的属性没有get方法*/
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        /**取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式*/
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    /**
     *Explain: 将传入的json，转换成对象
     * @author zhangxu
     * @date: 2019/7/31
     * @param: [json, c]
     * @return: java.lang.Object
     */
    public static Object readValue(String json,Class c) {

        try {
            return getObjectMapper().readValue(json,c);
        }catch (IOException e){
            return null;
        }
    }

    public static String writeAsString(Object o){
        try {
            return getObjectMapper().writeValueAsString(o);
        }catch (JsonProcessingException e){
            return e.getMessage();
        }
    }
}
