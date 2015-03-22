package com.changh.sccms.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test5 {
	public static void main(String[] args) {
		
	String a = "";
	try
    {
        String c = new String(a.getBytes("iso8859-1"),"utf-8");
        System.out.println(Arrays.toString("A&B&C&".split("&")));
        String criteria = "select * from xxxxx";
        System.out.println(criteria.substring(criteria.indexOf("from")));
    }
    catch (UnsupportedEncodingException e)
    {
        e.printStackTrace();
    }
}
}