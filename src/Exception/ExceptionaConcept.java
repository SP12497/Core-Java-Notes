package Exception;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;



public class ExceptionaConcept 
{
	public static void main(String[] args)
	{
		int i , j , k=0;
		int a[] = new int[4];	//	array of 4
		i=8;
		j=2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			System.out.println("Enter denominator :");
			j= Integer.parseInt(br.readLine());
			
			k=i/j;		//Error denominator is j = 0.
			System.out.println("K = " + k);	
			
			for(int c =0 ; c<=4; c++)		// array size is 4  and we assign 5 values.. ie Error.
			{
				a[c] = c+1;
			}
			
			for(int value :a)
			{
				System.out.println(value);
			}
		}
		catch (IOException e )
		{
			System.out.println("some IO Error.");
		}
		catch(ArithmeticException e)		//It catch only arithmatic errors
		{
			System.out.println("Cannot Divide by Zero");
		}
		catch(ArrayIndexOutOfBoundsException e) 	//It catch Array related errors.
		{
			System.out.println("Maximum no of values is 4");
		}
		catch(Exception e)		//It catch all the errors
		{
			System.out.println("Unknown exception");
		}
		
		finally			//no matters what output , but this code always execute.
		{
			System.out.println("----Finally Bye----");
		}

		
		
		
	}

}
