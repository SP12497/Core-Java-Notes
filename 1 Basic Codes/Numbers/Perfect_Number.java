package Numbers;
//	6 = 1+ 2 + 3
//  28 = 1+ 2 + 4 + 7 + 14  ..all numbers are completly divides 28 and addition is also 28

public class Perfect_Number 
{
	public static void main (String [] args)
	{
		int num = 28;
		int sum = 0;
		
		for (int i = 1; i<=num/2; i++)
		{
			if ( num %i == 0)
			{
				sum=sum+i;
			}
		}
		
		if (sum == num)
			System.out.println("Perfect Number");
		else 
			System.out.println("Not perfect number");

	}
}