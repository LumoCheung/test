package test.nio;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年12月27日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2015年12月27日	新建文件.
 * 
 * </pre>
 */
public class TimerClinet {
	public  static void main(String[] args){
		int port = 8090;
		
		new Thread(new TimeClientHandle("127.0.0.1", port),"client-001").start();
	}
}
