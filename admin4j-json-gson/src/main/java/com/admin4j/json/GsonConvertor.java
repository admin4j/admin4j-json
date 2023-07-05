package com.admin4j.json;

import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author andanyang
 * @since 2023/5/5 14:22
 */
@Data
public class GsonConvertor implements JSONConvertor {

    private Gson gson = new Gson();

    /**
     * 解析/发序列化成对象
     */
    @Override
    public <T> T parseObject(InputStream is, Charset charset, Class<T> clazz) {

        InputStreamReader inputStreamReader = new InputStreamReader(is, charset);
        return gson.fromJson(inputStreamReader, clazz);
    }

    /**
     * 解析/发序列化成对象
     */
    @Override
    public <T> T parseObject(byte[] bytes, Charset charset, Class<T> clazz) {

        String s = new String(bytes, charset);
        return parseObject(s, clazz);
    }

    /**
     * 解析/发序列化成对象
     */
    @Override
    public <T> T parseObject(String input, Class<T> clazz) {
        return gson.fromJson(input, clazz);
    }

    /**
     * JSON 转 Map
     */
    @Override
    public Map<String, Object> parseMap(String input) {
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(input, type);
    }

    @Override
    public Map<String, Object> parseMap(InputStream is) {
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        return gson.fromJson(inputStreamReader, type);
    }

    @Override
    public <T> List<T> parseList(String input, Class<T> clazz) {
        TypeToken<?> parameterized = TypeToken.getParameterized(List.class, clazz);
        return gson.fromJson(input, parameterized.getType());
    }

    @Override
    public <T> List<T> parseList(InputStream is, Class<T> clazz) {

        TypeToken<?> parameterized = TypeToken.getParameterized(List.class, clazz);
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        return gson.fromJson(inputStreamReader, parameterized.getType());
    }

    /**
     * 序列化成json字符串
     */
    @Override
    public String toJSONString(Object object) {

        return gson.toJson(object);
    }

    @Override
    public byte[] serialize(Object object) {
        return toJSONString(object).getBytes();
    }


    @Override
    public JSONMapper parseMapper(InputStream is) {
        JsonObject json = this.gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), JsonObject.class);
        return json == null ? null : new GsonJSONMapper(this.gson, json);
    }

    @Override
    public JSONMapper parseMapper(String input) {
        JsonObject json = this.gson.fromJson(input, JsonObject.class);
        return json == null ? null : new GsonJSONMapper(this.gson, json);
    }

    @Override
    public List<JSONMapper> parseJSONMappers(String input) {

        JsonArray list = gson.fromJson(input, JsonArray.class);
        List<JSONMapper> jsonMappers = new ArrayList<>(list.size());
        while (list.iterator().hasNext()) {
            JsonElement next = list.iterator().next();
            jsonMappers.add(new GsonJSONMapper(gson, (JsonObject) next));
        }

        return jsonMappers;
    }

    @Override
    public List<JSONMapper> parseJSONMappers(InputStream is) {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        JsonArray list = gson.fromJson(inputStreamReader, JsonArray.class);
        List<JSONMapper> jsonMappers = new ArrayList<>(list.size());
        while (list.iterator().hasNext()) {
            JsonElement next = list.iterator().next();
            jsonMappers.add(new GsonJSONMapper(gson, (JsonObject) next));
        }

        return jsonMappers;
    }


    @Override
    public JSONArrayMapper parseArrayMapper(String input) {
        JsonArray list = gson.fromJson(input, JsonArray.class);
        return list == null ? null : new GsonArrayMapper(gson, list);
    }

    @Override
    public JSONArrayMapper parseArrayMapper(InputStream is) {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        JsonArray list = gson.fromJson(inputStreamReader, JsonArray.class);

        return list == null ? null : new GsonArrayMapper(gson, list);
    }
}
