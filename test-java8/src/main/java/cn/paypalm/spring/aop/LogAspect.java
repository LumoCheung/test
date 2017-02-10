package cn.paypalm.spring.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/** 
 * <p> Description:  切面</p>
 * @Author zhangzilu
 * @Date [2017年1月13日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月13日	新建文件.
 * 
 * </pre>
 */
@Aspect
@Component
public class LogAspect {
	@Pointcut("@annotation(cn.paypalm.spring.aop.Action)")
	public void annotationPointCut(){}
	
	@After("annotationPointCut()")
	public void after(JoinPoint joinPoint){
		MethodSignature signature=(MethodSignature) joinPoint.getSignature();
		Method method=signature.getMethod();
		Action action=method.getAnnotation(Action.class);
		System.out.println("注解式拦截 "+action.name());
		
	}
	
	@Before("execution(* cn.paypalm.spring.aop.DemoMethodService.*(..))")
	public void before(JoinPoint joinPoint){
		MethodSignature signature=(MethodSignature) joinPoint.getSignature();
		Method method=signature.getMethod();
		System.out.println("方法规则式拦截，"+method.getName());
	}
}
