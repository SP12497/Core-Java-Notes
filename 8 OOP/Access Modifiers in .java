
Java access modifiers :

Access Specifier is used to provide your code in Java whether other classes can access your code or not.

Access Modifier provides both Access Specifier and Access Modifiers for creating access to your Java code for other classes. Here modifier is also used to do the same task but there are limitations.
	private : specific class
	default : specific package
	public : Any class or package
	protected : subsiding class (inheritance)

Class:
 class can be, default, public or abstract
 can can not be private protected.
 but inner class can be private, protected, public or default.
	eg.
		class A{}	//default	//default is only inside package
		public class B 			//inside and outside package
		class C { private class D {}}
		
Variables:
	int a;
	public  int b;
	private int c;
	protected int d;
	
Method:
	method can be default, public, private, protected.