package cn.paypalm.file;

import java.util.Scanner;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年8月5日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路 2015年8月5日	新建文件.
 * 
 * </pre>
 */
public class Test2 {
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		String str=s.nextLine();
		String[] strs=str.split(",");
		int[] a=new int[strs.length];
		for(int i=0;i<strs.length;i++)
		{
			a[i]=Integer.parseInt(strs[i]);
		}
		System.out.println(add(a));
		
	}
	
	public static String add(int[] a)
	{
		int jl=a[1]-a[0];
		int flag=0;
		for(int i=1;i<a.length;i++)
		{
			if(jl>a[i]-a[i-1]) {
				jl=a[i]-a[i-1];
				flag=1;
			}
			if(jl<a[i]-a[i-1])
				flag=1;
		}
		int max=a[a.length-1];
		if(flag==0)
		{
			max=a[a.length-1]+jl;
		}
		StringBuffer s=new StringBuffer();
		s.append(a[0]);
		for(int i=a[0]+jl;i<=max;i+=jl)
		{
			s.append(","+i);
		}
		return s.toString();
	}

}
