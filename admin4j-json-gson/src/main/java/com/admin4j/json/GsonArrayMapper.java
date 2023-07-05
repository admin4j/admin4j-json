package com.admin4j.json;

import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author andanyang
 * @since 2023/5/25 15:46
 */
@RequiredArgsConstructor
public class GsonArrayMapper implements JSONArrayMapper {
    private final Gson gson;
    private final JsonArray json;

    /**
     * 获取原始对象
     */
    @Override
    public Object getOriginObject() {
        return json;
    }

    /**
     * @param index 元素下标
     * @return 子 JsonObj
     */
    @Override
    public JSONMapper getMapper(int index) {
        JsonObject json1 = (JsonObject) json.get(index);
        return json1 == null ? null : new GsonJSONMapper(gson, json1);
    }

    /**
     * @param index 元素下标
     * @return 子 JsonArr
     */
    @Override
    public JSONArrayMapper getArray(int index) {
        JsonArray json1 = (JsonArray) json.get(index);
        return json1 == null ? null : new GsonArrayMapper(gson, json1);
    }

    /**
     * @param index 元素下标
     * @return boolean 值
     */
    @Override
    public boolean getBooleanValue(int index) {

        return json.get(index).getAsBoolean();
    }

    /**
     * @param index 元素下标
     * @return int 值
     */
    @Override
    public int getIntValue(int index) {
        return json.get(index).getAsInt();
    }

    /**
     * @param index 元素下标
     * @return long 值
     */
    @Override
    public long getLongValue(int index) {
        return json.get(index).getAsLong();
    }

    /**
     * @param index 元素下标
     * @return float 值
     */
    @Override
    public float getFloatValue(int index) {
        return json.get(index).getAsFloat();
    }

    /**
     * @param index 元素下标
     * @return double 值
     */
    @Override
    public double getDoubleValue(int index) {
        return json.get(index).getAsDouble();
    }

    /**
     * @param index 元素下标
     * @return String 值
     */
    @Override
    public String getString(int index) {
        return json.get(index).getAsString();
    }

    /**
     * @param tClass 目标类型
     * @return 转 JavaBean 列表
     */
    @Override
    public <T> List<T> toList(Class<T> tClass) {
        T[] beans = gson.fromJson(json, TypeToken.getArray(tClass).getType());
        return Arrays.asList(beans);
    }


    @Override
    public int size() {
        return json.size();
    }

    @Override
    public boolean isEmpty() {
        return json.isEmpty();
    }
}
