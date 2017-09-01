package cn.paypalm.dubbo.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.paypalm.dubbo.api.TestService;
import cn.paypalm.dubbo.api.TestStubService;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月24日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月24日	新建文件.
 * 
 * </pre>
 */
@Service("testServiceTwo")
public class TestServiceImplTwo implements TestService,TestStubService{
	private static final Logger log = LogManager.getLogger(TestServiceImplTwo.class);
	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestService#Test(java.lang.String) 
	 */ 
	@Override
	public void Test(String demo) {
		log.debug("谁在用琵琶弹奏一曲东风破");
		System.out.println(demo);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestStubService#test(java.lang.String) 
	 */ 
	@Override
	public void test(String name) {
		log.debug("犹记得那年我们都还很年幼");
		System.out.println(name);
	}

}
