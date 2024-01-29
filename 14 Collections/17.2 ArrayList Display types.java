/*
java 7 :	
	support only OOP
java 8 :
	introduces functional programming
		dont focus on how to do things, just focus on what to do	
		remaining part will be handled by stream API
*/

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;



public class CMain 
{
	public static void main(String[] args) throws Exception
	{
		
		List <Integer> values = Arrays.asList(11,22,33,44,55);
		
/*	There are 2 Types of Iteration of Arraylist :
		1. Internal Iteration
		2. External Iteration
*/	

	//1. Internal Iteration: Introduced in Java 8 Stream API
	//	type 1.a :
		//values.forEach(i-> System.out.println(i));	//Its a functional interface //It accept consumer object as a parameter.
	//	type 1.b :
//		Consumer<Integer> c = new Consumer<Integer>() {
//			@Override
//			public void accept(Integer i) {
//				System.out.println(i);
//			}
//		};
//		values.forEach(c);

	//	type 2 :
	System.out.println("Stream API :");
		values.forEach(System.out::println);		//Method reference(::) =>its know we have to do println(i); bcoz of Consumer is a functional interface




	//2. External Iteration : It has 3 types
	System.out.println("for loop :");
		for(int i=0 ; i<values.size() ; i++)	//arr.length is for normal arrays
		{
			System.out.println(values.get(i));
		}
		
	System.out.println("Iterator :");
		Iterator <Integer> it = values.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
	System.out.println("Advance for loop :");
		for(int i : values) 
		{
			System.out.println(i);
		}

	}
}