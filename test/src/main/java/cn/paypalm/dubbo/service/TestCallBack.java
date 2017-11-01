package cn.paypalm.dubbo.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年9月4日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年9月4日	新建文件.
 * 
 * </pre>
 */
@Service
public class TestCallBack {
	private static final Logger log = LogManager.getLogger(TestCallBack.class);
	public void onreturn(String res,String param) {//第一个参数是返回的参数，void返回null//第二个之后的参数为入参
		log.debug("return:谁在用琵琶弹奏一曲东风破");
		System.out.println(res+"/"+param);
	}
	
	public void onthrow(Throwable e,String param) {
		//第一个是异常//第二个之后是入参
	}
	
	public void oninvoke(String param) {
		log.debug("测试");
	}
	
}
