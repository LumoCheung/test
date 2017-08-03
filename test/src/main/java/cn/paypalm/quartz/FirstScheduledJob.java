package cn.paypalm.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月2日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月2日	新建文件.
 * 
 * </pre>
 */
public class FirstScheduledJob extends QuartzJobBean {
	private AnotherBean anotherBean;
	/**
	 * <p>Description:</p>
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext) 
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("I am FirstScheduledJob");  
        this.anotherBean.printAnotherMessage();  
	}
	/**
	 * @return the anotherBean
	 */
	public AnotherBean getAnotherBean() {
		return anotherBean;
	}
	/**
	 * @param anotherBean the anotherBean to set
	 */
	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}
	
}
