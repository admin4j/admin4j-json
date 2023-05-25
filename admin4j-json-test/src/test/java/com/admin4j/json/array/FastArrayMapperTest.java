package com.admin4j.json.array;

import com.admin4j.json.FastJSONArrayMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Before;

/**
 * @author andanyang
 * @since 2023/5/25 16:14
 */
public class FastArrayMapperTest extends ArrayMapperTest {


    @Before
    public void initialize() {
        super.initialize();
        JSONArray objects = JSON.parseArray(testArrsys);
        jsonArrayMapper = new FastJSONArrayMapper(objects);
    }


}