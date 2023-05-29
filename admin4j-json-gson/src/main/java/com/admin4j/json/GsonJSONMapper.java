package com.admin4j.json;

import com.admin4j.json.mapper.JSONMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author andanyang
 * @since 2023/5/25 13:58
 */
@RequiredArgsConstructor
public class GsonJSONMapper implements JSONMapper {

    private final Gson gson;
    private final JsonObject json;

    /**
     * 获取原始对象
     */
    @Override
    public Object getOriginObject() {
        return json;
    }

    /**
     * 转化为JSON 字符串
     *
     * @return
     */
    @Override
    public String toJSONString() {
        return gson.toJson(json);
    }

    /**
     * 转化为java对象
     *
     * @param tClass
     */
    @Override
    public <T> T toJavaObject(Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    @Override
    public Boolean getBoolean(String key) {
        return json.has(key) ? json.get(key).getAsBoolean() : null;
    }

    @Override
    public boolean getBooleanValue(String key) {
        return json.get(key).getAsBoolean();
    }

    @Override
    public Integer getInteger(String key) {
        return json.has(key) ? json.get(key).getAsInt() : null;
    }

    @Override
    public int getIntValue(String key) {
        return json.get(key).getAsInt();
    }

    @Override
    public Long getLong(String key) {
        return json.has(key) ? json.get(key).getAsLong() : null;
    }

    @Override
    public long getLongValue(String key) {
        return json.get(key).getAsLong();
    }

    @Override
    public Float getFloat(String key) {
        return json.has(key) ? json.get(key).getAsFloat() : null;
    }

    @Override
    public float getFloatValue(String key) {
        return json.get(key).getAsFloat();
    }

    @Override
    public Double getDouble(String key) {
        return json.has(key) ? json.get(key).getAsDouble() : null;
    }

    @Override
    public double getDoubleValue(String key) {
        return json.get(key).getAsDouble();
    }

    @Override
    public String getString(String key) {
        return json.get(key).getAsString();
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return json.get(key).getAsBigDecimal();
    }

    @Override
    public boolean containsKey(String key) {
        return json.has(key);
    }

    @Override
    public Set<String> keySet() {
        return json.keySet();
    }

    /**
     * 获取改key的 子对象的JSONMapper值
     *
     * @param key
     */
    @Override
    public JSONMapper getJSONMapper(String key) {

        return json.has(key) ? new GsonJSONMapper(gson, (JsonObject) json.get(key)) : null;
    }

    /**
     * 获取改key的 子对象的Object值
     *
     * @param key
     * @param tClass
     */
    @Override
    public <T> T getObject(String key, Class<T> tClass) {
        return json.has(key) ? gson.fromJson(json.get(key), tClass) : null;
    }

    /**
     * 获取改key的 子对象的List值
     *
     * @param key
     * @param tClass
     */
    @Override
    public <T> List<T> getArray(String key, Class<T> tClass) {
        JsonArray asJsonArray = json.getAsJsonArray(key);
        List<T> result = new ArrayList<>(asJsonArray.size());
        for (JsonElement jsonElement : asJsonArray) {
            result.add(gson.fromJson(jsonElement, tClass));
        }
        return result;
    }

    /**
     * 获取改key的 子对象的List值
     *
     * @param key
     */
    @Override
    public List<JSONMapper> getMappers(String key) {
        JsonArray asJsonArray = json.getAsJsonArray(key);
        List<JSONMapper> result = new ArrayList<>(asJsonArray.size());
        for (JsonElement jsonElement : asJsonArray) {
            result.add(new GsonJSONMapper(gson, (JsonObject) jsonElement));
        }
        return result;
    }

    /**
     * 子元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return json.size();
    }

    @Override
    public String toString() {

        return json.toString();
    }
}
