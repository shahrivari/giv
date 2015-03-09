package amu.saeed.giv;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Saeed on 3/6/2015.
 */
public class GivMapper {
    private Map<Class, TypeInfo> types = new HashMap<Class, TypeInfo>();

    public GivMapper() {
    }

    public Map<String, Object> map(Object obj) throws GivException {
        if (!types.containsKey(obj.getClass()))
            types.put(obj.getClass(), TypeInfo.buildFromType(obj.getClass()));
        TypeInfo typeInfo = types.get(obj.getClass());

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (TypeInfo.FieldInfo fieldinfo : typeInfo.fieldInfos) {
            Field field = fieldinfo.field;
            Class clazz = fieldinfo.type;
            String fieldName = fieldinfo.name;
            Object fieldObject = null;
            try {
                fieldObject = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new GivException(e.getMessage());
            }

            if (clazz.equals(List.class)) {
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
