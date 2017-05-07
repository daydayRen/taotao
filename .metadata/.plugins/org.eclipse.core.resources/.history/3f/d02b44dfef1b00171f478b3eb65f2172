package it.moni;
/**
 * 求出第k个数是什么  用数组存储
 * @author oneday
 *
 */
public class 全排列逆转 {
	static //阶乘
	int[] a=new int[10];
	public static long fact(long n){
			long i;
			long s=1;
			for(i=2;i<=n;i++){
				s*=i;
			}
			return s;
		}
	public static void inv(int a[],int n,int k){
		boolean v[]=new boolean[10];
		for(int i=0;i<10;i++)
			v[i]=false;
		int b,c,t;
		k--;
		for(b=0;b<n;b++){
			t=(int) (k/fact(n-1-b));
			for(c=1;c<=n;c++){
				if(!v[c]){
					if(t==0)
					break;
					t--;
				}
					
			}
			
			a[b]=c;
			//标识符  判断是否已经使用过
			v[c]=true;
			k%=fact(n - 1 - b);
			
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		inv(a,5,16);
		for(int i=0;i<5;i++)
		System.out.print(a[i]+"  ");
	}

}
