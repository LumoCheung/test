package cn.paypalm.file;

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
public class Test {
	public static  void main(String[] args)
	{
		int a=6,b=6;
		int[][] jz=new int[a][b];
		int x=a/2;
		int y=b/2;
		jz[x][y]=1;
		print(jz, x, y, x-1, y, 2);
		for(int i=0;i<a;i++)
		{
			for(int j=0;j<b;j++)
			{
				System.out.print(jz[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void print(int[][] jz,int i,int j,int m,int n,int k )
	{
		jz[m][n]=k;
		if(i==m&&j<n)
		{
			//判断右边是否为1
			if(jz[m+1][n]==0)
			{
				//右转
				print(jz, m, n, m+1, n,k+1);
			}
			else {
				//直走
				if(n+1>=jz[0].length||jz[m][n+1]!=0)
					return;
				print(jz, m, n, m, n+1,k+1);
			}
		}
		else if(i==m&&j>n) {
			if(jz[m-1][n]==0)
			{
				//右转
				print(jz, m, n, m-1, n,k+1);
			}
			else {
				//直走
				if(n-1<0||jz[m][n-1]!=0)
					return;
				print(jz, m, n, m, n-1,k+1);
			}
		}
		else if(i<m&&n==j){
			if(jz[m][n-1]==0)
			{
				//右转
				print(jz, m, n, m, n-1,k+1);
			}
			else {
				//直走
				if(m+1>jz.length||jz[m+1][n]!=0)
					return;
				print(jz, m, n, m+1, n,k+1);
			}
		}
		else {
			if(jz[m][n+1]==0)
			{
				//右转
				print(jz, m, n, m, n+1,k+1);
			}
			else {
				//直走
				if(m-1<0||jz[m-1][n]!=0)
					return;
				print(jz, m, n, m-1, n,k+1);
			}
		}
	}

}
