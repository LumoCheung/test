package cn.paypalm.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年10月26日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2015年10月26日	新建文件.
 * 
 * </pre>
 */
public class ListTest {
	public static void main(String[] args)
	{
		List<String> list=Collections.synchronizedList(new ArrayList<String>());
		
		list=new LinkedList<String>();
		for(int i=0;i<10;i++){
			list.add(i, "str"+i);			
		}
		System.out.println(Arrays.toString(list.toArray()));
	}

}
