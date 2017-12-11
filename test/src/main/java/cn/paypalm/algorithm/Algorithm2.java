package cn.paypalm.algorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

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
		public static void main2() {
			int[] num=new int[10];
			int[] zhi=new int[10];
			for(int i=0;i<10;i++){
				num[i]=i;
				zhi[i]=0;
			}
			//倒序
			new Algorithm2().huisu(num, 9, zhi);
			System.out.println(s);
		}
		/**
		 * 方格填数

		如下的10个格子
		      +--+--+--+
		      |     |     |     |
		+--+--+--+--+
		|     |     |     |     |
		+--+--+--+--+
		|     |     |     |
		+--+--+--+

		（如果显示有问题，也可以参看【图1.jpg】）

		填入0~9的数字。要求：连续的两个数字不能相邻。
		（左右、上下、对角都算相邻）

		一共有多少种可能的填数方案？

		请填写表示方案数目的整数。
		注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。
		 */
		
		public void huisu2(int[] num,int x,int[] zhi){
			if(x>11){
				s++;
				System.out.println(toString(zhi));
				return;
			}else if(zhi[x]==Integer.MIN_VALUE){
				huisu2(num, x+1, zhi);
				return;
			}
			for(int i=9;i>=0;i--){
				if(num[i]>-1&&guize(x,zhi,num[i])){
					zhi[x]=num[i];
					num[i]=-1;
					huisu2(num, x+1, zhi);
					num[i]=zhi[x];//还原
				}
			}
		}
		private boolean guize(int x,int[] zhi,int t){
			if(x%4!=0 //不是第一个
					&&x-1>-1&&Math.abs(zhi[x-1]-t)<2){//左边
				return false;
			}
			if(x-4>-1&&Math.abs(zhi[x-4]-t)<2){//上边
				return false;
			}
			if(x-5>-1&&x%4!=0&&Math.abs(zhi[x-5]-t)<2){//左上
				return false;
			}
			if(x-3>-1&&x%4!=3&&Math.abs(zhi[x-3]-t)<2){//右上
				return false;
			}
			return true;
		}
		public static String toString(int[] a){
			StringBuffer sb=new StringBuffer();
			sb.append("[");
			for(int i=0;i<a.length;i++){
				if(a[i]!=Integer.MIN_VALUE) sb.append(a[i]).append(",");
			}
			return sb.toString().substring(0, sb.length()-1)+"]";
		}
		public static void main3() {
			int[] num=new int[10];
			int[] zhi=new int[12];
			for(int i=0;i<10;i++){
				num[i]=i;
				zhi[i+1]=0;
			}
			zhi[0]=zhi[11]=Integer.MIN_VALUE;
			//正序
			new Algorithm2().huisu2(num, 0, zhi);
			System.out.println(s);
		}

