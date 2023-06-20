package com.admin4j.json;

import com.admin4j.json.mapper.JSONMapper;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * JSON 解析工具类
 *
 * @author andanyang
 * @since 2023/5/5 13:10
 */
public class JSONUtil {

    private static final JSONConvertor JSON_CONVERTOR = loadJSONConvertor();

    /**
     * 使用SPI 加载 JSON_CONVERTOR
     *
     * @return
     */
    private static JSONConvertor loadJSONConvertor() {
        return StreamSupport.stream(ServiceLoader.load(JSONConvertor.class).spliterator(), false)
                .sorted().findFirst().orElseGet(() -> {
                    throw new IllegalArgumentException("no JSON converter found");
                });
    }


    /**
     * 解析/发序列化成对象
     */
    public static <T> T parseObject(InputStream is, Charset charset, Class<T> clazz) {

        return JSON_CONVERTOR.parseObject(is, charset, clazz);
    }

    /**
     * 解析/发序列化成对象
     */
    public static <T> T parseObject(InputStream is, Class<T> clazz) {
        return parseObject(is, StandardCharsets.UTF_8, clazz);
    }

    /**
     * 解析/发序列化成对象
     */
    public static <T> T parseObject(byte[] bytes, Charset charset, Class<T> clazz) {
        return JSON_CONVERTOR.parseObject(bytes, charset, clazz);
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        return parseObject(bytes, StandardCharsets.UTF_8, clazz);
    }


    /**
     * 解析/发序列化成对象
     */
    public static <T> T parseObject(String input, Class<T> clazz) {
        return JSON_CONVERTOR.parseObject(input, clazz);
    }

    /**
     * JSON 转 Map
     */
    public static Map<String, Object> parseMap(String input) {
        return JSON_CONVERTOR.parseMap(input);
    }

    /**
     * 解析/发序列化成Map
     */
    public static Map<String, Object> parseMap(InputStream is) {
        return JSON_CONVERTOR.parseMap(is);
    }

    /**
     * 解析/发序列化成JSONMapper
     */
    public static JSONMapper parseMapper(String input) {
        return JSON_CONVERTOR.parseMapper(input);
    }

    /**
     * 解析/发序列化成JSONMapper
     */
    public static JSONMapper parseMapper(InputStream is) {
        return JSON_CONVERTOR.parseMapper(is);
    }

    /**
     * 解析/发序列化成List
     */
    public static <T> List<T> parseList(String input, Class<T> clazz) {
        return JSON_CONVERTOR.parseList(input, clazz);
    }

    public static <T> List<T> parseList(InputStream is, Class<T> clazz) {
        return JSON_CONVERTOR.parseList(is, clazz);
    }

    /**
     * 序列化成json字符串
     *
     * @param object
     */
    public static String toJSONString(Object object) {

        return JSON_CONVERTOR.toJSONString(object);
    }

    public static byte[] serialize(Object object) {
        return JSON_CONVERTOR.serialize(object);
    }
}
