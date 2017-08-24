package cn.paypalm.dubbo.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年4月6日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年4月6日	新建文件.
 * 
 * </pre>
 */
@Component
public class SpringContext implements ApplicationContextAware {
	private ApplicationContext ctx;

	/**
	 * <p>Description:</p>
	 * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
	 */ 
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx=ctx;
	}
	
	public ApplicationContext getContext(){
		return ctx;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name){
		Object bean=ctx.getBean(name);
		return bean==null?null:(T)bean;
	}

}
