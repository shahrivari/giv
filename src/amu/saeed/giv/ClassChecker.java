package amu.saeed.giv;


import java.util.*;

/**
 * Created by Saeed on 3/7/2015.
 */
public class ClassChecker {
    public static Set<Class> acceptableTypes = Collections.unmodifiableSet(new HashSet<Class>() {{
        //primitives
        add(Boolean.TYPE);
        add(Boolean.class);
        add(Byte.TYPE);
        add(Byte.class);
        add(Character.TYPE);
        add(Character.class);
        add(Short.TYPE);
        add(Short.class);
        add(Integer.TYPE);
        add(Integer.class);
        add(Long.TYPE);
        add(Long.class);
        add(Float.TYPE);
        add(Float.class);
        add(Double.TYPE);
        add(Double.class);
        add(String.class);
        add(Date.class);

        //arrays
        add(boolean[].class);
        add(Boolean[].class);
        add(byte[].class);
        add(Byte[].class);
        add(char[].class);
        add(Character[].class);
        add(short[].class);
        add(Short[].class);
        add(int[].class);
        add(Integer[].class);
        add(long[].class);
        add(Long[].class);
        add(float[].class);
        add(Float[].class);
        add(double[].class);
        add(Double[].class);
        add(String[].class);
        add(Date[].class);

        //Collections
        add(List.class);
        add(Set.class);
        add(Map.class);
    }});

    public static Set<Class> acceptableKeyTypes = Collections.unmodifiableSet(new HashSet<Class>() {{
        //primitives
        add(Character.class);
        add(Integer.class);
        add(Long.class);
        add(Double.class);
        add(String.class);
    }});

    public static boolean isAcceptableType(Class clazz) {
        return acceptableTypes.contains(clazz) || clazz.isEnum();
    }

    public static boolean isAcceptableKeyType(Class clazz) {
        return acceptableKeyTypes.contains(clazz) || clazz.isEnum();
    }

}
