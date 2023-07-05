package com.admin4j.json.mapper;

import java.util.List;

/**
 * @author andanyang
 * @since 2023/5/25 12:37
 */
public interface JSONArrayMapper {

    /**
     * 获取原始对象
     */
    Object getOriginObject();

    /**
     * @param index 元素下标
     * @return 子 JsonObj
     */
    JSONMapper getMapper(int index);

    /**
     * @param index 元素下标
     * @return 子 JsonArr
     */
    JSONArrayMapper getArray(int index);

    /**
     * @param index 元素下标
     * @return boolean 值
     */
    boolean getBooleanValue(int index);

    /**
     * @param index 元素下标
     * @return int 值
     */
    int getIntValue(int index);

    /**
     * @param index 元素下标
     * @return long 值
     */
    long getLongValue(int index);

    /**
     * @param index 元素下标
     * @return float 值
     */
    float getFloatValue(int index);

    /**
     * @param index 元素下标
     * @return double 值
     */
    double getDoubleValue(int index);


    /**
     * @param index 元素下标
     * @return boolean 值
     */
    Boolean getBoolean(int index);

    /**
     * @param index 元素下标
     * @return int 值
     */
    Integer getInteger(int index);

    /**
     * @param index 元素下标
     * @return long 值
     */
    Long getLong(int index);

    /**
     * @param index 元素下标
     * @return float 值
     */
    Float getFloat(int index);

    /**
     * @param index 元素下标
     * @return double 值
     */
    Double getDouble(int index);

    /**
     * @param index 元素下标
     * @return String 值
     */
    String getString(int index);

    /**
     * @param <T>    目标泛型
     * @param tClass 目标类型
     * @return 转 JavaBean 列表
     */
    <T> List<T> toList(Class<T> tClass);


    int size();


    boolean isEmpty();
}
