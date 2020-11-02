import java.util.Scanner;
import java.util.Random;

public class ecc7 extends temp
{
	private static int a,b,p,n,na,nb;
	private static Random r = new Random(); 
	private static double g,G;
	private static int temp=0;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		ecc7 ecc = new ecc7();
		System.out.println("Enter text:");
		String s = sc.nextLine();
		sc.close();
		byte[] by = s.getBytes();
		
		//calculation
		
		a = r.nextInt(10);
		System.out.println("a:" + a);
		
		b = r.nextInt(10);
		System.out.println("b:" + b);
		
		try 
		{
		double z = 4 * (Math.pow(a,3)) + (27 * (Math.pow(b,2)));
		System.out.println("z:" + z);	
		}
		catch(ArithmeticException e)
		{
			e.printStackTrace();
		}
		
		n = r.nextInt(10);
		System.out.println("n:" + n);
		
		temp t = new temp();
		p = t.getdata();
		System.out.println("p:" + p);

		//int temp1=0;
		double  LHS[] = new double[p];
		double arr1[] = new double[p];
		double y1[] = new double[p];
		for(int y=0;y<p;y++)
		{
					 LHS[y]=Math.pow(y,2);
					 arr1[y] = LHS[y]%p;
					 y1[y] = y;
					 //System.out.println("arr1 " + arr1[y] + "lhs " + LHS[y]);
					 //temp1++;
		}
		//System.out.println(temp1);
		//System.out.println(LHS);
		//System.out.println(arr1);
		
		//int temp2=0;
		double  RHS[]= new double[p];
		double arr2[] = new double[p];
		double x1[] = new double[p];
		for(int x=0;x<p;x++)
		{
				 RHS[x]=(Math.pow(x,3))+(a*x)+b;
				 arr2[x] = RHS[x]%p;
				 x1[x] = x;
				 //System.out.println("arr2 " + arr2[x] + "rhs " + RHS[x]);
				 //temp2++;
		}
		//System.out.println(temp2);
		//System.out.println(RHS);
		//System.out.println(arr2);
		
		

		for(int i=0;i<p;i++)
		{
			for(int j=0;j<p;j++)
			{
				if(arr1[i]==arr2[j])
				{
					System.out.println("(" + x1[j] + ","+ y1[i] + ")");
					temp++;
				}
			}
		}
		System.out.println(temp);
		
		g = ecc.gen();
		System.out.println("g:" + g);
		
		double array[][] = new double[temp][2];
		/*for(int i=0;i<temp;i++)
		{
			for(int j=0;j<2;j++)
			{
					array[i][j] = {}
					System.out.println("(" + x1[j] + ","+ y1[i] + ")");
					temp++;
			}
		}*/
		
	}
	
	public static double gen()
	{
		G = r.nextInt(50);
		if(G>=temp)
		{
			gen();
		}
		return G;
	}
	
}

