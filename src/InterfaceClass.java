//An interface is a completely "abstract class" that is 
//	used to group related methods with empty bodies:

//To access the interface methods, the interface must be "implemented" (kinda like inherited) by another class with the implements keyword (instead of extends).
//	The body of the interface method is provided by the "implement" class:
/*
*  Notes on Interfaces: 
	 * 		a] Like abstract classes, interfaces cannot be used to
	 * 		   create objects (in the example above, it is not possible to create an
	 * 		   "Animal" object in the MyMainClass) 
	 * 		b] Interface methods do not have a body - the body is provided by the "implement" class
	 * 		c] On implementation of an interface, you must override all of its methods 
	 * 		d] Interface methods are by default : public abstract  , no need to specity.
	 * 		e] Interface attributes/variables are by default public, static and final 
	 * 		e] An interface cannot contain a constructor 
	 * 		   (as it cannot be used to create objects) 
	 *   	f] To implement multiple interfaces, separate them with a comma:
 * 
 * Why And When To Use Interfaces? 
 *	 1) To achieve security - hide certain details and 
 *	    only show the important details of an object (interface).
 * 
 *	 2) Java does not support "multiple inheritance on classes" (a class can only inherit
 *	 from one superclass). However, it can be achieved with interfaces, because
 *	 the class can implement multiple interfaces. Note: To implement multiple
 * 	 interfaces, separate them with a comma (see example below). 


 */

//Types of Interface :
//	1] Marker Interface -> without any methods
//	2] SAM -> Single Abstract Method -> Functional Interface
//	3] Normal Interface.
//		There are many more types of interfaces...





interface Inter1
{
	void show();	//by default public abstract , no need to specify.
}
interface Inter2
{
	public abstract void print();
}
//-----------------
class ClassInter implements Inter1 , Inter2		// Class Support Multiple Interface
{
	public void show()
	{
		System.out.println("	Abc show() Inside interClass interface");
	}
	public void print()
	{
		System.out.println("	Xyz print() Inside interClass interface");
	}
}
//--------------

interface Inter3 extends Inter1, Inter2 {}			// Interface Support Multiple Inheritance

//------------------

interface Inter4
{
	default void common()
	{
		System.out.println("	Inter4 Common()");
	}
	default void defaultMethod()
	{
		System.out.println("	Inter4 Default defaultMethod");
	}
	static void staticMethod()
	{
		System.out.println("	Inter4 Static staticMethod");
	}
}
class Class1 
{
	public void common()
	{
		System.out.println("	Class1 common");
	}
}
class Class2 extends Class1 implements Inter4		//Both 1st Class and then Interface otherwise it throws error
{
	//- Here class1 and interface4 both had defined common() method.
	//- Class have more Priority than Interface.
}

//--------

// class 3 implements Inter4 extends Class1			//Error : 1st Inheritance then Interface








public class InterfaceClass 
{
	public static void main(String[] args)
	{

		// Inter1 obj1 = new Inter1(); 		//Error Interface does not contain any Constructor.
	
System.out.println("Reference of Inter1 interface using constructor of child class :");
		Inter1 obj1 = new ClassInter();
		obj1.show();
		//obj1.print();		//error : print in not Inter1 type.

//-------------------
System.out.println("Create constructor of Interface only inside in main. :");

		Inter1 obj2 = new Inter1()
		{
			public void show()
			{
				System.out.println("	In Inter1 Interface using Inter1() constructor created inside main.");
			}
		};					// use Semicolon ; 
		
		obj2.show();
		
//-------------------
System.out.println("Lambda Expression (->)  introduced in JAVA 1.8 : works only with functional interface" );	

		Inter2 x = () -> System.out.println("	In Inter2 Interface using Lambda Expression created inside main.");
		
		x.print();
		
//-------------------
System.out.println("Object of ClassInter class with Multiple Interface :");	

		ClassInter c = new ClassInter();
				
		c.show();
		c.print();
		
System.out.println("class Class2 extends Class1 implements Inter4 :");
//Class have more Priority than Interface.

		Class2 c2 = new Class2();
		{
			c2.common();		//Class1 common() method will be call bcoz class have more priority than interface.
		}
		
//Static and Default method inside Interface
System.out.println("Static Method inside Inter4");
		Inter4.staticMethod();			//Static Method access by Interface/Class name
		//Inter4.defaultMethod();		//Default Method access by Object
	}	
}