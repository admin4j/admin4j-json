package com.admin4j.json.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author andanyang
 * @since 2023/5/25 12:37
 */
public interface JSONMapper {

    /**
     * 获取原始对象
     */
    Object getOriginObject();


    /**
     * 转化为JSON 字符串
     *
     * @return
     */
    String toJSONString();


    /**
     * 转化为java对象
     */
    <T> T toJavaObject(Class<T> tClass);

    Boolean getBoolean(String key);


    boolean getBooleanValue(String key);


    Integer getInteger(String key);

    int getIntValue(String key);


    Long getLong(String key);


    long getLongValue(String key);


    Float getFloat(String key);

    float getFloatValue(String key);


    Double getDouble(String key);


    double getDoubleValue(String key);


    String getString(String key);

    BigDecimal getBigDecimal(String key);

    boolean containsKey(String key);


    Set<String> keySet();


    /**
     * 获取改key的 子对象的JSONMapper值
     */
    JSONMapper getJSONMapper(String key);


    /**
     * 获取改key的 子对象的Object值
     */
    <T> T getObject(String key, Class<T> tClass);

    /**
     * 获取改key的 子对象的List值
     */
    <T> List<T> getArray(String key, Class<T> tClass);

    /**
     * 获取改key的 子对象的List值
     */
    List<JSONMapper> getMappers(String key);

    /**
     * 子元素的数量
     *
     * @return
     */
    int size();
}
