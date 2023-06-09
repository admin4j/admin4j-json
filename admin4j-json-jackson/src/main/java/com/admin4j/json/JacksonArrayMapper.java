package com.admin4j.json;

import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andanyang
 * @since 2023/5/25 16:00
 */
@RequiredArgsConstructor
public class JacksonArrayMapper implements JSONArrayMapper {

    private final ObjectMapper mapper;
    private final ArrayNode arrayNode;

    /**
     * 获取原始对象
     */
    @Override
    public Object getOriginObject() {
        return arrayNode;
    }

    /**
     * @param index 元素下标
     * @return 子 JsonObj
     */
    @Override
    public JSONMapper getMapper(int index) {
        JsonNode jsonNode = arrayNode.get(index);
        return jsonNode == null ? null : new JacksonJSONMapper(mapper, jsonNode);
    }

    /**
     * @param index 元素下标
     * @return 子 JsonArr
     */
    @Override
    public JSONArrayMapper getArray(int index) {
        ArrayNode arrayNode1 = (ArrayNode) arrayNode.get(index);
        return arrayNode1 == null ? null : new JacksonArrayMapper(mapper, arrayNode1);
    }

    /**
     * @param index 元素下标
     * @return boolean 值
     */
    @Override
    public boolean getBooleanValue(int index) {

        return arrayNode.get(index).booleanValue();
    }

    /**
     * @param index 元素下标
     * @return boolean 值
     */
    @Override
    public Boolean getBoolean(int index) {
        JsonNode node = arrayNode.get(index);
        return node == null ? null : node.booleanValue();
    }


    /**
     * @param index 元素下标
     * @return int 值
     */
    @Override
    public int getIntValue(int index) {
        return arrayNode.get(index).intValue();
    }

    /**
     * @param index 元素下标
     * @return int 值
     */
    @Override
    public Integer getInteger(int index) {
        JsonNode node = arrayNode.get(index);
        return node == null ? null : node.intValue();
    }

    /**
     * @param index 元素下标
     * @return long 值
     */
    @Override
    public Long getLong(int index) {
        JsonNode node = arrayNode.get(index);
        return node == null ? null : node.longValue();
    }

    /**
     * @param index 元素下标
     * @return long 值
     */
    @Override
    public long getLongValue(int index) {
        return arrayNode.get(index).longValue();
    }

    /**
     * @param index 元素下标
     * @return float 值
     */
    @Override
    public float getFloatValue(int index) {
        return arrayNode.get(index).floatValue();
    }

    /**
     * @param index 元素下标
     * @return float 值
     */
    @Override
    public Float getFloat(int index) {
        JsonNode node = arrayNode.get(index);
        return node == null ? null : node.floatValue();
    }

    /**
     * @param index 元素下标
     * @return double 值
     */
    @Override
    public double getDoubleValue(int index) {
        return arrayNode.get(index).doubleValue();
    }

    /**
     * @param index 元素下标
     * @return double 值
     */
    @Override
    public Double getDouble(int index) {
        JsonNode node = arrayNode.get(index);
        return node == null ? null : node.doubleValue();
    }

    /**
     * @param index 元素下标
     * @return String 值
     */
    @Override
    public String getString(int index) {
        JsonNode node = arrayNode.get(index);
        return node == null ? null : node.textValue();
    }

    /**
     * @param tClass 目标类型
     * @return 转 JavaBean 列表
     */
    @Override
    public <T> List<T> toList(Class<T> tClass) {
        CollectionType collectionType = this.mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        try {
            return this.mapper.treeToValue(arrayNode, collectionType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int size() {
        return arrayNode.size();
    }

    @Override
    public boolean isEmpty() {
        return arrayNode.isEmpty();
    }
}
