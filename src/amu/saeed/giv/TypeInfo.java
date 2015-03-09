package amu.saeed.giv;

import amu.saeed.giv.annotations.GivField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Saeed on 3/9/2015.
 */
public class TypeInfo {
    public FieldInfo[] fieldInfos;

    public static TypeInfo buildFromType(Class clazz) throws GivException {
        TypeInfo typeInfo = new TypeInfo();
        HashSet<String> nameSet = new HashSet();
        ArrayList<FieldInfo> validFields = new ArrayList();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean skipThis = false;
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            String fieldName = field.getName();
            String fieldAlias = "";
            Annotation[] annots = field.getAnnotations();
            for (Annotation annot : annots) {
                if (annot instanceof GivField) {
                    GivField givField = (GivField) annot;
                    if (givField.skip())
                        skipThis = true;
                    if (!"".equals(givField.name()))
                        fieldName = givField.name();
                    fieldAlias = givField.alias();
                }
            }
            if (skipThis)
                continue;

            if (nameSet.contains(fieldName) || nameSet.contains(fieldAlias))
                throw new GivException("clash between names or alias!: " + fieldName);
            nameSet.add(fieldName);
            if (!"".equals(fieldAlias))
                nameSet.add(fieldAlias);

            FieldInfo info = new FieldInfo();
            info.field = field;
            info.name = fieldName;
            info.alias = fieldAlias;
            info.type = fieldType;

            if (!ClassChecker.acceptableTypes.contains(fieldType) && !fieldType.isEnum())
                throw new GivUnsupportedTypeException("The class has complex types as field: " + fieldType.toString());
            validFields.add(info);
        }
        typeInfo.fieldInfos = validFields.toArray(new FieldInfo[0]);
        return typeInfo;
    }

    public static class FieldInfo {
        Class type = null;
        Field field = null;
        String name = "";
        String alias = "";
    }
}
