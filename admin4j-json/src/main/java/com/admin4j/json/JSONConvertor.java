package com.admin4j.json;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * JSON 序列化器
 *
 * @author andanyang
 * @since 2023/5/5 12:21
 */
public interface JSONConvertor extends Prioritized {

    /**
     * 解析/发序列化成对象
     */
    <T> T parseObject(InputStream is, Charset charset, Class<T> clazz);

    default <T> T parseObject(InputStream is, Class<T> clazz) {
        return parseObject(is, StandardCharsets.UTF_8, clazz);
    }

    /**
     * 解析/发序列化成对象
     */
    <T> T parseObject(byte[] bytes, Charset charset, Class<T> clazz);

    default <T> T parseObject(byte[] bytes, Class<T> clazz) {
        return parseObject(bytes, StandardCharsets.UTF_8, clazz);
    }

    /**
     * 解析/发序列化成对象
     */
    <T> T parseObject(String input, Class<T> clazz);

    /**
     * JSON 转 Map
     */
    Map<String, Object> parseMap(String input);

    Map<String, Object> parseMap(InputStream is);

    <T> List<T> parseList(String input, Class<T> clazz);

    <T> List<T> parseList(InputStream is, Class<T> clazz);

    /**
     * 序列化成json字符串
     *
     * @param object
     */
    String toJSONString(Object object);

    byte[] serialize(Object object);
}
