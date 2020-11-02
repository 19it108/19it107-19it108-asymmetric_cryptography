import java.util.Scanner;
import java.util.Random;

public class temp
{
	static Scanner sc = new Scanner(System.in);
	public static int p;
	public static Random r = new Random();
	//static int p;
	
	public static void main(String[] args) 
	{
		getdata();
	}
	
	public static int getdata()
	{
		p = r.nextInt(50);
		
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
}
