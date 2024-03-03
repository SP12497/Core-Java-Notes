/*
Polymorphism means "many forms", and it occurs when we have 
 many classes that are related to each other by inheritance.
 Like we specified in the previous chapter; 
 Inheritance lets us inherit attributes and methods from another class. 
 Polymorphism uses those methods to perform different tasks.


Types :
	1 Method Overloading.  	: Early Binding,  Static Binding,	Compile Time Polymorphism. 
		- Method name is same but signature(parameters) should be different in same class.
		- Same: method name , Class
			Different : Parameter
	2 Method OverRiding. 	: Late Binding ,  Dynamic Binding, 	Run Time Polymorphism.
		- Method name and signature(parameters) are same but during Inheritance (different class).
		- Same: method name , Parameter 
			Different : Class
*/

public class PolyMorphism 
{
	public static void main(String[] args) 
	{
System.out.println("Method Overriding Polymorphism : ");

	CA obj = new CA();
	obj.show();
	obj.show(1);
	obj.show(1.1);
	obj.show(1,2);

System.out.println();

System.out.println("Method OverRiding : ");
	CB obj1 = new CB();
	obj1.show();
	}
}


class CA		// Method OverLoading
{
	public void show()
	{
		System.out.println(" Show CA");
	}
	public void show(int a)
	{
		System.out.println(" Show CA with 1 Int Parameter");
	}
	
	public void show(double a)
	{
		System.out.println(" Show CA with 1 Double Parameter");
	}
	
	public void show(int a , int b)
	{
		System.out.println(" Show CA with 2 Int Parameter");
	}
}

class CB extends CA
{
	public void show()
	{
		System.out.println(" Show CB ");
		super.show(10);		//super is used to call parent class Method or variable.
	}
	
	
}

