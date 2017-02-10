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
public class MainTest {
	public static void main(String[] args){
		Integer a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		Integer b[]={1};
		int[] t={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		
		Sort<Integer> sort=new MergingSort<Integer>(new CompareAble<Integer>() {

			public int compare(Integer a, Integer b) {
				if(a>b) return 1;
				if(a<b) return -1;
				return 0;
			}
		});
		sort.sort(a);
		sort.sort(t);
//		sort.sort(b);
	}

}
