package cn.paypalm.text;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class StringTestFu {
	
	public static void stringFu(String string) throws UnsupportedEncodingException {
		string="���ֲ���«��";

		System.out.println(Arrays.toString(string.getBytes()));
		System.out.println(Arrays.toString(string.getBytes("gbk")));
		System.out.println(Arrays.toString(string.getBytes("utf-8")));
		System.out.println("//"+Charset.defaultCharset().name());
		System.out.println(System.getProperty("file.encoding"));
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		StringTestFu.stringFu(null);
	}

}
