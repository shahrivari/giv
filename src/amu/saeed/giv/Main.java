package amu.saeed.giv;


import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, GivUnsupportedTypeException, IOException, GivException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<TestType> ll = new ArrayList<TestType>();
        for (int i = 0; i < 10000; i++)
            ll.add(new TestType());

        System.out.println("Create Time:" + stopwatch);
        stopwatch.reset().start();


        for (int x = 0; x < 10; x++)
            for (TestType t : ll)
                GivMapper.map(t);

        System.out.println("Map Time:" + stopwatch);
        stopwatch.reset().start();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        for (int x = 0; x < 10; x++)
            for (TestType t : ll) {
                gson.toJson(t);
            }

        System.out.println("Gson Time:" + stopwatch);
        stopwatch.reset().start();

        JsonMapper2 mapper = new JsonMapper2();
        for (int x = 0; x < 10; x++)
            for (TestType t : ll) {
                mapper.toJson(t);
            }

        System.out.println("Giv Time:" + stopwatch);
        stopwatch.reset().start();


//
//        for(int x=0;x<10;x++)
//        for(TestType t:ll) {
//            jax.writeValueAsString(GivMapper.map(t));
//        }
//
//        System.out.println("Time:" + stopwatch);
//        stopwatch.reset().start();


    }
}
