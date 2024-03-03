/*
Final variables:
	- When a variable is declared with final keyword, its value can't be modified, essentially a constant. 
	- This also means that you must initialize a final variable.
	- If the final variable is a reference, this means that the variable cannot be re-bound to reference another object,
	   but internal state of the object pointed by that reference variable can be changed 
	   i.e. you can add or remove elements from final array or final collection. 
	- It is good practice to represent final variables in all UPPER_CASE, using underscore to separate words.
*/

//Final VARIABLE 		: To create Constant Variable
//Final Method 			: Prevent Method Overriding  
//Final Class 			: Prevent Inheritance

public class Main {
	public static void main(String[] args) {
		int i = 10; // Local Variable // Primitive Variable
		i++;
		System.out.println("Primitive Variable i =" + i);

		// ------------------------
		// Final Variable: Once initialize, you can not modified
		final int k = 10; // initialize at the time of declaration.
// 		k++; // error: cannot assign a value to final variable k
		System.out.println("Final Primitive Variable k =" + k);

		// OR

		final int j;
		j = 10; // Once initialize, you can not modified
		// j++; // error: cannot assign a value to final variable k
		System.out.println("Primitive Variable j =" + j);

		// Final Method:

		FinalA obj = new FinalA();
		obj.show();
		obj.show(100);
		System.out.println("FinalA obj1 = new InheritB() : ");
		FinalA obj1 = new InheritB(); // error
		obj1.show();
		obj1.show(11);

	}
}

// --------------
// Final Method :
// --------------
class FinalA {
	int x; // Instance Variable
	static int y; // Class Variable // Static variable

	final public void show() // Final Method : can't override but can overload
	{
		System.out.println("FinalA : Show A");
	}

	public void show(int a) {
		System.out.println("FinalA : Show A  :" + a);
	}
}

class InheritB extends FinalA {
	// once Final , we cant override the final method.
	// public void show() { System.out.println("Show B"); }
	
	public void show(int a) {
		System.out.println("InheritB : Show A  :" + a);
	}
}

// -------------
// final Class :
// -------------

final class FinalClass {
	final public void show() // Final Method
	{
		System.out.println("Show A");
	}
}

// class InheritFinalA extends FinalClass {} //We cannot Inherit final Class.
