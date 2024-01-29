/*
In Java, it is possible to inherit attributes and methods from one class to another. We group the "inheritance concept" into two categories:

subclass (child) - the class that inherits from another class
superclass (parent) - the class being inherited from
To inherit from a class, use the extends keyword.
*/

public class InheritanceIsA
{

	public static void main(String[] args) 
	{
//		Add obj = new Add();
//		System.out.println(obj.sum());
		
		AddSub obj1 = new AddSub();
		System.out.println(obj1.sum());
		System.out.println(obj1.sub());
		
	}
}



class Add
{
	int num1;
	int num2;
	int result;
	
	public Add()
	{
		num1=20;
		num2=10;
	}
	
	public int sum()
	{
		return (num1 + num2);
	}
}


class AddSub extends Add
{
	public int sub()
	{
		return (num1-num2);
	}
}