import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.math.BigInteger;


public class ecc9
{
	private static double n,na,nb,pro=1,yp=1,yp1,Y;
	private static BigInteger p1;
	private static Random r = new Random(); 
	private static int p,g,g1,g2,X,bitLength = 6;
	private static long G;
	private static int temp=0;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		ecc9 ecc = new ecc9();
		System.out.println("Enter text:");
		String s = sc.nextLine();
		String res = "";
		sc.close();
		char[] str = s.toCharArray();
		
		
		p1 = BigInteger.probablePrime(bitLength, r);
		p=p1.intValue();
		System.out.println("p:" + p);
		gen4(p, n);
		
		
		double array[][] = new double[100000][2];
		
		double[] suf=gen6();
		nb=suf[1];
		System.out.println("a: " + suf[0]);
		System.out.println("b: " +suf[1]);
		System.out.println("z:" + suf[2]);
		n =(int)gen3();
		System.out.println("n:" + n);
	
		double LHS[] = new double[(int)p];
		//System.out.println((int)p);
		double arr1[] = new double[(int)p];
		double y1[] = new double[(int)p];
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
		
		double  RHS[]= new double[(int)p];
		double arr2[] = new double[(int)p];
		double x1[] = new double[(int)p];
		
		for(int x=0;x<p;x++)
		{
				 RHS[x]=(Math.pow(x,3))+(suf[0]*x)+suf[1];
				 arr2[x] = RHS[x]%p;
				 x1[x] = x;
				// System.out.println("arr2 " + arr2[x] + "rhs " + RHS[x]);
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
					//System.out.println(temp);
						
					array[temp][0]=x1[j];
					array[temp][1]=y1[i];
					
					temp++;
				}
			}
		}
		//System.out.println(temp);
		
		g = (int)gen();
		System.out.println("g:" + g);
		System.out.println("(" + array[g][0] + ","+ array[g][1] + ")");	
		
		double xr[] = new double[temp+1];
		double yr[] = new double[temp+1];
		double slope[] = new double[temp+1];
		double array2[][] = new double[temp+1][2];
		xr[1]=array[g][0];
		yr[1]=array[g][1];
		
		 
		slope[2]=((3*(Math.pow(array[g][0],2)) + suf[0])%p)*modInverse((2*yr[1]),p);
		System.out.println( "slope: " + slope[2]);
		xr[2]=(Math.pow(slope[2],2 ) -(2*xr[1]))%p;
		yr[2]=((slope[2]*(xr[1]-xr[2]))-(yr[1]))%p;
		array2[2][0]=xr[2];
		array2[2][1]=yr[2];
		
		System.out.println("2"+ "g" + "(" + array2[2][0] + ","+ array2[2][1] + ")");
		for(int i=3;i<temp;i++)
		{
			
			slope[i]=(((yr[i-1]-yr[1]))%p)*modInverse(xr[i-1]-xr[1],p);
			xr[i]=((Math.pow(slope[i],2 )) -(xr[1]+xr[i-1]))%p;
			yr[i]=((slope[i]*(xr[1]-xr[i]))-(yr[1]))%p;
			
			
			array2[i][0]=xr[i];
			array2[i][1]=yr[i];
			System.out.println(i+ "g" + "(" + array2[i][0] + ","+ array2[i][1] + ")");
			
			
			
			
		}
		g1=(int)gen1();
		g2=(int)gen5();
		System.out.println("private key a : "+ g1);
		System.out.println("private key b:"+ g2);
		double publickeya[][]= new double[1][2];
		publickeya[0][0]=array2[g1][0];
		publickeya[0][1]=array2[g1][1];
		System.out.println("publickey a :" + "("+ publickeya[0][0]+ ","+ publickeya[0][1] + ")");
		double publickeyb[][]= new double[1][2];
		publickeyb[0][0]=array2[g2][0];
		publickeyb[0][1]=array2[g2][1];
		System.out.println("publickey b :" + "("+ publickeyb[0][0]+ ","+ publickeyb[0][1] + ")");
		
		double g3=r.nextInt(temp-1);
		
		
		for(int i=0; i< str.length ; i++) 
		{
			res += Integer.toBinaryString(str[i]); 
	    }
		System.out.println(res); //decimal to binary string
		
		String N = "00000"; //padding 0 bits
		res = res + N;
		System.out.println("Result after padding :" + res); //added n 0s
			X = Integer.parseInt(res,2);
		//++X;
		System.out.println("X:" + X);
		
		
		Y = ((Math.pow(X,3)) + (suf[0]*X) + suf[1])%p;
		//System.out.println("Y:" + Y);
		
		
		yp1=gen7(Y);
		System.out.println("Y:" + yp1);
		
		int k = r.nextInt(temp);
		//System.out.println("k:" + k);
		
		double cipher[][] = new double[2][2];
		double slope2[] = new double[k+1];
		double publickeybk[][]= new double[k+1][2];
		cipher[0][0]=array2[k][0];
		cipher[0][1]=array2[k][1];
		slope2[2]=((3*(Math.pow(publickeyb[0][0],2)) + suf[0])%p)*modInverse((2*publickeyb[0][1]),p);
		System.out.println( "slope: " + slope2[2]);
		publickeybk[2][0]=(Math.pow(slope2[2],2 ) -(2*publickeyb[0][0]))%p;
		publickeybk[2][1]=((slope2[2]*(publickeyb[0][0]-publickeybk[2][0])-(publickeyb[0][1]))%p);
		
		
		System.out.println("2"+ "k" + "(" + publickeybk[2][0] + ","+ publickeybk[2][1] + ")");
		for(int i=3;i<=k;i++)
		{
			
			slope2[i]=(((publickeybk[i-1][1]-publickeyb[0][1]))%p)*modInverse(publickeybk[i-1][0]-publickeyb[0][0],p);
			publickeybk[i][0]=((Math.pow(slope2[i],2 )) -(xr[1]+xr[i-1]))%p;
			publickeybk[i][1]=((slope2[i]*(publickeyb[0][0]-publickeybk[i][0]))-(publickeyb[0][1]))%p;
			
			
			
			//System.out.println(i+ "k" + "(" + publickeybk[i][0] + ","+ publickeybk[i][1] + ")");
			
			
			
			
		}
		double slope3;
		slope3=(((yp1-publickeybk[k][1]))%p)*modInverse(X-publickeybk[k][0],p);
		cipher[1][0]=((Math.pow(slope3,2 )) -(X+publickeybk[k][0]))%p;
		cipher[1][1]=((slope3*(publickeybk[k][0]-cipher[1][0]))-(publickeybk[k][1]))%p;
		//System.out.println( "(" + cipher[1][0] + ","+ cipher[1][1] + ")");
		System.out.println("encrypted text : {" + "(" +cipher[0][0]+"," +  cipher[0][1]+")" + ","+ "("+cipher[1][0] +","+cipher[1][1]+")"+ "}");
		
		
		 double slope4[]= new double[g2+1];
			slope4[2]=((3*(Math.pow(cipher[0][0],2)) + suf[0])%p)*modInverse(2*cipher[0][1],p);
			//System.out.println( "slope: " + slope4[2]);
			double d1[][]= new double[g2+1][2];
			d1[2][0]=(Math.pow(slope4[2],2 ) -(2*cipher[0][0]))%p;
			d1[2][1]=((slope4[2]*(cipher[0][0]-d1[2][0])-(cipher[0][1]))%p);
			
			
			System.out.println("2" + "(" + d1[2][0] + ","+ d1[2][1] + ")");
			for(int i=3;i<=g2;i++)
			{
				
				slope4[i]=(((d1[i-1][1]-cipher[0][1]))%p)*modInverse(d1[i-1][0]-cipher[0][0],p);
				d1[i][0]=((Math.pow(slope4[i],2 )) -(cipher[0][0]+d1[i-1][0]))%p;
				d1[i][1]=((slope4[i]*(cipher[0][0]-d1[i][0]))-(cipher[0][1]))%p;
				
				
				
				//System.out.println(i + "(" + d1[i][0] + ","+ d1[i][1] + ")");
				
				
				
				
			}
		
		//encryption
		String binary = "";
		binary = Integer.toBinaryString(X);
		int lenB = binary.length();
		System.out.println("binary:" + binary);
		String orgX=binary.substring(0,lenB-N.length());
		System.out.println("Original X in Binary after removing padding:" + orgX);
		//int XX=Integer.parseInt(orgX,2);	
		//System.out.println(XX);
		String decrypttext = "";
		for(int i=0 ; i<orgX.length()/7;i++)
		{
			int a =Integer.parseInt(orgX.substring(7*i,(i+1)*7),2);
			decrypttext += (char)(a);
		}
		System.out.println("decrypt text:  " + decrypttext);
		
		
		
	}
	public static long gen()
	{
		G = r.nextInt(50);
		if(G>=temp)
		{
			gen();
		}
		return G;
	}
	
	public static long gen1()
	{
		G = r.nextInt(50);
		if(G>g || G==1 || G==0 )
		{
			try
			{gen1();
			}catch(StackOverflowError e){
				gen1();
			}
		}
		return G;
	}
	public static long gen5()
	{
		G = r.nextInt(50);
		if(G>g || G==1 || G==0 )
		{
			try
			{gen5();
			}catch(StackOverflowError e){
				gen5();
			}
		}
		return G;
	}
	 static double[] gen6()
	{
		double[] ans = new double[3];
		ans[0] = r.nextInt(20);
		ans[1] = r.nextInt(20);
		ans[2] = (( 4 * (int)(Math.pow(ans[0],3)) + (27 * (int)(Math.pow(ans[1],2))))%p);
		if(ans[2]==0 )
		{
			gen6();
			
		}
		return ans;
	}
	static int modInverse(double a, double m) 
    { 
        a = a % m; 
        for (int x = 1; x < m; x++) 
           if ((a * x) % m == 1) 
              return x; 
        return 1; 
    } 
	public static long gen3()
	{
		G = r.nextInt(10);
		if(G==0)
		{
			gen3();
		}
		return G;
	}
	public static void gen4(double a, double b)
	{
		if((Math.pow(a, b))>=((Math.pow(2,32))-1))
				{
					gen3();
					getdata();
				}
	}
	public static int getdata()
	{
		
		int p = r.nextInt(50);
		if(p<=3)
		{
			getdata();
		}
		for(int i = 2; i<p; i++)
		{
			if(p%i==0) 
			{
				getdata();
				break;
			}
			if(i == p-1) 
			{
				break;
			}
		}
		return p;
	}
	/*private static void check(int B) 
	{

		if(B == )
		{
			System.out.println("cordinates X and Y belongs to the elliptic Curve");
		}
		else
		{
			System.out.println("cordinates X and Y does not belong to the elliptic Curve");
	//		X++;
		}
	}*/
	private static double gen7(double a)
	{
		
		if((Math.pow(yp,2)%p)!=a)
		{
			try {
			yp++;
			gen7(Y);
			}catch(StackOverflowError e){
			  gen7(Y)	;
			}
		}
		return yp;
	}
	private static String byte_string(byte[] value)
    {
        String test = "";
        for (byte b : value)
        {
            test += Byte.toString(b);
        }
        return test;
    }

}

