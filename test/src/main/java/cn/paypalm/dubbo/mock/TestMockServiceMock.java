package cn.paypalm.dubbo.mock;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cn.paypalm.dubbo.api.TestMockService;

/** 
 * <p> Description:  Mock通常用于服务降级，比如某验权服务，当服务提供方全部挂掉后，
 * 客户端不抛出异常，而是通过Mock数据返回授权失败。
 * 
 * warningMock是Stub的一个子集，便于服务提供方在客户端执行容错逻辑，
 * 因经常需要在出现RpcException(比如网络失败，超时等)时进行容错，
 * 而在出现业务异常(比如登录用户名密码错误)时不需要容错，如果用Stub，
 * 可能就需要捕获并依赖RpcException类，而用Mock就可以不依赖RpcException，
 * 因为它的约定就是只有出现RpcException时才执行。</p>
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
public class TestMockServiceMock implements TestMockService{
	private static final Logger log = LogManager.getLogger(TestMockServiceMock.class);
	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestMockService#test(java.lang.String) 
	 */ 
	@Override
	public void test(String name) {
		log.debug("水向东流时间怎么偷");//岁月在墙上剥落看见小时候
		System.out.println("mock调用："+name);
	}

}
