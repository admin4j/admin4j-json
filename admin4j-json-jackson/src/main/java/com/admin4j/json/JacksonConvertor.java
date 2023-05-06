package com.admin4j.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/agonie201218/article/details/125966380
 *
 * @author andanyang
 * @since 2023/5/5 13:47
 */
@Data
public class JacksonConvertor implements JSONConvertor {


    TypeReference<Map> mapTypeRef = new TypeReference<Map>() {
    };
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 解析/发序列化成对象
     *
     * @param is
     * @param charset
     * @param clazz
     */
    @Override
    public <T> T parseObject(InputStream is, Charset charset, Class<T> clazz) {

        try {
            return mapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析/发序列化成对象
     *
     * @param bytes
     * @param charset
     * @param clazz
     */
    @Override
    public <T> T parseObject(byte[] bytes, Charset charset, Class<T> clazz) {
        try {
            return mapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析/发序列化成对象
     *
     * @param input
     * @param clazz
     */
    @Override
    public <T> T parseObject(String input, Class<T> clazz) {
        try {
            return mapper.readValue(input, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JSON 转 Map
     *
     * @param input
     */
    @Override
    public Map<String, Object> parseMap(String input) {
        try {
            return mapper.readValue(input, mapTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> parseMap(InputStream is) {
        try {

            return mapper.readValue(is, mapTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> parseList(String input, Class<T> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            return mapper.readValue(input, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> parseList(InputStream is, Class<T> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            return mapper.readValue(is, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化成json字符串
     *
     * @param object
     */
    @Override
    public String toJSONString(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] serialize(Object object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
