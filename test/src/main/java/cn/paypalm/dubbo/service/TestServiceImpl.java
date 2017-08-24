package cn.paypalm.dubbo.service;

import org.springframework.stereotype.Service;

import cn.paypalm.dubbo.api.TestService;

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
public class TestServiceImpl implements TestService{

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.dubbo.api.TestService#Test(java.lang.String) 
	 */ 
	@Override
	public void Test(String demo) {
			System.out.println(demo);
	}

}
