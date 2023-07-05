package com.admin4j.json;

import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author andanyang
 * @since 2023/5/25 15:30
 */
@RequiredArgsConstructor
public class Fast2JSONArrayMapper implements JSONArrayMapper {


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
        return jsonObject == null ? null : new Fastjson2JSONMapper(jsonObject);
    }

    /**
     * @param index 元素下标
     * @return 子 JsonArr
     */
    @Override
    public JSONArrayMapper getArray(int index) {

        JSONArray jsonArray1 = jsonArray.getJSONArray(index);
        return jsonArray1 == null ? null : new Fast2JSONArrayMapper(jsonArray1);
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
     * @return int 值
     */
    @Override
    public int getIntValue(int index) {
        return jsonArray.getIntValue(index);
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
     * @return float 值
     */
    @Override
    public float getFloatValue(int index) {
        return jsonArray.getFloatValue(index);
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
        //jsonArray.iterator()
        return jsonArray.isEmpty();
    }


    //@Override
    //public boolean hasNext() {
    //    return jsonArray.iterator().hasNext();
    //}
    //
    //
    //@Override
    //public Object next() {
    //    return jsonArray.iterator().next();
    //}
}
