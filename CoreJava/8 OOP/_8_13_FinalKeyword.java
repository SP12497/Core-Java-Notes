/*
Final variables:
	- When a variable is declared with final keyword, its value can't be modified, essentially a constant. 
	- This also means that you must initialize a final variable.
	- If the final variable is a reference, this means that the variable cannot be re-bound to reference another object,
	   but internal state of the object pointed by that reference variable can be changed 
	   i.e. you can add or remove elements from final array or final collection. 
	- It is good practice to represent final variables in all UPPER_CASE, using underscore to separate words.
*/

// Final VARIABLE 		: To create Constant Variable
// Final Method 		: Prevent Method Overriding  
// Final Class 			: Prevent Inheritance


public class _8_13_FinalKeyword {
	public static void main(String[] args) {
		int i = 10; // Local Variable // Primitive Variable
		i++;
		System.out.println("Primitive Variable i =" + i);	// 11

		// ------------------------
		// Final Variable: Once initialize, you can not modified
		final int k = 10; // initialize at the time of declaration.
		// k++; // error: cannot assign a value to final variable k
		System.out.println("Final Primitive Variable k =" + k);

		// OR

		final int j;
		// System.out.println(j);	// error: variable name might not have been initialized
		j = 10; // Once initialize, you can not modified
		// j++; // error: cannot assign a value to final variable k
		System.out.println("Primitive Variable j =" + j);

		// Final Method:

		FinaleMethodClass obj = new FinaleMethodClass();
		obj.show();
		obj.show(100);
		System.out.println("FinaleMethodClass obj1 = new InheritB() : ");
		FinaleMethodClass obj1 = new InheritB(); // error
		obj1.show();
		obj1.show(11);

	}
}

// --------------
// Final Method :
// --------------
class FinaleMethodClass {
	int x; // Instance Variable: by default 0
	static int y; // Class Variable // Static variable: by default 0

	final public void show() // Final Method : can't override but can overload
	{
		System.out.println("FinaleMethodClass : Show A");
	}

	public void show(int a) {	// We can overload the final method
		System.out.println("FinaleMethodClass : Show A  :" + a);
	}
}

class InheritB extends FinaleMethodClass {
	// once Final , we can't override the final method.
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
