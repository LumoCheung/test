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
public class HeapSort<T> extends Sort<T> {

	/**
	 * @param compareAble
	 */
	public HeapSort(CompareAble<T> compareAble) {
		super(compareAble);
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(java.lang.Object[]) 
	 */ 
	@Override
	public T[] sortIndex(T[] t) {
		for(int i=0;i<t.length-1;i++){
			buildHeap(t, t.length-1-i);
			swap(t, 0, t.length-1-i);
			System.out.println(Arrays.toString(t));
		}
		return t;
	}
	
	private T[] buildHeap(T[] t,int lastIndex){
		
		for(int i=(lastIndex-1)/2;i>=0;i--){
			int k=i;
			while(k*2+1<=lastIndex){
				int index=k*2+1;
				if(index+1<=lastIndex&&compareAble.compare(t[index],t[index+1])<0){
					index++;
				}
				if(compareAble.compare(t[k], t[index])<0){
					swap(t, k, index);
					k=index;
				}else{
					break;
				}
			}
		}
		
		return t;
	}
	
	private void swap(T[] t,int a,int b){
		T temp=t[a];
		t[a]=t[b];
		t[b]=temp;
	}

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.sort.Sort#sortIndex(int[]) 
	 */ 
	@Override
	public int[] sortIndex(int[] t) {
		
		int arrayLength=t.length;
		for(int i=0;i<arrayLength-1;i++){
			buildMaxHeap(t,arrayLength-1-i);
			swap(t, 0, arrayLength-1-i);
			System.out.println(Arrays.toString(t));
		}
		
		return t;
	}
	
	private int[] buildMaxHeap(int[] t, int lastIndex){
		
		for(int i=(lastIndex-1)/2;i>-1;i--){
			int k=i;
			
			while(2*k+1<=lastIndex){
				int index=2*k+1;
				if(index<lastIndex&&t[index]<t[index+1]){
					index++;
				}
				if(t[k]<t[index]){
					swap(t, k, index);
					k=index;
				}else{
					break;
				}
			}
			
			/**这种算法违反了堆的定义
			int index=2*i+1;
			if(index<lastIndex&&t[index]<t[index+1]){
				index++;
			}
			if(t[i]<t[index])
				swap(t, i, index);*/
		}
		
		return t;
	}

	private  void swap(int[] data, int i, int j) { 
		int tmp=data[i];
		data[i]=data[j];
		data[j]=tmp;
	}   
}
