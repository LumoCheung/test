package cn.paypalm.sort;

import java.util.Arrays;
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
public class InsertSort<T>  extends Sort<T>{
	
	/**
	 * @param compareAble
	 */
	public InsertSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * 
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[])
	 */
	@Override
	public T[] sortIndex(T[] t) {
		
		T temp=null;
		for(int i=1;i<t.length;i++){
			temp=t[i];
			int j=i;
			for(;j>0&&compareAble.compare(temp,t[j-1])<0;j--){
				//这里使用的是交换，实际上是应该全部后移
				t[j]=t[j-1];
				//t[j-1]=temp;//这不不需要
			}
			t[j]=temp;
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
		int temp;
		for(int i=1;i<t.length;i++){
			temp=t[i];
			int j=i;
			for(;j>0&&temp<t[j-1];j--){
				//这里使用的是交换，实际上是应该全部后移
				t[j]=t[j-1];
				//t[j-1]=temp;//这不不需要
			}
			t[j]=temp;
			System.out.println(Arrays.toString(t));
		}		
		return t;
	}
}
