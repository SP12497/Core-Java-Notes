package Strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReader_Scanf 
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("Enter a number :");
		
		InputStreamReader is = new InputStreamReader(System.in);
		
		BufferedReader br = new BufferedReader(is);
		
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(n);
	
//--------------
		System.out.println("Enter a number :");
		
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
		
		int x = Integer.parseInt(br.readLine());
		
		System.out.println(x);
		
		
		
				
		
		
	}

}
