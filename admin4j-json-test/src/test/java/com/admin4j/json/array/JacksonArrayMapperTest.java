package com.admin4j.json.array;

import com.admin4j.json.JacksonArrayMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Before;

/**
 * @author andanyang
 * @since 2023/5/25 16:14
 */
public class JacksonArrayMapperTest extends ArrayMapperTest {


    @Before
    public void initialize() {
        super.initialize();

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode node = null;
        try {
            node = (ArrayNode) objectMapper.readTree(testArrsys);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        jsonArrayMapper = new JacksonArrayMapper(objectMapper, node);
    }


}