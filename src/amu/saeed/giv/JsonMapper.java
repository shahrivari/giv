//package amu.saeed.giv;
//
//import amu.saeed.giv.annotations.GivField;
//import com.eclipsesource.json.JsonArray;
//import com.eclipsesource.json.JsonObject;
//import amu.saeed.giv.annotations.GivSkip;
//
//import java.io.IOException;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Saeed on 3/6/2015.
// */
//public class JsonMapper {
//    static private final Map<Class, JsonWriter> classMap = Collections.unmodifiableMap(new HashMap<Class, JsonWriter>() {{
//        put(Boolean.TYPE, new BooleanJsonWriter());
//        put(Boolean.class, new BooleanJsonWriter());
//        put(Byte.TYPE, new ByteJsonWriter());
//        put(Byte.class, new ByteJsonWriter());
//        put(Short.TYPE, new ShortJsonWriter());
//        put(Short.class, new ShortJsonWriter());
//        put(Integer.TYPE, new IntJsonWriter());
//        put(Integer.class, new IntJsonWriter());
//        put(Long.TYPE, new LongJsonWriter());
//        put(Long.class, new LongJsonWriter());
//        put(Float.TYPE, new FloatJsonWriter());
//        put(Float.class, new FloatJsonWriter());
//        put(Double.TYPE, new DoubleJsonWriter());
//        put(Double.class, new DoubleJsonWriter());
//        put(String.class, new StringJsonWriter());
//        put(byte[].class, new ByteArrayJsonWriter());
//    }});
//
//    public String toJson(Object obj) throws IllegalAccessException, GivJsonException {
//        JsonObject jsonObject = new JsonObject();
//
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            boolean skipThis = false;
//            field.setAccessible(true);
//            Class<?> clazz = field.getType();
//            String fieldName = field.getName();
//            Object fieldObject = field.get(obj);
//
//            Annotation[] annots = field.getAnnotations();
//            for (Annotation annot : annots) {
//                if (annot instanceof GivSkip) {
//                    skipThis = true;
//                    break;
//                } else if (annot instanceof GivField) {
//                    GivField givField = (GivField) annot;
//                    if (!"".equals(givField.name()))
//                        fieldName = givField.name();
//                }
//            }
//            if (skipThis)
//                continue;
//
//            if (fieldObject == null) {
//                jsonObject.add(fieldName, JsonObject.NULL);
//                continue;
//            }
//            //enums
//            else if (clazz.isEnum()) {
//                jsonObject.add(fieldName, fieldObject.toString());
//                continue;
//            }
//
//            if (classMap.containsKey(clazz))
//                classMap.get(clazz).write(fieldName, fieldObject, jsonObject);
//            else
//                throw new GivJsonException("Unsupported type!");
//
//
////                //objects
////            else if (clazz.equals(Boolean.class) || clazz.equals(Boolean.TYPE))
////                jsonObject.set(fieldName, (Boolean) fieldObject);
////
////            else if (clazz.equals(Byte.class) || clazz.equals(Byte.TYPE))
////                jsonObject.set(fieldName, (Byte) fieldObject);
////
////            else if (clazz.equals(Short.class) || clazz.equals(Short.TYPE))
////                jsonObject.set(fieldName, (Short) fieldObject);
////
////            else if (clazz.equals(Integer.class) || clazz.equals(Integer.TYPE))
////                jsonObject.set(fieldName, (Integer) fieldObject);
////
////            else if (clazz.equals(Integer.class) || clazz.equals(Long.TYPE))
////                jsonObject.set(fieldName, (Long) fieldObject);
////
////            else if (clazz.equals(Float.class) || clazz.equals(Float.TYPE))
////                jsonObject.set(fieldName, (Float) fieldObject);
////
////            else if (clazz.equals(Double.class) || clazz.equals(Double.TYPE))
////                jsonObject.set(fieldName, (Double) fieldObject);
////
////            else if (clazz.equals(String.class))
////                jsonObject.set(fieldName, (String) fieldObject);
////
////            else if (clazz.equals(boolean[].class)) {
////                JsonArray array = new JsonArray();
////                boolean[] arr = (boolean[]) fieldObject;
////                for (int i = 0; i < arr.length; i++)
////                    array.add(arr[i]);
////                jsonObject.add(fieldName, array);
////            } else if (clazz.equals(Boolean[].class)) {
////                JsonArray array = new JsonArray();
////                Boolean[] arr = (Boolean[]) fieldObject;
////                for (int i = 0; i < arr.length; i++)
////                    array.add(arr[i]);
////                jsonObject.add(fieldName, array);
////            } else if (clazz.equals(byte[].class)) {
////                JsonArray array = new JsonArray();
////                byte[] arr = (byte[]) fieldObject;
////                for (int i = 0; i < arr.length; i++)
////                    array.add(arr[i]);
////                jsonObject.add(fieldName, array);
////            } else if (clazz.equals(Byte[].class)) {
////                JsonArray array = new JsonArray();
////                Byte[] arr = (Byte[]) fieldObject;
////                for (int i = 0; i < arr.length; i++)
////                    array.add(arr[i]);
////                jsonObject.add(fieldName, array);
////            } else if (clazz.equals(short[].class)) {
////                JsonArray array = new JsonArray();
////                short[] arr = (short[]) fieldObject;
////                for (int i = 0; i < arr.length; i++)
////                    array.add(arr[i]);
////                jsonObject.add(fieldName, array);
////            } else if (clazz.equals(Short[].class)) {
////                JsonArray array = new JsonArray();
////                Short[] arr = (Short[]) fieldObject;
////                for (int i = 0; i < arr.length; i++)
////                    array.add(arr[i]);
////                jsonObject.add(fieldName, array);
////            }
//
//
//        }
//
//        return jsonObject.toString();
//    }
//
//    static private interface JsonWriter {
//        void write(String fieldName, Object fieldObject, JsonObject jsonObject);
//    }
//
//    static private class BooleanJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Boolean) fieldObject);
//        }
//    }
//
//    static private class ByteJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Byte) fieldObject);
//        }
//    }
//
//    static private class ShortJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Short) fieldObject);
//        }
//    }
//
//    static private class IntJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Integer) fieldObject);
//        }
//    }
//
//    static private class LongJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Long) fieldObject);
//        }
//    }
//
//    static private class FloatJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Float) fieldObject);
//        }
//    }
//
//    static private class DoubleJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (Double) fieldObject);
//        }
//    }
//
//    static private class StringJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            jsonObject.add(fieldName, (String) fieldObject);
//        }
//    }
//
//    static private class ByteArrayJsonWriter implements JsonWriter {
//        @Override
//        public void write(String fieldName, Object fieldObject, JsonObject jsonObject) {
//            JsonArray array = new JsonArray();
//            byte[] arr = (byte[]) fieldObject;
//            for (int i = 0; i < arr.length; i++)
//                array.add(arr[i]);
//            jsonObject.add(fieldName, array);
//        }
//    }
//
//
//}
