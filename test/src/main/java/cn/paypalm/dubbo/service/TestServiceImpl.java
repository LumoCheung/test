package cn.paypalm.dubbo.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.paypalm.dubbo.api.TestCallBackService;
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
@Service("testService")
public class TestServiceImpl implements TestService,TestStubService,TestCallBackService{
	private static final Logger log = LogManager.getLogger(TestServiceImpl.class);
	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestService#Test(java.lang.String) 
	 */ 
	@Override
	public void Test(String demo) {
			System.out.println(demo);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestStubService#test(java.lang.String) 
	 */ 
	@Override
	public void test(String name) {
		log.debug("枫叶将故事染色结局我看透");
		System.out.println(name);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestCallBackService#call(java.lang.String) 
	 */ 
	@Override
	public String call(String msg) {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("犹记得那年我们都还很年幼");
		System.out.println("而如今琴声幽幽我的等候你没听过");
		return "枫叶将故事染色结局我看透";
	}

}
