package cn.paypalm.dubbo.api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;

/** 
 * <p> Description:  远程服务后，客户端通常只剩下接口，而实现全在服务器端，
 * 但提供方有些时候想在客户端也执行部分逻辑，比如：做ThreadLocal缓存，
 * 提前验证参数，调用失败后伪造容错数据等等，此时就需要在API中带上Stub，
 * 客户端生成Proxy实例，会把Proxy通过构造函数传给Stub，然后把Stub暴露组给用户，
 * Stub可以决定要不要去调Proxy。 ----dubbo 官网</p>
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
public class TestServiceStub implements TestService{
	private static final Logger log=LogManager.getLogger(TestServiceStub.class);
	
	private TestService testService;
	
	public TestServiceStub(TestService testService) {
		this.testService=testService;
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestService#Test(java.lang.String) 
	 */ 
	@Override
	public void Test(String demo) {
		log.debug("一盏离愁孤灯伫立在窗口");
		try {
			testService.Test(demo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
