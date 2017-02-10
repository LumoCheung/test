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
public class ShellSort<T> extends Sort<T> {

	/**
	 * @param compareAble
	 */
	public ShellSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[]) 
	 */ 
	@Override
	public T[] sortIndex(T[] t) {
		
		int d=t.length/2;
		
		while(true){
			for(int i=0;i<t.length;i++){
				int j=i;
				T temp=t[i];
				for(;j>=d&&compareAble.compare(temp, t[j-d])<0;j-=d){
					t[j]=t[j-d];
				}
				t[j]=temp;
				System.out.println(Arrays.toString(t));
			}
			if(d==1) break;
			d=d/2;
			if(d%2==0) d++;
		}
		
		return t;
	}
	
	public int[] sortIndex(int [] t){
		int d=t.length/2;
		
		while(true){
			for(int i=0;i<t.length;i++){
				int j=i;
				int temp=t[i];
				for(;j>=d&&temp<t[j-d];j-=d){
					t[j]=t[j-d];
				}
				t[j]=temp;
				System.out.println(Arrays.toString(t));
			}
			if(d==1) break;
			d=d/2;
			if(d%2==0) d++;
		}
		return t;
	}
	
}
