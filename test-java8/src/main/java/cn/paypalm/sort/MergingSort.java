package cn.paypalm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月3日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月3日	新建文件.
 * 
 * </pre>
 */
public class MergingSort<T> extends Sort<T> {

	/**
	 * @param compareAble
	 */
	public MergingSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[]) 
	 */ 
	@Override
	public T[] sortIndex(T[] t) {
		
		divide(t, 0, t.length-1);
		
		return t;
	}
	
	private void divide(T[] t,int first,int last){
		if(first<last){
			int center=(first+last)/2;
			divide(t, first, center);
			divide(t, center+1, last);
			
			merge(t, first, center, last);
			System.out.println(Arrays.toString(t));
		}
	}
	
	private void merge(T[] t,int first,int center,int last){
		int i=first,j=center+1,k=0;
		Object[] temp=new Object[last-first+1];
		while(i<center+1&&j<=last){
			if(compareAble.compare(t[i], t[j])<0){
				temp[k++]=t[i++];
			}else{
				temp[k++]=t[j++];
			}
		}
		
		if(i<center+1){
			while(i<center+1){
				temp[k++]=t[i++];
			}
		}else if(j<=last){
			while(j<=last){
				temp[k++]=t[j++];
			}
		}
		
		for(int z=0;z<temp.length;z++){
			t[first+z]=(T) temp[z];
		}
		
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(int[]) 
	 */ 
	@Override
	public int[] sortIndex(int[] t) {
		
		divide(t, 0, t.length-1);
		
		return t;
	}
	
	private void divide(int[] t,int first,int last){
		
		if(first<last){
			int center=(first+last)/2;
			divide(t, first, center);
			divide(t, center+1, last);
			
			merge(t, first, center, last);
			System.out.println(Arrays.toString(t));
		}
		
	}
	
	private void merge(int[] t,int first,int center,int last){
		
		//1.first->center
		//2.center+1->last
		
		int[] temp=new int[last-first+1];
		int i=first,j=center+1,k=0;
		
		while(i<center+1&&j<=last){
			if(t[i]<t[j]){
				temp[k++]=t[i++];
			}else{
				temp[k++]=t[j++];
			}
		}
		
		//1.i<center+1
		if(i<center+1){
			for(;i<center+1;i++){
				temp[k++]=t[i];
			}
		}else if(j<=last){
			for(;j<=last;j++){
				temp[k++]=t[j];
			}
		}
		
		///
		for(int z=0;z<temp.length;z++){
			t[first+z]=temp[z];
		}
	}

}
