package amu.saeed.giv;

import amu.saeed.giv.annotations.GivField;
import amu.saeed.giv.annotations.GivSkip;

import java.util.*;

/**
 * Created by Saeed on 3/6/2015.
 */
public class TestType {
    public ENU tt = ENU.Apple;
    public int id;
    public Integer ID;
    @GivField(name = "nm")
    public String name = "Tat\ntat \r lloo http://alalk.com/asdasd/asdasda/s?=&43 <html></html>\"''";
    //@GivField(type = GivField.DataType.BASE64_BYTE_ARRAY)
    public byte[] raw = new byte[]{53, 54};
    @GivSkip
    Set<Integer> set = new HashSet<Integer>() {{
        add(1);
        add(2);
    }};
    Set<Set<Integer>> mset = new HashSet<Set<Integer>>() {{
        add((Set) new HashSet<Integer>());
    }};
    Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(1, "sa");
    }};
    Date date = new Date();
    @GivSkip
    String skipthis;


    public static enum ENU {
        Apple
    }
}
