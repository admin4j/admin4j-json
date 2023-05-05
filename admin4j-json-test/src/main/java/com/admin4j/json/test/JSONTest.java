package com.admin4j.json.test;

import com.admin4j.json.JSONUtil;
import com.admin4j.json.test.entity.User;
import org.junit.Test;

import java.util.Map;

/**
 * @author andanyang
 * @since 2023/5/5 15:35
 */
public class JSONTest {

    @Test
    public void testToJSONString() throws Exception {

        User admin4j = new User("admin4j", 18);
        String jsonString = JSONUtil.toJSONString(admin4j);
        System.out.println("jsonString = " + jsonString);
    }

    @Test
    public void testParseObject() throws Exception {

        String json = "{\"age\":18,\"name\":\"admin4j\"}";
        User user = JSONUtil.parseObject(json, User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void testParseMap() throws Exception {

        String json = "{\"age\":18,\"name\":\"admin4j\"}";
        Map<String, Object> stringObjectMap = JSONUtil.parseMap(json);
        System.out.println("stringObjectMap = " + stringObjectMap);
    }
}
