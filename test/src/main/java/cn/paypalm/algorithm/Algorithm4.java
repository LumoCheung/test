package cn.paypalm.algorithm;

import javax.sql.rowset.Predicate;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年3月3日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年3月3日	新建文件.
 * 
 * </pre>
 */
public class Algorithm4 {
	
/**
 * 四平方和

四平方和定理，又称为拉格朗日定理：
每个正整数都可以表示为至多4个正整数的平方和。
如果把0包括进去，就正好可以表示为4个数的平方和。

比如：
5 = 0^2 + 0^2 + 1^2 + 2^2
7 = 1^2 + 1^2 + 1^2 + 2^2
（^符号表示乘方的意思）

对于一个给定的正整数，可能存在多种平方和的表示法。
要求你对4个数排序：
0 <= a <= b <= c <= d
并对所有的可能表示法按 a,b,c,d 为联合主键升序排列，最后输出第一个表示法


程序输入为一个正整数N (N<5000000)
要求输出4个非负整数，按从小到大排序，中间用空格分开

例如，输入：
5
则程序应该输出：
0 0 1 2

再例如，输入：
12
则程序应该输出：
0 2 2 2

再例如，输入：
773535
则程序应该输出：
1 1 267 838

 */
	
	public static boolean lagrange(int x){
		int a=0,b=0,c=0,d=0;
		double max=Math.sqrt(x);
		for(a=0;a<=max;a++)
			for(b=a;b<=max;b++)
				for(c=b;c<=max;c++)
					for(d=c;d<=max;d++){
						if(a*a+b*b+c*c+d*d==x){
							System.out.println(a+" "+b+" "+c+" "+d);
							return true;
						}
					}
		System.out.println("失败："+x);
		return false;
	}
	
/**
 * 交换瓶子

有N个瓶子，编号 1 ~ N，放在架子上。

比如有5个瓶子：
2 1 3 5 4

要求每次拿起2个瓶子，交换它们的位置。
经过若干次后，使得瓶子的序号为：
1 2 3 4 5

对于这么简单的情况，显然，至少需要交换2次就可以复位。

如果瓶子更多呢？你可以通过编程来解决。

输入格式为两行：
第一行: 一个正整数N（N<10000）, 表示瓶子的数目
第二行：N个正整数，用空格分开，表示瓶子目前的排列情况。

输出数据为一行一个正整数，表示至少交换多少次，才能完成排序。

例如，输入：
5
3 1 2 5 4

程序应该输出：
3

再例如，输入：
5
5 4 3 2 1

程序应该输出：
2
 */
	//不知道对否....
	public static int change(int[] params){
		int s=0;
		for(int i=0;i<params.length;i++){
			while(params[i]!=i+1){
				jiaohuan(params, params[i]-1, i);
				s++;
			}
		}
		return s;
	}
	
	private static void jiaohuan(int[] params,int x,int y){
		int z=params[x];
		params[x]=params[y];
		params[y]=z;
	}
/**
 * 最大比例

X星球的某个大奖赛设了M级奖励。每个级别的奖金是一个正整数。
并且，相邻的两个级别间的比例是个固定值。
也就是说：所有级别的奖金数构成了一个等比数列。比如：
16,24,36,54
其等比值为：3/2

现在，我们随机调查了一些获奖者的奖金数。
请你据此推算可能的最大的等比值。

输入格式：
第一行为数字 N (0<N<100)，表示接下的一行包含N个正整数
第二行N个正整数Xi(Xi<1 000 000 000 000)，用空格分开。每个整数表示调查到的某人的奖金数额

要求输出：
一个形如A/B的分数，要求A、B互质。表示可能的最大比例系数

测试数据保证了输入格式正确，并且最大比例是存在的。

例如，输入：
3
1250 200 32

程序应该输出：
25/4

再例如，输入：
4
3125 32 32 200

程序应该输出：
5/2

再例如，输入：
3
549755813888 524288 2

程序应该输出：
4/1
 */
	public long x=Long.MAX_VALUE;//x>=y
	public long y=Long.MAX_VALUE;
	public void aha(long[] a){
		for(int i=0;i<a.length-1;i++){
			if(a[i]==a[i+1]) continue;//相同的数字不计算
			long chu=zhanzhuan(a[i], a[i+1]);
			long zx=a[i]/chu;
			long zy=a[i+1]/chu;
			if(isABigger(zx, zy)&&zx<x){
				if(!isCon(zx, zy, x, y)) continue;
				x=zx;
				y=zy;				
			}else if(!isABigger(zx, zy)&&zy<x){	
				if(!isCon(zy, zx, x, y)) continue;
				x=zy;
				y=zx;
			}else if(isABigger(zx, zy)?isCon(x, y, zx, zy):isCon(x, y, zy, zx)){
				
			}
//			System.out.println(x+"/"+y);
		}
	}
	
	private long zhanzhuan(long a,long b){
		if(isABigger(a, b)){
			long z=b;
			b=a;
			a=z;
		}
		long chu=a;//取小值
		while (b%chu!=0) {
			chu=b%a;
			b=a;
			a=chu;
		}
		
		return chu;
	}
	
	private boolean isABigger(long a,long b){
		return a-b>0?true:false;
	}
	
	private boolean isCon(long x,long y, long xx,long yy){
		if(xx==Long.MAX_VALUE||yy==Long.MAX_VALUE||x==Long.MAX_VALUE||y==Long.MAX_VALUE){
			return true;
		}
		double l=Math.log(xx)/Math.log(x);
		long il=(long)l;//强转
		if(l==il) return true;//log(xx/yy)/log(x/y)==整数
		else{
			this.x=(long) (xx/Math.pow(x, il));
			this.y=(long) (yy/Math.pow(y, il));
		}
		return false;
	}
	
	/**
	 * 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */	
	public static void main(String[] args){
		/*for(int i=1;i<5000000;i++){
			if(!Algorithm4.lagrange(i)) 
				break;
		}*/
//		
//		Algorithm4.lagrange(12);
//		Algorithm4.lagrange(773535);
		
//		int[] a={3,1,2,5,4};
//		System.out.println(Algorithm4.change(a));
		
//		long a[]={125000000000L,20000000000L,3200000000L};
		long a[]={549755813888L,524288L,2L};
		Algorithm4 algorithm=new Algorithm4();
		algorithm.aha(a);
		System.out.println(algorithm.x+"/"+algorithm.y);
	}

}
