package cn.paypalm.test2;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年9月27日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年9月27日	新建文件.
 * 
 * </pre>
 */
public class Test {
	
	public  class TestNode{
		public int i;
	}
	
	public static void main(String[] args){
		String str="^[\\w\\-]{0,64}$";
		
		System.out.println("0.3".matches(str));
	}

}
