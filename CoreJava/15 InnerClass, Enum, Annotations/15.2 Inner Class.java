
/* Inner Class ($): A$B
1. Member / Non-Static Inner Class
2. Static / Nested Inner Class
3. Anonymous Inner Class
4. Lambda Inner Class
*/
//outer class is only be public, abstract, default or final, never static.

class ParentClass // default
{
	int i;

	public void print() {		System.out.println("Class A print() Method");
	}

	class memberClass // Member / Non-Static Inner Class
	{
		public void show() {
			System.out.println("Member / Non-Static InnerClass B Show() Method " + i);
		}
	}

	static class StaticClass // Static / Nested Inner Class
	{
		public void show() {
			System.out.println("Static / Nested Inner Class C show() Method ");
		}
	}

}

@FunctionalInterface // Interface with only one method
interface FI {
	public void print();
}

public class Main {
	public static void main(String[] args) {
		ParentClass pObj = new ParentClass();
		pObj.i = 10;

//	1. Member / Non-Static Inner Class
		ParentClass.memberClass instanceObj1 = pObj.new memberClass();
		instanceObj1.show();
		ParentClass.memberClass instanceObj2 = new ParentClass().new memberClass();

//	2. Static / Nested Inner Class

		ParentClass.StaticClass obj3 = new ParentClass.StaticClass(); // same as C obj = new C();
		obj3.show();

//	3. Anonymous Inner Class (CMain$1.class);
		FI obj4 = new FI() {
			public void print() {
				System.out.println("Anonymous Inner Class Print() Method");
			}
		};
		obj4.print();

//	4. Lambda Inner Class
		FI obj5 = () -> System.out.println("Lambda Inner Class Print() Method");
		obj5.print();
	}
}
