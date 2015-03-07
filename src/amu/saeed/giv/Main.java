package amu.saeed.giv;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, GivUnsupportedTypeException, IOException, GivException {
        // write your code here
//        Stopwatch stopwatch=Stopwatch.createStarted();
//
//        List<TestType> ll=new ArrayList<TestType>();
//        for(int i=0;i<10000;i++)
//            ll.add(new TestType());
//
//        System.out.println("Time:" + stopwatch);
//        stopwatch.reset().start();
//
//        for(TestType t:ll)
//            t.map.size();
//
//        System.out.println("Time:" + stopwatch);
//        stopwatch.reset().start();
//
//        ObjectMapper jax = new ObjectMapper();
//        for(int x=0;x<10;x++)
//        for(TestType t:ll) {
//            jax.writeValueAsString(t);
//        }
//
//        System.out.println("Time:" + stopwatch);
//        stopwatch.reset().start();
//
//        Gson gson=new GsonBuilder().disableHtmlEscaping().create();
//        for(int x=0;x<10;x++)
//        for(TestType t:ll) {
//            gson.toJson(t);
//        }
//
//        System.out.println("Time:" + stopwatch);
//        stopwatch.reset().start();
//
//        for(int x=0;x<10;x++)
//        for(TestType t:ll) {
//            jax.writeValueAsString(GivMapper.map(t));
//        }
//
//        System.out.println("Time:" + stopwatch);
//        stopwatch.reset().start();


        TestType test = new TestType();
        System.out.println(ClassChecker.isSimple(test));
//        JsonMapper2 mapper = new JsonMapper2();
//        String json = mapper.toJson(test);

        //Map<String, Object> mm = GivMapper.map(test);
//
        String jack = new ObjectMapper().writeValueAsString(test);
        //String jack1 = new ObjectMapper().writeValueAsString(mm);
//
//
        String gson = new GsonBuilder().disableHtmlEscaping().create().toJson(test);
////
        Map<String, Object> jackmap = new ObjectMapper().readValue(gson, Map.class);
        TestType tt2 = new ObjectMapper().readValue(gson, TestType.class);
        Map<String, Object> gsonmap = new GsonBuilder().disableHtmlEscaping().create().fromJson(gson, Map.class);
////
////        System.out.println(json);
        System.out.println(jack);
        System.out.println(gson);
        //System.out.println(jack1);
    }
}
