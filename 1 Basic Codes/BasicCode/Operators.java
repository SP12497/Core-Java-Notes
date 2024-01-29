package BasicCode;

public class Operators
{
	public static void main(String [] args)
	{
		int a = 10;	// 	1 0 1 0 
		int b = 12; // 	1 1 0 0
		int c ;
		c = a & b;	// 	1 0 0 0 = 8	AND
	//  c = a | b; // 	1 1 1 0 = 14	OR
		
	//	c = a >> 2; // 1 0 = 2 Right Shift
	//  c = a << 2; // 1 0 1 0 0 0 = 40 Left Shift
	
		System.out.println(c);
		
	//------------------------------------------------
		boolean x = true ; 
		// x = !x;		// false
		System.out.println(x);
	//------------------------------------------------
	//Ternary Operator ?:
		
		int i =3 ;
		int j = 5;
		int k= 4;
		
		j =  i==1? 6:7;
		System.out.println(j);	//7
		
		int gretestNo = i>j? (i>k?i:k) : (j>k?j:k);		//5
	}
}
