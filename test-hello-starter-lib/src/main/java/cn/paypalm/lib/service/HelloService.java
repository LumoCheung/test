package cn.paypalm.lib.service;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月14日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月14日	新建文件.
 * 
 * </pre>
 */
public class HelloService {
	
	private String msg;
	
	public String sayHello(){
		return "hello "+msg;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
