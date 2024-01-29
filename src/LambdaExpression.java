/*
A functional interface is an interface that contains only one abstract method. 
They can have only one functionality to exhibit. From Java 8 onwards, 
lambda expressions can be used to represent the instance of a functional interface. 
A functional interface can have any number of default methods.
*/


@FunctionalInterface	//not compulsory
interface Ab
{
	void show();
}

public class LambdaExpression 
{
	public static void main(String[] args) 
	{
		Ab obj = () -> System.out.println("in show");
		obj.show();
	}

}