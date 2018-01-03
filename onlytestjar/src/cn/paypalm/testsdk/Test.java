package cn.paypalm.testsdk;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.paypalm.sdk.mer.api.PayPalmAPISDK;
import cn.paypalm.sdk.mer.common.XMLDocument;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年12月6日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年12月6日	新建文件.
 * 
 * </pre>
 */
public class Test {
	
	public static void main(String[] args) {
		Map<String, String> map =new HashMap<String, String>();
		map.put("merid", "201708041656");//20151126api
		map.put("productid", "SMZF0202");
		map.put("merorderno", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("opcode", "MA5001");
		map.put("meruserid", "MA5001");
		map.put("paystyle", "3");
		map.put("tranamt", "100");
		map.put("orderdesc", "测试");
		map.put("remark", "测试");
		map.put("notifyurl", "http://192.168.31.12:8064/pay-demo/merNotifyResult.jsp");
		map.put("merorderdate", "20171116");
		try {
			XMLDocument xml=PayPalmAPISDK.xmlRequest(map,true);
			System.out.println("1:"+xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		//String string="Fj/u4BE6w+vzODv+x+W1UkMD/3ReTyauV8oHqCt3fRseFuPoIzpqI0oTlfh6tBHZn7YgRMvNFuW+K1Pk3ulEPsSBBQt/CQVmL0n+m2CoMaK6888AkrY93OpZEzUvqs4RzVRdY3oGcg68cTmYwK8bPdKZpPi6keWY4RQa+djaO73w1LsqCtxjg5Il0lsKcnEePUVQBYPzdyjPJB8lWr4J2bmlBA/mO3b+D6Ai+kTtxoycZqBUaoeQyk/+71W3hz10svL6ATaxa1V/24Chlknjhro7zZJ21Z+LGNVJRSGTp2wBzZMsJA7bcK9gdS7M/G9uhgGtkHlT6GgDcKyfbxBFn7FC0nrzASx8bC180JU1X/r5twHlaH46aTTRqJlLv+qaPtMCFvyfXI1Wlzzq8rz18x+CSUtc74HRiKcPHZEdRkpDjQYEdNTdUk7SmzhGLpZ81rcHlv8B7c0ur5R5ajvqj+jV3/WzRE4lqKxcov5VwNUcj2Lm6N0EBZfsZOa8GGGO0I2uuWep2tS6LyG3iOY6IOGgu6Nn8nLbGeiWNfF5ZWDcSUf8v6tU4YFPWtScuYTziGuFv+EhKscQMGRKu09eEWPLaCKsBOBwZah/mhkM8VSTvY3DzMnIZLF25yQeVhoB0K+/o0dXrL8P02kzyu2Be/c2LEtelWCRlFHLhOjGd6Yxoa5Dh+2UO9BjiZE0vGL7ZbdmEQRRfKD9nF/G2vRjX6VvjZAI6dH6A+VlJxGn9BCHtPeBfnphATiq4kvS87xDl7r4tq391Qt5mXWhvxDJog5nw1PBOu14QfeXtzdsi/moSUtAhmGmEcjh/B5o1AgVRcR0WVpLuFONU0Wa6NbtKA==";
//		System.out.println(URLDecoder.decode(string,"utf-8"));
		
//		System.out.println(PayPalmAPISDK.getReturnData(string));
//		
//		string="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><paypalm><rspcode>000000</rspcode><rspdesc>成功</rspdesc><orderno>100001659814</orderno><payurl> weixin://wxpay/bizpayurl?pr=sEgAgVE </payurl></paypalm>";
//		System.out.println(PayPalmAPISDK.encryptData(string, PayPalmAPISDK.XML));
	}

}
