/*
	Java Wrapper Classes :	java.lang package

		Wrapper classes provide a way to use primitive data types (int, boolean, etc..) as objects.
		
		java is only 99.99% OOP lang because use of primitive data types

		The table below shows the primitive type and the equivalent wrapper class:
		
		Primitive Data Type :	Wrapper Class
		byte					Byte
		short					Short
		int						Integer
		long					Long
		float					Float
		double					Double
		boolean					Boolean
		char					Character
*/

import java.util.ArrayList;

public class CMain 
{
	public static void main(String[] args) 
	{
		int i = 5;

		//Integer ii = new Integer(10);
		Integer ii = new Integer(i);	//Boxing			: primitive to wrapper
		Integer jj = i;					//AutoBoxing

		int j = jj.intValue();			// UnBoxing
		int k = jj;						// AutoUnBoxing
		
		System.out.println(jj.intValue());
		System.out.println(j);


	}
}