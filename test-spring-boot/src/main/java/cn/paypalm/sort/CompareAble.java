package cn.paypalm.sort;

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
public interface CompareAble<T> {
	/**
	 * 
	 * compare
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param a
	 * @param b
	 * @return a==b 0;a>b 1;a<b -1;
	 * @since Ver 1.00
	 */
	public int compare(T a,T b);
}
