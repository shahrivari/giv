package amu.saeed.giv;

import amu.saeed.giv.annotations.GivField;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Saeed on 3/6/2015.
 */
public class JsonMapper2 {

    Map<String, Object> map = new LinkedHashMap<String, Object>();
    Gson gson = new Gson();
    StringBuilder builder = new StringBuilder(64);

    public String toJson(Object obj) throws IllegalAccessException, GivUnsupportedTypeException, IOException {
        Field[] fields = obj.getClass().getDeclaredFields();
        builder.setLength(0);
        for (Field field : fields) {
            boolean skipThis = false;
            field.setAccessible(true);
            Class<?> clazz = field.getType();
            String fieldName = field.getName();
            Object fieldObject = field.get(obj);

            Annotation[] annots = field.getAnnotations();
            for (Annotation annot : annots) {
                if (annot instanceof GivField) {
                    GivField givField = (GivField) annot;
                    if (!"".equals(givField.name()))
                        fieldName = givField.name();
                }
            }
            if (skipThis)
                continue;
            if (fieldObject == null)
                continue;

            //map.put(fieldName, fieldObject);
            //builder.append('"').append(fieldName).append("\":").append(fieldObject);
        }

//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(map);
        //return gson.toJson(map,Map.class);
        return builder.toString();
    }


}
