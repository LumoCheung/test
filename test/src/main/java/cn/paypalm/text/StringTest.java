package cn.paypalm.text;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String string="«ɽ";
		
		byte[] sb=string.getBytes("gbk");
		
		System.out.println(new String(sb, 0, sb.length,"utf-8"));
		
		System.out.println(Arrays.toString(new String(sb, 0, sb.length,"utf-8").getBytes("utf-8")));
		System.out.println(Arrays.toString(string.getBytes("gbk")));
//		System.out.println(Arrays.toString(string.getBytes("utf-8")));
		
		System.out.println("=========================");
		
//		StringTestFu.stringFu(null);
	}

}
