package com.admin4j.json;

import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author andanyang
 * @since 2023/5/25 13:23
 */
@RequiredArgsConstructor
public class JacksonJSONMapper implements JSONMapper {

    private final ObjectMapper mapper;
    private final JsonNode jsonNode;


    /**
     * 是否为数组
     *
     * @param key
     */
    @Override
    public boolean isArray(String key) {
        JsonNode node = jsonNode.get(key);
        return node != null && node.isArray();
    }

    /**
     * 是否是对象
     *
     * @param key
     */
    @Override
    public boolean isObject(String key) {
        JsonNode node = jsonNode.get(key);
        return node != null && node.isObject();
    }

    /**
     * 获取原始对象
     */
    @Override
    public Object getOriginObject() {
        return jsonNode;
    }

    /**
     * 转化为JSON 字符串
     *
     * @return
     */
    @Override
    public String toJSONString() {
        return jsonNode.toString();
    }

    /**
     * 转化为java对象
     *
     * @param tClass
     */
    @Override
    public <T> T toJavaObject(Class<T> tClass) {
        return mapper.convertValue(jsonNode, tClass);
    }

    @Override
    public Boolean getBoolean(String key) {
        JsonNode node = jsonNode.get(key);
        return node == null ? null : node.asBoolean();
    }

    @Override
    public boolean getBooleanValue(String key) {
        JsonNode node = jsonNode.get(key);
        return node.asBoolean();
    }

    @Override
    public Integer getInteger(String key) {
        return jsonNode.has(key) ? jsonNode.get(key).intValue() : null;
    }

    @Override
    public int getIntValue(String key) {
        return jsonNode.get(key).intValue();
    }

    @Override
    public Long getLong(String key) {
        return jsonNode.has(key) ? jsonNode.get(key).longValue() : null;
    }

    @Override
    public long getLongValue(String key) {
        return jsonNode.get(key).longValue();
    }

    @Override
    public Float getFloat(String key) {
        return jsonNode.has(key) ? jsonNode.get(key).floatValue() : null;
    }

    @Override
    public float getFloatValue(String key) {
        return jsonNode.get(key).floatValue();
    }

    @Override
    public Double getDouble(String key) {
        return jsonNode.has(key) ? jsonNode.get(key).doubleValue() : null;
    }

    @Override
    public double getDoubleValue(String key) {
        return jsonNode.get(key).doubleValue();
    }

    @Override
    public String getString(String key) {
        return jsonNode.get(key).asText();
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return new BigDecimal(jsonNode.get(key).asText());
    }

    @Override
    public boolean containsKey(String key) {
        return jsonNode.has(key);
    }

    @Override
    public Set<String> keySet() {
        final Iterator<String> it = this.jsonNode.fieldNames();

        return new AbstractSet<String>() {
            @Override
            public Iterator<String> iterator() {
                return it;
            }

            @Override
            public int size() {
                return jsonNode.size();
            }
        };
    }

    /**
     * 获取改key的获取该key的 子对象的对象（JSONMapper）值
     *
     * @param key
     */
    @Override
    public JSONMapper getJSONMapper(String key) {
        JsonNode jsonNode1 = jsonNode.get(key);
        return jsonNode1 == null ? null : new JacksonJSONMapper(mapper, jsonNode1);
    }

    /**
     * @param key key
     * @return 获取该key的 子对象的 数组 （JSONArrayMapper）值
     */
    @Override
    public JSONArrayMapper getJSONArrayMapper(String key) {
        JsonNode node = this.jsonNode.get(key);
        if (node == null || !node.isArray()) {
            return null;
        }
        ArrayNode arrayNode = (ArrayNode) jsonNode.get(key);
        return arrayNode == null ? null : new JacksonArrayMapper(mapper, arrayNode);
    }

    /**
     * 获取改key的 子对象的Object值
     *
     * @param key
     * @param tClass
     */
    @Override
    public <T> T getObject(String key, Class<T> tClass) {

        return mapper.convertValue(jsonNode.get(key), tClass);
    }

    /**
     * 获取改key的 子对象的List值
     *
     * @param key
     * @param tClass
     */
    @Override
    public <T> List<T> getArray(String key, Class<T> tClass) {
        JsonNode node = this.jsonNode.get(key);
        if (node == null || !node.isArray()) {
            return null;
        }

        int size = node.size();
        if (size == 0) {
            return Collections.emptyList();
        }

        List<T> result = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            JsonNode item = node.get(i);
            result.add(mapper.convertValue(item, tClass));
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
        JsonNode node = this.jsonNode.get(key);
        if (node == null || !node.isArray()) {
            return null;
        }

        int size = node.size();
        if (size == 0) {
            return Collections.emptyList();
        }

        List<JSONMapper> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            JsonNode item = node.get(i);
            result.add(new JacksonJSONMapper(mapper, item));
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
        return jsonNode.size();
    }

    @Override
    public String toString() {

        return jsonNode.toString();
    }
}
