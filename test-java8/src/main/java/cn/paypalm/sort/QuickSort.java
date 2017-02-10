package cn.paypalm.sort;

import java.util.Arrays;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月2日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月2日	新建文件.
 * 
 * </pre>
 */
public class QuickSort <T> extends Sort<T>{

	/**
	 * @param compareAble
	 */
	public QuickSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[]) 
	 */ 
	@Override
	public T[] sortIndex(T[] t) {
		quick(t, 0, t.length-1);
		return t;
	}
	
	private int getMiddle(T[] t,int begin,int last){
		T temp=t[begin];
		while(begin<last){
			while(begin<last&&compareAble.compare(temp, t[last])<=0){
				last--;
			}
			t[begin]=t[last];
			while(begin<last&&compareAble.compare(t[begin], temp)<=0){
				begin++;
			}
			t[last]=t[begin];
		}
		t[last]=temp;
		System.out.println(Arrays.toString(t));
		return last;
	}
	private void quick(T[] t,int begin,int last){
		if(begin<last){
			int middle=getMiddle(t, begin, last);
			quick(t, begin, middle-1);
			quick(t, middle+1, last);
		}
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(int[]) 
	 */ 
	@Override
	public int[] sortIndex(int[] t) {
		quick(t, 0, t.length-1);
		return t;
	}
	
	private int getMiddle(int[] t,int begin,int last){
		int temp=t[begin];
		while(begin<last){
			while(begin<last&&temp<=t[last]){
				last--;
			}
			t[begin]=t[last];
			while(begin<last&&temp>=t[begin]){
				begin++;
			}
			t[last]=t[begin];
		}
		t[begin]=temp;
		System.out.println(Arrays.toString(t));
		return last;
	}
	
	private void quick(int[] t,int begin,int last){
		if(begin<last){
			int middle=getMiddle(t, begin, last);
			quick(t, begin, middle-1);
			quick(t, middle+1, last);
		}
	}

}
