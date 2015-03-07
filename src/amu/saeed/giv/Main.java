package amu.saeed.giv;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, GivJsonException, IOException {
        // write your code here
        byte[] bb = new byte[0];
        Byte[] BB = new Byte[0];
        byte n = 0;

        TestType test = new TestType();
        JsonMapper2 mapper = new JsonMapper2();
        String json = mapper.toJson(test);

        String json2 = new ObjectMapper().writeValueAsString(test);

        Map<String, Object> map = new ObjectMapper().readValue(json, Map.class);

        System.out.println(json);
        System.out.println(json2);
    }
}
