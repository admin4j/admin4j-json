package com.admin4j.json.array;

import com.admin4j.json.GsonArrayMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.junit.Before;

/**
 * @author andanyang
 * @since 2023/5/25 16:14
 */
public class GsonArrayMapperTest extends ArrayMapperTest {


    @Before
    public void initialize() {
        super.initialize();

        Gson gson = new Gson();
        JsonArray jsonObject = gson.fromJson(testArrsys, JsonArray.class);
        jsonArrayMapper = new GsonArrayMapper(gson, jsonObject);
    }


}