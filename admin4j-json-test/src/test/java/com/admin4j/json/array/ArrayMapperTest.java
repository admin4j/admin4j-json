package com.admin4j.json.array;

import com.admin4j.json.Fast2JSONArrayMapper;
import com.admin4j.json.mapper.JSONArrayMapper;
import com.admin4j.json.mapper.JSONMapper;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author andanyang
 * @since 2023/5/25 16:14
 */
public class ArrayMapperTest {

    JSONArrayMapper jsonArrayMapper;
    String testArrsys;

    @Before
    public void initialize() {
        ArrayMapperTest.SubTestJSON subTestJSON = new ArrayMapperTest.SubTestJSON("10", 11, 12L);
        ArrayMapperTest.TestJSON testJSON1 = new ArrayMapperTest.TestJSON("1", 2, 3L, 4d, 5f, true, subTestJSON, Collections.singletonList(subTestJSON));
        ArrayMapperTest.TestJSON testJSON2 = new ArrayMapperTest.TestJSON("11", 12, 13L, 14d, 15f, true, subTestJSON, Collections.singletonList(subTestJSON));
        List<ArrayMapperTest.TestJSON> testJSONS = new ArrayList<>();
        testJSONS.add(testJSON1);
        testJSONS.add(testJSON2);


        Object[] objects1 = {
                "111", 1, 2L, 3D, 4f, true, subTestJSON, testJSONS
        };
        testArrsys = JSON.toJSONString(objects1);
        JSONArray objects = JSON.parseArray(testArrsys);
        jsonArrayMapper = new Fast2JSONArrayMapper(objects);

    }

    public void testGetOriginObject() {
    }

    @Test
    public void testGetMapper() {

        JSONMapper mapper = jsonArrayMapper.getMapper(6);
        System.out.println("mapper = " + mapper);
        assert mapper.getString("x").equals("10");
    }

    @Test
    public void testGetArray() {
        JSONArrayMapper array = jsonArrayMapper.getArray(7);
        System.out.println("array = " + array);
        assert array.size() == 2;
    }

    @Test
    public void testGetBooleanValue() {
        boolean booleanValue = jsonArrayMapper.getBooleanValue(5);
        System.out.println("booleanValue = " + booleanValue);
        assert booleanValue;
    }

    @Test
    public void testGetInt() {
        int intValue = jsonArrayMapper.getIntValue(1);
        System.out.println("intValue = " + intValue);
        assert intValue == 1;
    }

    @Test
    public void testGetLong() {
        long longValue = jsonArrayMapper.getLongValue(2);
        System.out.println("longValue = " + longValue);
        assert longValue == 2;
    }

    @Test
    public void testGetFloatValue() {
        float floatValue = jsonArrayMapper.getFloatValue(4);
        System.out.println("floatValue = " + floatValue);
        assert floatValue == 4;
    }

    @Test
    public void testGetDoubleValue() {
        double doubleValue = jsonArrayMapper.getDoubleValue(3);
        System.out.println("doubleValue = " + doubleValue);
        assert doubleValue == 3;
    }

    @Test
    public void testGetString() {
        String string = jsonArrayMapper.getString(0);
        System.out.println("string = " + string);
        assert Objects.equals(string, "111");
    }

    @Test
    public void testToList() {

        JSONArrayMapper array = jsonArrayMapper.getArray(7);
        List<TestJSON> list = array.toList(TestJSON.class);
        System.out.println("list = " + list);
        assert list.size() == 2;
    }

    @Test
    public void testSize() {
        int size = jsonArrayMapper.size();
        System.out.println("size = " + size);
        assert size == 8;
    }


    @Test
    public void testIsEmpty() {
        boolean empty = jsonArrayMapper.isEmpty();
        System.out.println("empty = " + empty);
        assert !empty;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SubTestJSON {
        private String x;
        private int y;
        private long z;
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
        private ArrayMapperTest.SubTestJSON subTestJSON;
        private List<ArrayMapperTest.SubTestJSON> subTestJSONs;

    }


}