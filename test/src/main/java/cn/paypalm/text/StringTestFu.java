package cn.paypalm.text;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringTestFu {
	
	public static void stringFu(String string) throws UnsupportedEncodingException {
		string="ºº×Ö²âÊÔÂ«»¨";

		System.out.println(Arrays.toString(string.getBytes()));
		System.out.println(Arrays.toString(string.getBytes("gbk")));
		System.out.println(Arrays.toString(string.getBytes("utf-8")));
	}

}
