package cn.paypalm.uss;

import java.util.HashMap;
import java.util.Map;

import cn.paypalm.uss.util.HttpInformServicePost;
import cn.paypalm.uss.util.UJackson;
import cn.paypalm.uss.util.UString;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年11月4日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年11月4日	新建文件.
 * 
 * </pre>
 */
public class Test {
	
	public static final String send_url="https://qyapi.weixin.qq.com/cgi-bin/message/send";
	
	public static final String access_url="https://qyapi.weixin.qq.com/cgi-bin/gettoken";
	
	public static final String corpId="wx80ea5f9ed691fa6c";
	
	public static final String secret="KiI5lxaRKFx6VieCaTnPnApZllVIPNDMd-PNYKbtqBop4bQDy3IwSJfEb82TReaV";
	
	public static void main(String[] args) throws Exception{
		
		String url="";		
		url=access_url+"?corpid="+corpId+"&corpsecret="+secret;		
		String resp=HttpInformServicePost.request(url, "", false);
		
		Map<String, Object> rep=UJackson.getMap(resp);
		if(!UString.isEmpty(rep.get("errcode"))){
			System.out.println(rep);
		}
		String access_token=String.valueOf(rep.get("access_token"));
		url=send_url+"?access_token="+access_token;
		String sendMsg="接口发送test--来自PC";
		Map<String, Object> req=new HashMap<String, Object>();
//		req.put("touser", "");
		req.put("toparty", "1");
//		req.put("totag","");
		req.put("msgtype", "text");
		req.put("agentid", 0);
		Map<String, Object> content=new HashMap<String, Object>();
		content.put("content", sendMsg);
		req.put("text", content);
		req.put("safe", 0);
		
		resp=HttpInformServicePost.request(url, UJackson.getJSONStr(req), true);
		
		System.out.println(resp);
	}
}
