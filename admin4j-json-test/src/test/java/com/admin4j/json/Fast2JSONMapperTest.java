package com.admin4j.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.Before;

import java.util.Collections;

/**
 * @author andanyang
 * @since 2023/5/25 14:40
 */
public class Fast2JSONMapperTest extends JSONMapperTest {


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


}