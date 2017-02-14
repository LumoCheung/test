package cn.paypalm.sort;

import java.util.Date;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月31日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月31日	新建文件.
 * 
 * </pre>
 */
public abstract class Sort<T> {
	
	CompareAble<T> compareAble;
	
	public Sort(CompareAble<T> compareAble){
		this.compareAble=compareAble;
	}
	
	public T[] sort(T[] t){
		Date begin=new Date();
		t=sortIndex(t);
		System.out.println("耗时："+(new Date().getTime()-begin.getTime())+"ms");
		return t;
	}
	
	public int[] sort(int[] t){
		Date begin=new Date();
		t=sortIndex(t);
		System.out.println("耗时："+(new Date().getTime()-begin.getTime())+"ms");
		return t;
	}
	
	public abstract T[] sortIndex(T[] t);
	
	public abstract int[] sortIndex(int[] t);
}
