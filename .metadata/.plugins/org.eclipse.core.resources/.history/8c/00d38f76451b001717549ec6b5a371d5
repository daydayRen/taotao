package it.base;

import java.util.Scanner;
/**
 *  输入格式
　　第一行有两个整数T（1 <= T <= 1000）和M（1 <= M <= 100）。用一个空格隔开，T代表总共可以用来採药的时间，M代表山洞里的草药的数目。
	接下来的M行每行包含两个在1到100之间（包含1和100）的整数。分别表示採摘某株草药的时间和这株草药的价值。
	输出格式
　　包括一行。这一行仅仅包括一个整数。表示在规定的时间内。能够採到的草药的最大总价值。
 * @author oneday
 * 如果采摘第i个草药，则d[T-t]+p>d[t]    其中T为总时间  t为采药时间，p为药用价值 
 *
 */

public class RuXueKaoShi{

	static int W ;
	static int[] w_arr ;
	static int[] p_arr ;
	static int[][] v;

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String[] sarr=scanner.nextLine().split(" ");
		W=Integer.parseInt(sarr[0]);
		w_arr=new int[Integer.parseInt(sarr[1])];
		p_arr=new int[w_arr.length];
		
		for(int i=0;i<w_arr.length;i++)
		{
			sarr=scanner.nextLine().split(" ");
			w_arr[i]=Integer.parseInt(sarr[0]);
			p_arr[i]=Integer.parseInt(sarr[1]);
		}

		k();
	}
	

	static void k()
	{
		int[] b=new int[W+1];
		for(int i=0;i<w_arr.length;i++)
		{
			for(int j=W;j>=w_arr[i];j--)
			{
				//b[j]= Math.max(b[j], b[j-w_arr[i]]+p_arr[i]);
				if(b[j-w_arr[i]]+p_arr[i]>b[j])
				{
					b[j] =b[j-w_arr[i]]+p_arr[i];
				}
			}
		}
		System.out.println(b[W]);//max
	}
}