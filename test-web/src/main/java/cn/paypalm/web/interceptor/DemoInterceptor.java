package cn.paypalm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月20日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月20日	新建文件.
 * 
 * </pre>
 */
@Component
public class DemoInterceptor  extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		long startTime=System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception{
		long startTime=(long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime=System.currentTimeMillis();
		System.out.println("本次请求处理时间为："+new Long(endTime-startTime)+"ms");
		request.setAttribute("handlingTime", endTime-startTime);
	}
}
