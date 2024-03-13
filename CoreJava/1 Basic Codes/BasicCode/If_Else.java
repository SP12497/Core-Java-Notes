public class If_Else
{
	public static void main (String [] args)
	{
	//1] Even Odd No Code :
		
		int no = 12 ;
		
		if(no %2 ==0)
		{
			System.out.println("No is Even");
		}
		else
		{
			System.out.println("No is Odd");
		}
		
		
	//2] Greater No
		
		
		int a = 30;
		int b = 40;
		int c = 50;
		
		if (a>b && a>c)
		{
			System.out.println("Greater Num is = a");
		}
		else if (b > c) 	// a is not greater
		{
			System.out.println("Greater Num is = b");
		}
		else
		{
			System.out.println("Greater NO is = c");
		}
		
		// logic 2:
		int gretestNo = a>b? (a>c?a:c) : (b>c?b:c);
		
	}
	
}
