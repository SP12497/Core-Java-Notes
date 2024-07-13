/*
	A functional interface is an interface that contains only one abstract method. 
	They can have only one functionality to exhibit. 
	From Java 8 onwards, 
	 lambda expressions can be used to represent the instance of a functional interface. 
	A functional interface can have any number of default methods.
	
	Lambda Expression works only with Functional Interface.
*/


@FunctionalInterface	//not compulsory
interface CA
{
	void show();
}


interface CB
{
	void print(int x);
}

interface CC
{
	void print(int x , int y);
}

abstract class AbClass {
	public abstract void show();
}

public class _8_21_2_LambdaExpression 
{
	public static void main(String[] args) 
	{
		CA obj = () -> System.out.println("in show");
		obj.show();
		
		CB obj1 = x -> System.out.println("in print :  x = " + x);
		obj1.print(10);
		
		//Multiline Lambda Expression ()->{};
		CC obj3 = (a , b) -> {
			System.out.println("in print :  x = " + a +  "  b = "+  b);
		};
		obj3.print(10 , 20);

		// ------- Lambda expression does not support for class for object creation.
		// AbClass a = () -> {};	// Syntax error.
	}
}
