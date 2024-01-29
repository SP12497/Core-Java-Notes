package BasicCode;

public class PrePostIncrement {
	
	public static void main(String [] args)
	{
		int a = 10 ;
		 int b=0;
		//b = a++;		//b=10 //a=11
		//b = ++a;		//b=12 //a=12
		
		a = a++;		//a = 10
		/* This print 10 because System created :
		 *  int temp = i;  //temp = 10
		 *  i++;  //11
		 *  i = temp;   // i = 10
		 * */
		
		System.out.println("a : " + a);
		System.out.println("b : " + b);
	}

}
