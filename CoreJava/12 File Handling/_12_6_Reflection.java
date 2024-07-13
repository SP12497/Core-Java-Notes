// Calling a private method of another class using Reflection

/*
  Reflection is an API used to examine or modify the behavior of methods, classes, and interfaces at runtime.
  - The required classes for reflection are provided under the java.lang.reflect package.
  - Reflection provides information about the class to which an object belongs and the methods of that class that can be executed using the object.
  - Through reflection, we can invoke methods at runtime irrespective of the access specifier used with them.
*/

import java.lang.reflect.Method;

public class _12_6_Reflection {
    public static void main(String[] args) throws Exception {
        Class<?> c = Class.forName("Test");
        Test t = (Test) c.getDeclaredConstructor().newInstance();
        Method m = c.getDeclaredMethod("show"); // ("Method name", "Parameter Types")
        m.setAccessible(true); // Make private method accessible.
        m.invoke(t); // ("Object", "Parameters")
    }
}

class Test {
    private void show() {
        System.out.println("In show()...");
    }
}

/*
Reflection API: Allows examining or modifying classes, methods, and interfaces at runtime.
java.lang.reflect package: Contains the necessary classes for reflection.
Class.forName("Test"): Loads the Test class dynamically.
getDeclaredConstructor().newInstance(): Creates a new instance of the Test class.
getDeclaredMethod("show"): Retrieves the private method show of the Test class.
setAccessible(true): Makes the private method accessible.
m.invoke(t): Invokes the show method on the instance t of the Test class.
 */
