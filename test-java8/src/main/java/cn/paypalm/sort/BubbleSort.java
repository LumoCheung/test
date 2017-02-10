package cn.paypalm.sort;

import java.util.Arrays;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月1日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月1日	新建文件.
 * 
 * </pre>
 */
public class BubbleSort<T> extends Sort<T> {

	/**
	 * @param compareAble
	 */
	public BubbleSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[]) 
	 */ 
	@Override
	public T[] sortIndex(T[] t) {
		for(int i=0;i<t.length;i++){
			for(int j=0;j<t.length-1-i;j++){
				if(compareAble.compare(t[j], t[j+1])>0){
					T temp=t[j];
					t[j]=t[j+1];
					t[j+1]=temp;
				}
			}
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
		for(int i=0;i<t.length;i++){
			for(int j=0;j<t.length-1-i;j++){
				if(t[j]>t[j+1]){
					int tem=t[j];
					t[j]=t[j+1];
					t[j+1]=tem;
				}
			}
			System.out.println(Arrays.toString(t));
		}
		return t;
	}

}
