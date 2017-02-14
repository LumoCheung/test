package cn.paypalm.sort;

import java.util.Arrays;

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
public class SelectSort <T> extends Sort<T>{

	/**
	 * @param compareAble
	 */
	public SelectSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[]) 
	 */ 
	@Override
	public T[] sortIndex(T[] t) {
		
		for(int i=0;i<t.length-1;i++){
			T min=t[i];
			int index=i;
			for(int j=i+1;j<t.length;j++){
				if(compareAble.compare(min, t[j])>0){
					index=j;
					min=t[j];
				}
			}
			t[index]=t[i];
			t[i]=min;
			System.out.println(Arrays.toString(t));
		}
		
		return t;
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(int[]) 
	 */ 
	@Override
	public int[] sortIndex(int[] t) {
		for(int i=0;i<t.length-1;i++){
			int min=t[i];
			int index=i;
			for(int j=i+1;j<t.length;j++){
				if(min>t[j]){
					index=j;
					min=t[j];
				}
			}
			t[index]=t[i];
			t[i]=min;
			System.out.println(Arrays.toString(t));
		}
		
		return t;
	}

}
