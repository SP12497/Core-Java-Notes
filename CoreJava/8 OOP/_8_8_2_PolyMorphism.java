/*
PolyMorphism means redefining the information.
Polymorphism means "many forms", and it occurs when we have
 many classes that are related to each other by inheritance.
 Like we specified in the previous chapter;
 Inheritance lets us inherit attributes and methods from another class.
 Polymorphism uses those methods to perform different tasks.

Types :
	1. Method Overloading.  	: Early Binding,  Static Binding,	Compile Time Polymorphism. 
		- Instance Method name is same but signature(parameters) should be different in same class.
		- Same: method name, Class
		  Different : Parameter and
		- return type can be same or different.
	2. Method OverRiding. 	: Late Binding ,  Dynamic Binding, 	Run Time Polymorphism.
		- Instance Method name and signature(parameters) are same but during Inheritance (different class).
		- Same: method name , Parameter and return type.
		  Different : Class
		  
	Method Hiding:
		- OverRiding of static method is not possible is java. But we can overload static method.
		- If we ReDefining the static method of super class into sub class then, this is called as Method Hidding.
	Data Hiding:
		In Abstraction, the variables of a class will be hidden from other classes, and can be accessed only through
		the methods of their current class. Therefore, it is also known as DATA HIDING.

Rules of Method Overriding in Java
 -> Following are rules of method overriding in java which must be followed  while overriding any method :
	- As stated earlier private, static and final method can not be overridden in Java.
	- Method signature must be same including return type, number of method parameters, type of parameters and order of parameters
	- Overriding method can not throw higher Exception than original or overridden method. 
		means if original method throws IOException than overriding method can not throw super class of IOException
		ie. Exception but it can throw any sub class of IOException or simply does not throw any Exception.
		This rule only applies to "checked Exception" in Java, overridden method is free to throw any unchecked Exception.
	- Overriding method can not reduce accessibility of overridden method, 
		means if original or overridden method is public than overriding method can not make it protected.

super keyword:
	- super is used to call parent class Method or variable.
	- super() is used to call parent class constructor.
	- super is used to refer immediate parent class instance variable.
	- super keyword instance method. Not in static method (static method is class level method, which is directly called by class name).
*/

public class _8_8_2_PolyMorphism {
	public static void main(String[] args) {
		System.out.println("Method Overloading Polymorphism : ");

		CA obj = new CA();
		obj.show();
		obj.show(1);
		obj.show(1.1);
		obj.show(1, 2);

		System.out.println();

		System.out.println("Method OverRiding : ");
		CB obj1 = new CB();
		obj1.show();
		obj1.sayCB();

		System.out.println("Child Reference : Method OverRiding : ");
		CA childRef = new CB();
		childRef.show();	// Show CB
		// childRef.sayCB(); // error
	}

}

class CA // Method OverLoading
{
	public void show() {
		System.out.println(" Show CA");
	}

	// public int show() {				// Duplicate method show() in type CA
	// 	System.out.println(" Show CA");
	// 	return 0;
	// }

	public void show(int a) {
		System.out.println(" Show CA with 1 Int Parameter");
	}

	public void show(double a) {
		System.out.println(" Show CA with 1 Double Parameter");
	}

	public int show(int a, int b) {			// return type can be different.
		System.out.println("int Show CA with 2 Int Parameter");
		return a+b;
	}
}

class CB extends CA // C++ : class CB : public/private/protected CA{}
{
	public void show() {
		System.out.println(" Show CB ");
		super.show(10); // super is used to call parent class Method or variable.
	}

	// public int show(int a) {				// return type int is not compatible with void
	// 	return a;
	// }
	
	public char show(char a) {				// parameter is different so it is not overriding.
		return a;
	}

	
	// private void show(int a) {				// attempting to assign weaker access privileges; was public
	// 	return a;
	// }

	public void sayCB() {
		System.out.println(" sayCB");
	}
}
