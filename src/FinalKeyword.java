/*
Final variables:
	- When a variable is declared with final keyword, its value can’t be modified, essentially a constant. 
	- This also means that you must initialize a final variable. 
	- If the final variable is a reference, this means that the variable cannot be re-bound to reference another object, 
	   but internal state of the object pointed by that reference variable can be changed 
	   i.e. you can add or remove elements from final array or final collection. 
	- It is good practice to represent final variables in all UPPER_CASE, using underscore to separate words.
*/

//Final Variable 	: To create Constant Variable
//Final Method 		: Prevent Method Overriding  
//Final Class 		: Prevent Inheritance


public class FinalKeyword 
{
	public static void main(String[] args) 
	{
		int i=10;	// Local Variable // Primitive Variable
		i++;
		System.out.println("Primitive Variable i ="+ i);
		
//------------------------
//Final Variable : Once initialize, you can not modified
//		final int j=10;					
//		j++;
//		System.out.println("Primitive Variable i ="+ j);
		
		//OR
		
		final int j;
		j=10;					//Once initialize, you can not modified
	//	j++;
		System.out.println("Primitive Variable j ="+ j);


		FinalA obj = new FinalA();
		obj.show();

		InheritB obj1 = new InheritB();
		obj1.show();
	}
	
	
	
}

//--------------
//Final Method :
//--------------

class FinalA
{
	int x;			//Instance Variable
	static int y;	//Class Variable // Static variable 
	final public void show() 	//Final Method
	{
		System.out.println("Show A");
	}
}

class InheritB extends FinalA
{ 
	 //once Final , we cant override the final method.  
	// public void show() { System.out.println("Show B"); }

}

//-------------
//final Class :
//-------------

final class FinalClass
{
	final public void show() 	//Final Method
	{
		System.out.println("Show A");
	}
}

//class InheritB extends FinalA {} 	//We cannot Inherit final Class.

