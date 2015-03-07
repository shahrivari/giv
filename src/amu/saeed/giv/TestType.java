package amu.saeed.giv;

import amu.saeed.giv.annotations.GivField;
import amu.saeed.giv.annotations.GivSkip;

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
    String skipthis;


    public static enum ENU {
        Apple
    }
}
