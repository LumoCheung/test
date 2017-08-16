package cn.paypalm.date;

import java.util.Arrays;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年8月14日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路 2015年8月14日	新建文件.
 * 
 * </pre>
 */
public class KMP {

	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args 
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s2="abcdfx";
		int[] next=new int[s2.length()]; 
		for(int i=0;i<s2.length();i++)
		{
			if(i==0)
			{
				next[i]=0;
				continue;
			}
			
			int k=i/2;
			if(k<1)
			{
				next[i]=0;
			}
			else {
				String str1="";
				String str2=" ";
				while(!str1.equals(str2))
				{
					str1=s2.substring(0,k);
					str2=s2.substring(i-k,i);
					//System.out.println(i+" "+str1+" "+str2);
					if(k<1)
					{
						next[i]=0;
					}
					else if(str1.equals(str2)){
						next[i]=k;
					}
					else {
						k--;
					}
				}
			}
		}
		System.out.println(Arrays.toString(next));
		
		String s1="abcabcabx";
		int j=0;
		System.out.println("debug"+s1.charAt(6)+true+s2.charAt(2));	
		for(int i=0;i<s1.length();i++)
		{
			if(s1.charAt(i)==s2.charAt(j))
			{
				j++;
				if(j==s2.length())
				{
					System.out.println(i+" "+j);
					break;
				}			
			}
			else {
				j=next[j];
				i--;
			}
		}
	}

}
