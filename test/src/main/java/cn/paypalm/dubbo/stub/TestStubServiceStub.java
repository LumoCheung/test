package cn.paypalm.dubbo.stub;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cn.paypalm.dubbo.api.TestStubService;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年9月1日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年9月1日	新建文件.
 * 
 * </pre>
 */
public class TestStubServiceStub implements TestStubService{
	private static final Logger log=LogManager.getLogger(TestStubServiceStub.class);
	private TestStubService testStubService;
	
	/**
	 * 
	 */
	public TestStubServiceStub(TestStubService testStubService) {
		this.testStubService=testStubService;
	}
	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestStubService#test(java.lang.String) 
	 */ 
	@Override
	public void test(String name) {
		log.debug("旧地如重游月圆更寂寞");
		
		testStubService.test(name);
	}

}
