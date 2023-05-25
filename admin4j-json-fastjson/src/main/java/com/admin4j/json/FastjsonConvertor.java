package com.admin4j.json;


import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author andanyang
 * @since 2023/5/5 13:31
 */
public class FastjsonConvertor implements JSONConvertor {

    /**
     * 解析/发序列化成对象
     *
     * @param is
     * @param clazz
     * @param charset
     */
    @Override
    public <T> T parseObject(InputStream is, Charset charset, Class<T> clazz) {

        try {
            return JSON.parseObject(is, charset, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析/发序列化成对象
     *
     * @param bytes
     * @param clazz
     * @param charset
     */
    @Override
    public <T> T parseObject(byte[] bytes, Charset charset, Class<T> clazz) {

        return JSON.parseObject(bytes, clazz);

    }

    /**
     * 解析/发序列化成对象
     *
     * @param input
     * @param clazz
     */
    @Override
    public <T> T parseObject(String input, Class<T> clazz) {
        return JSON.parseObject(input, clazz);
    }


    /**
     * JSON 转 Map
     */
    @Override
    public Map<String, Object> parseMap(String input) {
        return JSON.parseObject(input);
    }

    @Override
    public Map<String, Object> parseMap(InputStream is) {
        try {
            return JSON.parseObject(is, StandardCharsets.UTF_8, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> parseList(String input, Class<T> clazz) {
        return JSON.parseArray(input, clazz);
    }

    @Override
    public <T> List<T> parseList(InputStream is, Class<T> clazz) {

        String str = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        return JSON.parseArray(str, clazz);
    }

    /**
     * 序列化成json字符串
     *
     * @param object
     */
    @Override
    public String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }


    @Override
    public JSONMapper parseMapper(InputStream is) {
        try {
            JSONObject jsonObject = JSON.parseObject(is, StandardCharsets.UTF_8, JSONObject.class);
            return new FastjsonJSONMapper(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONMapper parseMapper(String input) {
        return new FastjsonJSONMapper(JSON.parseObject(input));
    }

    @Override
    public List<JSONMapper> parseJSONMappers(String input) {
        JSONArray objects = JSON.parseArray(input);
        List<JSONMapper> jsonMappers = new ArrayList<>(objects.size());
        for (Object o : objects) {
            jsonMappers.add(new FastjsonJSONMapper((JSONObject) o));
        }
        return jsonMappers;
    }

    @Override
    public List<JSONMapper> parseJSONMappers(InputStream is) {
        String str = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        return parseJSONMappers(str);
    }

    @Override
    public JSONArrayMapper parseArrayMapper(String input) {
        JSONArray objects = JSON.parseArray(input);
        return new FastJSONArrayMapper(objects);
    }

    @Override
    public JSONArrayMapper parseArrayMapper(InputStream is) {
        String str = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        return parseArrayMapper(str);
    }
}
