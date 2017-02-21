package cn.paypalm.algorithm;

import java.util.Arrays;
import java.util.Random;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月21日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月21日	新建文件.
 * 
 * </pre>
 */
public class Algorithm1 {
	
	/**
	 * 背景：
	 * 有一组数字，正常情况下这组数字呈标准的等差数列排列，公差为d，首项为a1，末项为an.
	 * 如果在正常情况下从这组数字中随机抽取出N个数字，求如何快速找到缺失的数字。
	 * （d,a1,an,N为已知值）
	 */
	
	static int[] c={1,2,3,4,5,6,7,8,9,10};
	
	static int N=3;
	
	static int a1=1;
	
	static int an=10;
	
	static int d=1;
	
	static int n=(an-a1)/d+1;
	
	static int[] a=new int[n-N];
	/**
	 * <h1>失败<h1>
	 * zheban
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param begin 开始坐标+1
	 * @param end 结束坐标+1
	 * @param count 去除的数目
	 * @since Ver 1.00
	 */
	public void zheban(int begin,int end,int count){
		
		if(end-begin<2){
			int rz=(a[begin-1]-a1)/d+1;
			for(int i=1;i<=count;i++)
				System.out.print(rz+count+" ");
			return ;
		}
		
		int m=0;//偏移量
		int y=((a[begin-1]+a[end-1])/2-a1)/d+1;//
		int z=(begin+end)/2;
		
		//System.out.println(""+z+"/"+y);
		
		m=y-z;
		//以z为分界线，右面N/2+m
		int r=count/2+m;
		int l=count-m;
		
		if(r>0)
			zheban(z, end, r);
		if(l>0)
			zheban(begin, z, l);
	}
	/**
	 * 通过前后数据差值比较，确实缺失值
	 * bianli
	 * 方法描述: 
	 * 逻辑描述: 
	 * @since Ver 1.00
	 */
	public void bianli(){
		for(int i=a1;i<a[0];i+=d){
			System.out.print(1+(i-a1)/d+" ");
		}
		for(int i=0;i<a.length-1;i++){
			if(a[i+1]-a[i]>d){
				int r=(a[i+1]-a[i])/d;
				for(int j=1;j<r;j++)
					System.out.print((a[i]-a1)/d+1+j+" ");
			}
		}
		for(int i=a[a.length-1];i<an;i+=d){
			System.out.print(a.length+(i-a[a.length-1])/d+" ");
		}
	}
	
	public void duibi(){
		/*int[] y=new int[(an-a1)/d+1];
		y[0]=a1;
		for(int i=1;i<100;i++){
			y[i]=y[i-1]+d;
		}*/
		
		int k=0;
		for(int i=0;i<n;i++){
			if(a[k] != a1+i*d){
				System.out.print(i+1+" ");
			}
			else {
				k++;
			}
		}
	}
	
	/**
	 * 数据初始化
	 * init
	 * 方法描述: 
	 * 逻辑描述: 
	 * @since Ver 1.00
	 */
	public void init(){
		c=new int[100];
		c[0]=a1=1;
		d=24;
		an=a1+99*d;
		for(int i=1;i<100;i++){
			c[i]=c[i-1]+d;
		}
		N=10;
		n=100;
		a=new int[n-N];
		Random r=new Random();
		for(int i=0;i<N;i++){
			int z=r.nextInt(n-1);
			while(c[z]==Integer.MIN_VALUE){
				z=r.nextInt(n-1);
			}
			System.out.print(z+1+" ");
			c[z]=Integer.MIN_VALUE;
		}
		System.out.println();
		int k=0;
		for(int i=0;i<c.length;i++){
			if(c[i]!=Integer.MIN_VALUE){
				a[k++]=c[i];
			}
		}
		System.out.println(Arrays.toString(a));
	}
	
	public static void main(String[] args){
		Algorithm1 one=new Algorithm1();
		one.init();
//		one.zheban(1, a.length, N);
//		one.bianli();
		one.duibi();
	}
}
