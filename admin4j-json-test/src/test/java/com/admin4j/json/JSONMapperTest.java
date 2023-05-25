package com.admin4j.json;

import com.admin4j.json.mapper.JSONMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author andanyang
 * @since 2023/5/25 14:40
 */
public class JSONMapperTest {

    JSONMapper jsonMapper;
    String testString;

    @Before
    public void init() throws Exception {


        SubTestJSON subTestJSON = new SubTestJSON("10", 11, 12L);
        TestJSON testJSON = new TestJSON("1", 2, 3L, 4d, 5f, true, subTestJSON, Collections.singletonList(subTestJSON));

        testString = JSON.toJSONString(testJSON);


        JSONObject jsonObject = com.alibaba.fastjson2.JSON.parseObject(testString);
        jsonMapper = new Fastjson2JSONMapper(jsonObject);

        //Gson gson = new Gson();
        //JsonObject jsonObject = gson.fromJson(testString, JsonObject.class);
        //jsonMapper = new GsonJSONMapper(gson, jsonObject);

        //ObjectMapper objectMapper = new ObjectMapper();
        //JsonNode node = objectMapper.readTree(testString);
        //jsonMapper = new JacksonJSONMapper(objectMapper, node);
    }

    @Test
    public void testOriginObject() {

        Object originObject = jsonMapper.getOriginObject();
        System.out.println("originObject = " + originObject);
    }

    @Test
    public void toJSONString() {

        String jsonString = jsonMapper.toJSONString();
        System.out.println("jsonString = " + jsonString);
        assert jsonString != null;
    }

    @Test
    public void toJavaObject() {
        TestJSON javaObject = jsonMapper.toJavaObject(TestJSON.class);
        System.out.println("javaObject = " + javaObject);
        assert javaObject.b == 2;
    }

    @Test
    public void getBoolean() {

        Boolean f = jsonMapper.getBoolean("f");
        System.out.println("f = " + f);
        assert f;
    }

    @Test
    public void getBooleanValue() {
        Boolean f = jsonMapper.getBooleanValue("f");
        System.out.println("f = " + f);
        assert f;
    }

    @Test
    public void getInteger() {
        Integer b = jsonMapper.getInteger("b");
        System.out.println("b = " + b);
        assert b == 2;
    }

    @Test
    public void getIntValue() {
        int b = jsonMapper.getIntValue("b");
        System.out.println("b = " + b);

        assert b == 2;
    }

    @Test
    public void getLong() {
        Long c = jsonMapper.getLong("c");
        System.out.println("c = " + c);
        assert c == 3L;
    }

    @Test
    public void getLongValue() {
        long c = jsonMapper.getLongValue("c");
        System.out.println("c = " + c);
        assert c == 3L;
    }

    @Test
    public void getFloat() {

        Float e = jsonMapper.getFloat("e");
        System.out.println("e = " + e);
        assert e == 5f;
    }

    @Test
    public void getFloatValue() {

        float e = jsonMapper.getFloatValue("e");
        System.out.println("e = " + e);
        assert e == 5f;
    }

    @Test
    public void getDouble() {
        Double d = jsonMapper.getDouble("d");
        System.out.println("e = " + d);
        assert d == 4D;
    }

    @Test
    public void getDoubleValue() {
        double d = jsonMapper.getDoubleValue("d");
        System.out.println("e = " + d);
        assert d == 4L;
    }

    @Test
    public void getString() {
        String string = jsonMapper.getString("a");
        System.out.println("string = " + string);
        assert string.equals("1");
    }

    @Test
    public void containsKey() {
        boolean a = jsonMapper.containsKey("a");
        System.out.println("a = " + a);
        boolean w = jsonMapper.containsKey("w");
        System.out.println("w = " + w);
        assert a;
        assert !w;
    }

    @Test
    public void keySet() {
        Set<String> strings = jsonMapper.keySet();
        System.out.println("strings = " + strings);
        assert strings.size() == 8;
    }

    @Test
    public void getJSONMapper() {

        JSONMapper subTestJSON = jsonMapper.getJSONMapper("subTestJSON");
        System.out.println("subTestJSON = " + subTestJSON);
        assert subTestJSON.getIntValue("y") == 11;
    }

    @Test
    public void getObject() {

        SubTestJSON subTestJSON = jsonMapper.getObject("subTestJSON", SubTestJSON.class);
        System.out.println("subTestJSON = " + subTestJSON);
        assert subTestJSON.x.equals("10");
    }

    @Test
    public void getArray() {
        List<SubTestJSON> subTestJSON = jsonMapper.getArray("subTestJSONs", SubTestJSON.class);
        System.out.println("subTestJSON = " + subTestJSON);
        assert subTestJSON != null;
        assert subTestJSON.get(0).x.equals("10");
    }

    @Test
    public void getArrayMapper() {

        List<JSONMapper> subTestJSONs = jsonMapper.getMappers("subTestJSONs");
        System.out.println("subTestJSONs = " + subTestJSONs);
        assert subTestJSONs != null;
        assert subTestJSONs.get(0).getInteger("y").equals(11);
    }

    @Test
    public void size() {

        int size = jsonMapper.size();
        System.out.println("size = " + size);
        assert size == 8;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestJSON {
        private String a;
        private int b;
        private long c;
        private double d;
        private float e;
        private boolean f;
        private SubTestJSON subTestJSON;
        private List<SubTestJSON> subTestJSONs;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SubTestJSON {
        private String x;
        private int y;
        private long z;
    }
}