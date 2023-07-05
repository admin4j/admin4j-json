package com.admin4j.json;

import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author andanyang
 * @since 2023/5/25 15:30
 */
@RequiredArgsConstructor
public class FastJSONArrayMapper implements JSONArrayMapper {


    private final JSONArray jsonArray;

    /**
     * 获取原始对象
     */
    @Override
    public Object getOriginObject() {
        return jsonArray;
    }

    /**
     * @param index 元素下标
     * @return 子 JsonObj
     */
    @Override
    public JSONMapper getMapper(int index) {
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        return jsonObject == null ? null : new FastjsonJSONMapper(jsonObject);
    }

    /**
     * @param index 元素下标
     * @return 子 JsonArr
     */
    @Override
    public JSONArrayMapper getArray(int index) {

        JSONArray jsonArray1 = jsonArray.getJSONArray(index);
        return jsonArray1 == null ? null : new FastJSONArrayMapper(jsonArray1);
    }

    /**
     * @param index 元素下标
     * @return boolean 值
     */
    @Override
    public boolean getBooleanValue(int index) {
        return jsonArray.getBooleanValue(index);
    }

    /**
     * @param index 元素下标
     * @return boolean 值
     */
    @Override
    public Boolean getBoolean(int index) {
        return jsonArray.getBoolean(index);
    }

    /**
     * @param index 元素下标
     * @return int 值
     */
    @Override
    public int getIntValue(int index) {
        return jsonArray.getIntValue(index);
    }

    /**
     * @param index 元素下标
     * @return int 值
     */
    @Override
    public Integer getInteger(int index) {
        return jsonArray.getInteger(index);
    }

    /**
     * @param index 元素下标
     * @return long 值
     */
    @Override
    public long getLongValue(int index) {
        return jsonArray.getLongValue(index);
    }

    /**
     * @param index 元素下标
     * @return long 值
     */
    @Override
    public Long getLong(int index) {
        return jsonArray.getLong(index);
    }

    /**
     * @param index 元素下标
     * @return float 值
     */
    @Override
    public float getFloatValue(int index) {
        return jsonArray.getFloatValue(index);
    }

    /**
     * @param index 元素下标
     * @return float 值
     */
    @Override
    public Float getFloat(int index) {
        return jsonArray.getFloat(index);
    }

    /**
     * @param index 元素下标
     * @return double 值
     */
    @Override
    public double getDoubleValue(int index) {
        return jsonArray.getDoubleValue(index);
    }

    /**
     * @param index 元素下标
     * @return double 值
     */
    @Override
    public Double getDouble(int index) {
        return jsonArray.getDouble(index);
    }

    /**
     * @param index 元素下标
     * @return String 值
     */
    @Override
    public String getString(int index) {
        return jsonArray.getString(index);
    }

    /**
     * @param tClass 目标类型
     * @return 转 JavaBean 列表
     */
    @Override
    public <T> List<T> toList(Class<T> tClass) {
        return jsonArray.toJavaList(tClass);
    }


    @Override
    public int size() {
        return jsonArray.size();
    }


    @Override
    public boolean isEmpty() {
        return jsonArray.isEmpty();
    }
}
