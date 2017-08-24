package cn.paypalm.io;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月23日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月23日	新建文件.
 * 
 * </pre>
 */
public class HttpUtil {
	public static String compositeRequest(String host){

        return "GET / HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: curl/7.43.0\r\n" +
                "Accept: */*\r\n\r\n";
    }
}
