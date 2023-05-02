package com.edan.report.utils;

public class StringInspect {
    //如果是空的
    public static boolean Blank(String s)
    {
        if ("null".equals(s)||"".equals(s)||s==null)
            return  true;
        else
            return false;
    }
}
