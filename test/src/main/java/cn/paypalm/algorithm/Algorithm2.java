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
public class Algorithm2 {
	
/**
* 背景：
煤球数目

有一堆煤球，堆成三角棱锥形。具体：
第一层放1个，
第二层3个（排列成三角形），
第三层6个（排列成三角形），
第四层10个（排列成三角形），
....
如果一共有100层，共有多少个煤球？

请填表示煤球总数目的数字。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。
*/

		public void name() {
			int k=1;
			int s=0;
			for(int i=1;i<=100;i++){
				s+=k;
				k+=(i+1);//i+1
			}
			System.out.println(s);
		}
/**
 * 生日蜡烛

某君从某年开始每年都举办一次生日party，并且每次都要吹熄与年龄相同根数的蜡烛。

现在算起来，他一共吹熄了236根蜡烛。

请问，他从多少岁开始过生日party的？

请填写他开始过生日party的年龄数。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。
 */
		public void name2() {
			for(int i=236;i>0;i--)
				for(int j=i-1;j>0;j--){
					if((i+j)*(i-j+1)/2==236){
						System.out.println(""+i+"-"+j);
					}
//					System.out.println((i+j)*(j-i+1)/2);
				}
		}
/**
 * 搭积木

小明最近喜欢搭数字积木，
一共有10块积木，每个积木上有一个数字，0~9。

搭积木规则：
每个积木放到其它两个积木的上面，并且一定比下面的两个积木数字小。
最后搭成4层的金字塔形，必须用完所有的积木。

下面是两种合格的搭法：

   0
  1 2
 3 4 5
6 7 8 9

   0
  3 1
 7 5 2
9 8 6 4    

请你计算这样的搭法一共有多少种？

请填表示总数目的数字。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。
 */
//		static int[] num=new int[10];
//		static int[] zhi=new int[10];
		static int s=0;
		public void huisu(int[] num,int x,int[] zhi){
			if(x==-1){
				s++;
				System.out.println(Arrays.toString(zhi));
			}
			int ceng=getCeng(x);
			for(int i=9;i>=0;i--){
				if(num[i]>-1&&(x>5//之后一行
						||(num[i]<zhi[x+ceng]&&num[i]<zhi[x+ceng+1]))){
					zhi[x]=num[i];
					num[i]=-1;
					huisu(num, x-1, zhi);
					num[i]=zhi[x];//还原
				}
			}
		}
		private int getCeng(int x){
			int i=1;
			int s=0;
			for(;i<5;i++){
				s+=i;
				if(x<s){
					break;
				}
			}
			return i;
		}
	
	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		int[] num=new int[10];
		int[] zhi=new int[10];
		for(int i=0;i<10;i++){
			num[i]=i;
			zhi[i]=0;
		}
		new Algorithm2().huisu(num, 9, zhi);
		System.out.println(s);
	}

}
