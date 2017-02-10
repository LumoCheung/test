package cn.paypalm.search;

import java.io.UnsupportedEncodingException;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月14日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月14日	新建文件.
 * 
 * </pre>
 */
public class Main {

	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		Base search = new BiTree();
		
		int[] t={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		
		//System.out.println(search.search(t, 35));
		
		try {
			System.out.println(isUTF8("aaaa".getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	* UTF-8编码格式判断
	* 
	* @param rawtext
	*            需要分析的数据
	* @return 是否为UTF-8编码格式
	*/
	public static boolean isUTF8(byte[] rawtext) {
	   int score = 0;
	   int i, rawtextlen = 0;
	   int goodbytes = 0, asciibytes = 0;
	   // Maybe also use UTF8 Byte Order Mark: EF BB BF
	   // Check to see if characters fit into acceptable ranges
	   rawtextlen = rawtext.length;
	   for (i = 0; i < rawtextlen; i++) {
	    if ((rawtext[i] & (byte) 0x7F) == rawtext[i]) { 
	     // 最高位是0的ASCII字符
	     asciibytes++;
	     // Ignore ASCII, can throw off count
	    } else if (-64 <= rawtext[i] && rawtext[i] <= -33
	      //-0x40~-0x21
	      && // Two bytes
	      i + 1 < rawtextlen && -128 <= rawtext[i + 1]
	      && rawtext[i + 1] <= -65) {
	     goodbytes += 2;
	     i++;
	    } else if (-32 <= rawtext[i]
	      && rawtext[i] <= -17
	      && // Three bytes
	      i + 2 < rawtextlen && -128 <= rawtext[i + 1]
	      && rawtext[i + 1] <= -65 && -128 <= rawtext[i + 2]
	      && rawtext[i + 2] <= -65) {
	     goodbytes += 3;
	     i += 2;
	    }
	   }
	   if (asciibytes == rawtextlen) {
	    return false;
	   }
	   score = 100 * goodbytes / (rawtextlen - asciibytes);
	   // If not above 98, reduce to zero to prevent coincidental matches
	   // Allows for some (few) bad formed sequences
	   if (score > 98) {
	    return true;
	   } else if (score > 95 && goodbytes > 30) {
	    return true;
	   } else {
	    return false;
	   }
	}

}
