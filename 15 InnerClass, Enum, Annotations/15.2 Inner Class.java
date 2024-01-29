/* Inner Class ($): A$B
		1. Member / Non-Static Inner Class
		2. Static / Nested Inner Class
		3. Anonymous Inner Class
		4. Lambda Inner Class
*/
//outer class is only be public , abstract or final , never static.

class A
{
	int i;
	public void print(){System.out.println("Class A print() Method");}
	
	
	class B		//Member / Non-Static Inner Class
	{
		public void show(){System.out.println("Member / Non-Static InnerClass B Show() Method " + i);}
	}
	
	
	static class C	//Static / Nested Inner Class	
	{
		public void show(){System.out.println("Static / Nested Inner Class C show() Method " );}
	}
	
	
}

@FunctionalInterface	//Interface with only one method
interface FI
{
	public void print();
}


public class CMain
{
	public static void main(String[] args) 
	{
		A obj = new A();
		obj.i= 10;
		
	//1. Member / Non-Static Inner Class
		A.B obj2 = obj.new B();		
		obj2.show();

	//	2. Static / Nested Inner Class

		A.C obj3 = new A.C();		//same as C obj = new C()
		obj3.show();



	//3. Anonymous Inner Class (CMain$1.class)
		FI obj4 = new FI()
				{
					public void print()
					{
						System.out.println("Anonymous Inner Class Print() Method");
					}
				};
		obj4.print();
	
	//4. Lambda Inner Class 
		FI obj5= ()-> System.out.println("Lambda Inner Class Print Method");
		obj5.print();
	
	}
}