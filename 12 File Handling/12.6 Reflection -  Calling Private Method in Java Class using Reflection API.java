//Calling Private method of another class using Reflection
/*
  Reflection is an API which is used to examine or modify the behavior of methods, classes, interfaces at runtime.
	- The required classes for reflection are provided under java.lang.reflect package.
	- Reflection gives us information about the class to which an object belongs and also the methods of that class which can be executed by using the object.
	- Through reflection we can invoke methods at runtime irrespective of the access specifier used with them.
*/
import java.lang.reflect.Method;

public class CMain
{
	public static void main(String[] args) throws Exception  
	{
		Class c = Class.forName("Test");
		Test t = (Test) c.newInstance();
		Method m = c.getDeclaredMethod("show", null); //("Method name" , "Parameter Types")
		m.setAccessible(true);	//Make private method accessible.
		m.invoke(t, null);	//("Object" , "Parameter Types")
	}
}

class Test 
{
	private void show()
	{
		System.out.println("In show()...");
	}
}
