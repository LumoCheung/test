package cn.paypalm.algorithm;

import java.util.Arrays;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月22日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月22日	新建文件.
 * 
 * </pre>
 */
public class Algorithm3 {
/**
 * 
凑算式

         B       DEF
A + --- + ------- = 10
         C       GHI
     
（如果显示有问题，可以参见【图1.jpg】）
	 
	 
这个算式中A~I代表1~9的数字，不同的字母代表不同的数字。

比如：
6+8/3+952/714 就是一种解法，
5+3/1+972/486 是另一种解法。

这个算式一共有多少种解法？

注意：你提交应该是个整数，不要填写任何多余的内容或说明性文字。

 */
	static int s=0;
	public void huisu(int[] num,int x,int[] zhi){
		if(x==-1){
			if(zhi[0]+((double)zhi[1])/zhi[2]+((double)(zhi[3]*100+zhi[4]*10+zhi[5]))/(zhi[6]*100+zhi[7]*10+zhi[8])==10){
				s++;
				System.out.println(Arrays.toString(zhi));
			}
		}
		for(int i=8;i>=0;i--){
			if(num[i]>-1){
				zhi[x]=num[i];
				num[i]=-1;
				huisu(num, x-1, zhi);
				num[i]=zhi[x];//还原
			}
		}
	}
	
	public static void main1(){
		int[] num=new int[9];
		int[] zhi=new int[9];
		for(int i=0;i<9;i++){
			num[i]=i+1;
			zhi[i]=-1;
		}
		new Algorithm3().huisu(num, 8, zhi);
		System.out.println(s);
	}

/**
 * 抽签

X星球要派出一个5人组成的观察团前往W星。
其中：
A国最多可以派出4人。
B国最多可以派出2人。
C国最多可以派出2人。
D国最多可以派出1人。
E国最多可以派出1人。
F国最多可以派出3人。

那么最终派往W星的观察团会有多少种国别的不同组合呢？

下面的程序解决了这个问题。
数组a[] 中既是每个国家可以派出的最多的名额。
程序执行结果为：
DEFFF
CEFFF
CDFFF
CDEFF
CCFFF
CCEFF
CCDFF
CCDEF
BEFFF
BDFFF
BDEFF
BCFFF
BCEFF
BCDFF
BCDEF
....
(以下省略，总共101行)
	
 */
	static int ss=0;
	public void f(int a[], int k, int m, char b[])
	{
		int i,j;
		
		if(k==6){ 
//			b[5] = '\0';
			if(m==0) {
				System.out.print(new String(b, 0, 5)+"\n");
				ss++;
			}
			return;
		}
		
		for(i=0; i<=a[k]; i++){
			for(j=0; j<i; j++) 
				b[5-m+j] = (char) (k+'A');
			f(a, k+1, m-j, b);  //填空位置
		}
	}
	
	public static void main2() {
		int[] a = {4,2,2,1,1,3};
		char[] b=new char[1024];
		new Algorithm3().f(a,0,5,b);	
		System.out.println(ss);
	}
	
	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		
	}
}
