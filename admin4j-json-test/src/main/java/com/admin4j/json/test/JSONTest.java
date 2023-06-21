package com.admin4j.json.test;

import com.admin4j.json.JSONUtil;
import com.admin4j.json.mapper.JSONMapper;
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

    @Test
    public void getJSONArrayMapper() throws Exception {

        String json = "{\"code\":0,\"message\":\"success\",\"error_details\":[],\"data\":[{\"wid\":985723,\"type\":3,\"name\":\"5636\",\"is_delete\":0},{\"wid\":618640,\"type\":3,\"name\":\"无忧达美东NJJW三号仓\",\"is_delete\":0},{\"wid\":618639,\"type\":3,\"name\":\"无忧达美东NJJW二号仓\",\"is_delete\":0},{\"wid\":618638,\"type\":3,\"name\":\"无忧达美南GAJW三号仓\",\"is_delete\":0},{\"wid\":618641,\"type\":3,\"name\":\"无忧达美南TXJW一号仓\",\"is_delete\":0},{\"wid\":618635,\"type\":3,\"name\":\"无忧达美西CAJW一号仓\",\"is_delete\":0},{\"wid\":618636,\"type\":3,\"name\":\"无忧达美西CAJW二号仓\",\"is_delete\":0},{\"wid\":618637,\"type\":3,\"name\":\"无忧达美西CAJW四号仓\",\"is_delete\":0},{\"wid\":618811,\"type\":3,\"name\":\"易达云 德国6仓\",\"is_delete\":0},{\"wid\":617839,\"type\":3,\"name\":\"易达云英国M2仓\",\"is_delete\":0},{\"wid\":617773,\"type\":3,\"name\":\"猎豹-徐达 MIRALOMA\",\"is_delete\":0},{\"wid\":617774,\"type\":3,\"name\":\"猎豹-徐达 NJCB\",\"is_delete\":0},{\"wid\":617663,\"type\":3,\"name\":\"猎豹供应链CAMR美西仓\",\"is_delete\":0},{\"wid\":617771,\"type\":3,\"name\":\"猎豹供应链CAMR美西仓（乐愿7）\",\"is_delete\":0},{\"wid\":617664,\"type\":3,\"name\":\"猎豹供应链GANW美南仓\",\"is_delete\":0},{\"wid\":617665,\"type\":3,\"name\":\"猎豹供应链NJCB美东仓\",\"is_delete\":0},{\"wid\":617772,\"type\":3,\"name\":\"猎豹供应链NJCB美东仓（乐愿7）\",\"is_delete\":0},{\"wid\":617776,\"type\":3,\"name\":\"谷仓德国5号仓\",\"is_delete\":0},{\"wid\":617775,\"type\":3,\"name\":\"谷仓英国仓\",\"is_delete\":0},{\"wid\":617674,\"type\":3,\"name\":\"递四方德国不莱梅仓\",\"is_delete\":0},{\"wid\":617673,\"type\":3,\"name\":\"递四方德国法兰克福2仓\",\"is_delete\":0},{\"wid\":617672,\"type\":3,\"name\":\"递四方英国莱切斯特仓\",\"is_delete\":0}],\"total\":0}";
        JSONMapper jsonMapper = JSONUtil.parseMapper(json);
        System.out.println("data = " + jsonMapper.getJSONArrayMapper("data"));
        Long wid = jsonMapper.getJSONArrayMapper("data").getMapper(0).getLong("wid");
        System.out.println("wid = " + wid);
        assert wid == 985723;
    }
}
