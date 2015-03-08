package amu.saeed.giv;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
    }});

    static public boolean isSimple(Object obj) throws GivException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean skipThis = false;
            field.setAccessible(true);
            Class<?> clazz = field.getType();
            Annotation[] annots = field.getAnnotations();
            for (Annotation annot : annots) {
//                if (annot instanceof GivSkip) {
//                    skipThis = true;
//                    break;
//                }
            }
            if (skipThis)
                continue;
            if (clazz.isEnum())
                continue;
            if (!acceptableTypes.contains(clazz))
                return false;

            try {
                if (clazz.equals(Collection.class)) {
                    Collection col = (Collection) field.get(obj);
                    if (col.size() > 0 && !acceptableTypes.contains(col.iterator().next().getClass()))
                        return false;
                } else if (clazz.equals(List.class)) {
                    List col = (List) field.get(obj);
                    if (col.size() > 0 && !acceptableTypes.contains(col.iterator().next().getClass()))
                        return false;
                } else if (clazz.equals(Set.class)) {
                    Set col = (Set) field.get(obj);
                    if (col.size() > 0 && !acceptableTypes.contains(col.iterator().next().getClass()))
                        return false;
                } else if (clazz.equals(Map.class)) {
//                        Map col = (Map) field.get(obj);
//                        if(col.size()>0&&!acceptableTypes.contains(col.iterator().next().getClass()))
//                            return false;
                }

            } catch (IllegalAccessException e) {
                throw new GivException(e.getMessage());
            }


        }
        return true;
    }
}
