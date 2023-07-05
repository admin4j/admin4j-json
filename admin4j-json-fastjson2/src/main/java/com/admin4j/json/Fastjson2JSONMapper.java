package com.admin4j.json;


import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author andanyang
 * @since 2023/5/25 12:39
 */
@RequiredArgsConstructor
public class Fastjson2JSONMapper implements JSONMapper {

    private final JSONObject jsonObject;

    @Override
    public Object getOriginObject() {
        return jsonObject;
    }

    @Override
    public JSONMapper getJSONMapper(String key) {
        JSONObject jsonObject1 = jsonObject.getJSONObject(key);
        return jsonObject1 == null ? null : new Fastjson2JSONMapper(jsonObject1);
    }

    @Override
    public JSONArrayMapper getJSONArrayMapper(String key) {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        return jsonArray == null ? null : new Fast2JSONArrayMapper(jsonArray);
    }

    @Override
    public <T> T getObject(String key, Class<T> tClass) {
        return jsonObject.getObject(key, tClass);
    }

    @Override
    public <T> List<T> getArray(String key, Class<T> tClass) {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<T> jsonMappers = new ArrayList<>(jsonArray.size());
        for (Object jsonObject : jsonArray) {
            jsonMappers.add(((JSONObject) jsonObject).toJavaObject(tClass));
        }
        return jsonMappers;
    }

    @Override
    public List<JSONMapper> getMappers(String key) {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<JSONMapper> jsonMappers = new ArrayList<>(jsonArray.size());
        for (Object jsonObject : jsonArray) {
            jsonMappers.add(new Fastjson2JSONMapper((JSONObject) jsonObject));
        }
        return jsonMappers;
    }

    @Override
    public Boolean getBoolean(String key) {
        return jsonObject.getBoolean(key);
    }

    @Override
    public boolean getBooleanValue(String key) {
        return jsonObject.getBooleanValue(key);
    }

    @Override
    public Integer getInteger(String key) {
        return jsonObject.getInteger(key);
    }

    @Override
    public int getIntValue(String key) {
        return jsonObject.getIntValue(key);
    }

    @Override
    public Long getLong(String key) {
        return jsonObject.getLong(key);
    }

    @Override
    public long getLongValue(String key) {
        return jsonObject.getLongValue(key);
    }

    @Override
    public Float getFloat(String key) {
        return jsonObject.getFloat(key);
    }

    @Override
    public float getFloatValue(String key) {
        return jsonObject.getFloatValue(key);
    }

    @Override
    public Double getDouble(String key) {
        return jsonObject.getDouble(key);
    }

    @Override
    public double getDoubleValue(String key) {
        return jsonObject.getDoubleValue(key);
    }

    @Override
    public String getString(String key) {
        return jsonObject.getString(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return jsonObject.getBigDecimal(key);
    }

    @Override
    public boolean containsKey(String key) {
        return jsonObject.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        return jsonObject.keySet();
    }

    @Override
    public String toJSONString() {
        return jsonObject.toJSONString();
    }

    @Override
    public <T> T toJavaObject(Class<T> tClass) {
        return jsonObject.toJavaObject(tClass);
    }

    @Override
    public String toString() {

        return jsonObject.toString();
    }

    /**
     * 子元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return jsonObject.size();
    }
}
