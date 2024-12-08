public class Patterns {

	
	public static void main(String [] args)
	{		
		System.out.println("1-------------------------");
		/*1	
		 		i=	1 2 3 4	
		 		j=1	* * * * 
				j=2	* * * * 
				j=3	* * * * 
				j=4	* * * * 	*/
		
		for (int i=0; i<4 ; i++)	//Rows
		{
			for (int j=0; j<4;	j++)	//Columns
			{
				System.out.print(" * ");
			}
			System.out.println();
		}
		
		
System.out.println("2-------------------------");
		/*1			*  
					* * 
					* * * 
					* * * * 	*/

		for (int i=1; i<=4 ; i++) {
			for (int j=1; j<=i;	j++) {
				System.out.print(" * ");
			}
			System.out.println();
		}

System.out.println("3-------------------------");
		/*3		i=	1 2 3 4
		 		j=1	* * * *
				j=2	* 	  * 
				j=3	*     * 
				j=4	* * * * 	*/
		
		int matrix = 4;
		for (int i=1; i<=matrix ; i++)
		{
			for (int j=1; j<=matrix;	j++)
			{
				if(i==1 | i==matrix | j==1 | j==matrix)
				{
					System.out.print(" * ");
				}
				else
				{
					System.out.print("   ");
				}
			}
			System.out.println();
		}
		
System.out.println("4-------------------------");
		
		/*4		1 2 3 4
		 		1 2 3 4 
		 		1 2 3 4 
		 		1 2 3 4 	*/


		for (int i=1; i<=4 ; i++)
		{
			for (int j=1; j<=4;	j++)
			{
				System.out.print(j + " ");
				
				//  1111
				//	2222
				//  3333
				//  4444
				// System.out.print(i +" ");
			}
			System.out.println();
		}
		

System.out.println("5-------------------------");
		
		/*6		1 2 3 4
		 		2 3 4 1 
		 		3 4 1 2
		 		4 1 2 3  	*/


		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				int k = j + i - 1;

				if (k > 4) { // when k= 5 6 7 8
					System.out.print(k - 4 + " "); // j+i - 5
				} else {
					System.out.print(k + " ");
				}
			}
			System.out.println();
		}

System.out.println("6-------------------------");
		
		/*7		1
		 		0 1   
		 		1 0 1
		 		0 1 0 1
		 		1 0 1 0 1
		*/


		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {

				if ((i + j) % 2 == 1) { // when i+j is even
					System.out.print(" 0 ");
				} else {
					System.out.print(" 1 ");
				}
			}
			System.out.println();
		}

System.out.println("7-------------------------");
		
		/*8		1
		 		2 2
		 		3 3 3 
		 		4 4 4 4
		 		5 5 5 5 5   	*/


		for (int i=1; i<=5 ; i++)	//Rows
		{
			for (int j=1; j<=i;	j++)	//Columns
			{
				System.out.print( i + " ");
			}
			System.out.println();
		}
		
System.out.println("8-------------------------");
		
		/*
			1
			2*2
			3*3*3
			2*2
			1
		*/
		
		
		
		int rows = 3;
			
		
		for (int i = 1; i <= rows; i++) 	// i= 1 2 3
		{
			for (int j = 1; j <= i; j++)	 //j= 1 2 3
			{	
				System.out.print(i);
				if (i!=j) {
					System.out.print("*");
				}
			}
			System.out.println("");
		}
				
		for (int i = rows-1; i >=1; i--)	//i= 2 1
		{
			for (int j = 1; j <= i; j++) 	// j= 1 2
			{	
				System.out.print(i);
				if (i!=j) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
}
