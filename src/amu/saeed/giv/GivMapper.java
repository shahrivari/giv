package amu.saeed.giv;

import amu.saeed.giv.annotations.GivField;
import amu.saeed.giv.annotations.GivSkip;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Saeed on 3/6/2015.
 */
public class GivMapper {
    private GivMapper() {
    }

    ;

    public static Map<String, Object> map(Object obj) throws GivException {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean skipThis = false;
            field.setAccessible(true);
            Class<?> clazz = field.getType();
            String fieldName = field.getName();
            Object fieldObject = null;
            try {
                fieldObject = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new GivException(e.getMessage());
            }

            Annotation[] annots = field.getAnnotations();
            for (Annotation annot : annots) {
                if (annot instanceof GivSkip) {
                    skipThis = true;
                    break;
                } else if (annot instanceof GivField) {
                    GivField givField = (GivField) annot;
                    if (!"".equals(givField.name()))
                        fieldName = givField.name();
                }
            }
            if (skipThis)
                continue;

            if (!ClassChecker.acceptableTypes.contains(clazz) && !clazz.isEnum())
                throw new GivUnsupportedTypeException("The class has complex types as field: " + clazz.toString());
            else if (clazz.equals(List.class)) {
                List list = (List) fieldObject;
                for (Object elem : list) {
                    Class<? extends Object> elemclass = elem.getClass();
                    if (!ClassChecker.acceptableTypes.contains(elemclass) && !elemclass.isEnum())
                        throw new GivUnsupportedTypeException("The class has a complex type in a List field: " + field.getName() + " element: " + elemclass);
                }
            } else if (clazz.equals(Set.class)) {
                Set set = (Set) fieldObject;
                for (Object elem : set) {
                    Class<? extends Object> elemclass = elem.getClass();
                    if (!ClassChecker.acceptableTypes.contains(elemclass) && !elemclass.isEnum())
                        throw new GivUnsupportedTypeException("The class has a complex type in a Set field: " + field.getName() + " element: " + elemclass);
                }
            } else if (clazz.equals(Map.class)) {
                Map m = (Map) fieldObject;
                for (Object memobj : m.entrySet()) {
                    Map.Entry entry = (Map.Entry) memobj;
                    Class<? extends Object> keyclass = entry.getKey().getClass();
                    if (!ClassChecker.acceptableTypes.contains(keyclass) && !keyclass.isEnum())
                        throw new GivUnsupportedTypeException("The class has a complex type as key in a Map field: " + field.getName() + " element: " + keyclass);
                    Class<? extends Object> valclass = entry.getValue().getClass();
                    if (!ClassChecker.acceptableTypes.contains(valclass) && !valclass.isEnum())
                        throw new GivUnsupportedTypeException("The class has a complex type as key in a Map field: " + field.getName() + " element: " + valclass);
                }
            }


            if (!map.containsKey(fieldName))
                map.put(fieldName, fieldObject);
            else
                throw new GivException("Got a field two times:" + fieldName);
        }

        return map;
    }


}