/**
 * 剪邮票

如【图1.jpg】, 有12张连在一起的12生肖的邮票。

1		2		3		4
5		6		7		8
9		10	11	12

现在你要从中剪下5张来，要求必须是连着的。
（仅仅连接一个角不算相连）
比如，【图2.jpg】，【图3.jpg】中，粉红色所示部分就是合格的剪取。

请你计算，一共有多少种不同的剪取方法。

请填写表示方案数目的整数。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。
 */
		private class Node{
			int r;//纵
			int c;//横
			/**
			 * <p>Description:</p>
			 * @see java.lang.Object#toString() 
			 */ 
			@Override
			public String toString() {
				return "Node [r=" + r + ", c=" + c + "]";
			}
			
		}
		
		public void huisu3(int[][] you,int n, int x, int y,Node[] chu){
			if(n==chu.length){
					if(guize(chu)){
					s++;
					System.out.println(Arrays.toString(chu));
				}
				return;
			}
			//这种做法是全排列，数目是12*11*10*9*8=95 040，数据量过大，最好的做法是实际上是12*11*10*9*8/(5*4*3*2*1)=792
			//大大减少guize()判断的次数
			//做法是只取当前邮票后面的邮票
			for(int i=x;i<3;i++){
				int j=0;
				if(x==i){
					j=y;
				}
				for(;j<4;j++){
				
				//新做法：任意五张邮票先取出，取完之后判断，一次遍历，保证不重复取出
				
				if(you[i][j]>-1&&!(i<x&&j<y)){
					Node node=new Node();
					node.r=i;
					node.c=j;
					chu[n]=node;
					you[i][j]=-1;
					huisu3(you, n+1, i, j, chu);
					chu[n]=null;
					you[i][j]=0;//还原
				}

			}
				/*XXX 回溯失败 2017年2月24日19:05:42
				if(you[i][j]>-1&&guize(you, x,i,j ,chu)){
					Node node=new Node();
					node.r=i;
					node.c=j;
					chu[x]=node;
					you[i][j]=-1;
					huisu3(you, x+1, chu);
					chu[x]=null;
					you[i][j]=0;//还原
				}*/
			}
		}
		
		private class NodeChu{
//			int i;
			boolean is;
			List<Integer> nexts=new ArrayList<Integer>();
		}
		
		private boolean guize(Node[] chu){
			
			// 最难的是判定五张邮票相连，最开始的想法是递归判断，时间消耗太严重
			NodeChu[] nodes=new NodeChu[5];
			for(int i=0;i<5;i++){
				nodes[i]=new NodeChu();
			}
			//1. 构建邻接链表，擂台法
			for(int i=0;i<chu.length-1;i++){
				for(int j=i+1;j<chu.length;j++){
					if((Math.abs(chu[i].r-chu[j].r)<2&&chu[i].c==chu[j].c)
							||(Math.abs(chu[i].c-chu[j].c)<2&&chu[i].r==chu[j].r)){
						nodes[i].nexts.add(j);
					}
				}
			}
			
			//2. 伪遍历邻接链表(改造)
//			int ss=0;//长度
			boolean is=is(nodes);

			if(!is){
				is= is(nodes);
			}			
			/*if(chu[0].r==0&&chu[0].c==0&&chu[1].r==0&&chu[1].c==2){
				System.out.println(Arrays.toString(chu));
				for(int i=0;i<nodes.length;i++){
					System.out.println(nodes[i].is+"/"+nodes[i].nexts);
				}
			}*/
			return is;
		}
		
		public boolean is(NodeChu[] nodes){
			nodes[0].is=true;
			for(int i=0;i<nodes.length;i++){
				if(nodes[i].nexts.size()>0){
					for(Integer e:nodes[i].nexts){
						if(nodes[e].is&&!nodes[i].is){
							nodes[i].is=true;
						}else if(!nodes[e].is&&nodes[i].is){
							nodes[e].is=true;
						}
					}
				}
			}
			boolean is=true;
			for(int i=0;i<nodes.length;i++){
				if(!nodes[i].is){
					is=false;
					break;
				}
			}
			return is;
		}
		//XXX 回溯判断是否有效失败，2017年2月28日19:21:12
		public int shu(Node[] chu,int ss){
			
//			if()
			
			for(int i=0;i<chu.length-1;i++){
				for(int j=i+1;j<chu.length;j++){
					if((Math.abs(chu[i].r-chu[j].r)<2&&chu[i].c==chu[j].c)
							||(Math.abs(chu[i].c-chu[j].c)<2&&chu[i].r==chu[j].r)){
						ss++;
					}
				}
			}
			
			return ss;
		}
		/**
		 * 失败：2017年2月24日18:28:52
		 * XXX 抛弃
		 * jian
		 * 方法描述: 
		 * 逻辑描述: 
		 * @param you
		 * @param x 横坐标
		 * @param y 纵坐标
		 * @param n 第几块邮票
		 * @param chu
		 * @since Ver 1.00
		 */
		public void jian(int[][] you,int x,int y,int n,Node[] chu){
			Node node=new Node();
			node.r=x;
			node.c=y;
			
			chu[n]=node;
			you[x][y]=-1;
			
			if(n==chu.length-1){
				s++;
//				System.out.println(Arrays.toString(chu));
				write(Arrays.toString(chu)+"\n");
				//还原
				chu[n]=null;
				you[x][y]=0;
				return;
			}
			
			for(int i=0;i<=n;i++){
				x=chu[i].r;
				y=chu[i].c;
				if (x-1>-1&&you[x-1][y]!=-1) {
					jian(you, x-1, y, n+1, chu);
				}
				if (x+1<3&&you[x+1][y]!=-1) {
					jian(you, x+1, y, n+1, chu);
				}
				if (y-1>-1&&you[x][y-1]!=-1) {
					jian(you, x, y-1, n+1, chu);
				}
				if (y+1<4&&you[x][y+1]!=-1) {
					jian(you, x, y+1, n+1, chu);
				}
			}
			//还原
			chu[n]=null;
			you[x][y]=0;
		}
		
	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		/*try {
			FileUtils.writeStringToFile(new File(path), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int[][] you=new int[3][4];
		Node[] chu=new Node[5];
		new Algorithm2().huisu3(you, 0, 0, 0, chu);
		System.out.println(s);

		/**XXX 抛弃原因，重复次数计算失败
		for(int i=0;i<3;i++)
		for(int j=0;j<4;j++)
			new Algorithm2().jian(you, i, j, 0, chu);
		 * 有4次，3次，2次和1次，不统一
		 * 
		System.out.println(s/8);*/
	}
	
	public static String path="/log.log";
	
	private void write(String data){
		try {
			FileUtils.writeStringToFile(new File(path), data, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
