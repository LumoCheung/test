package cn.paypalm.search;

import java.util.Date;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月14日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月14日	新建文件.
 * 
 * </pre>
 */
public abstract class Base {
	
	public boolean search(int[] t,int key){
		
		Date begin=new Date();
		boolean b=searchChild(t,key);
		System.out.println("耗时："+(new Date().getTime()-begin.getTime())+"ms");
		
		return b;
	}
	
	public abstract boolean searchChild(int[] t,int key);

}
