package BasicCode;
// 0 1 1 2 3 5 8 13
public class Fibonacci_Series
{
	public static void main(String [] args)
	{
		 int a = 1 , b= 1;
		 
		 int k = 1;
		 
		 System.out.print("0 " +k + " ");
		 
		 while(k <= 100)	// prints series till 100
		 {
			
			 System.out.print(k + " ");
			 k=a+b;
			 a = b;
			 b = k;
		 }
	}
}
