package Strings;
//String is Immutable because to increase the performance of the code.
// FlyWeight : more than two reference/object can share/pointed to the same object (same memory).

//Immutable means after changing the value of object, new object (new memory) is created.
//	Does not change the same value, bcoz old object (memory) is also pointed by the another objects.

public class ImmutableString 
{
	public static void main(String[] args) {
		
		String str1 = "Sagar";
		String str2= "Sagar";
		
		System.out.println("str1 : " + str1.hashCode());
		System.out.println("str2 : " + str2.hashCode());
		
		System.out.println("----------------------------------");
		
//   here memory created in HEAP and store "Sagar" in memory.
//	suppose "Sagar" stored with memory address is 1001.
//	then both str1 and str2 are pointing to the same memory location ie. 1001 addresss in heap.
	
		str1 ="Suraj";	// Now here new memory is created at another address suppose in 2001 address in heap.
		

		System.out.println("str1 : " + str1.hashCode());
		System.out.println("str2 : " + str2.hashCode());

		
		
		String s1 = new String("Yogesh");
		
		
		System.out.println("s1 : " + s1.hashCode());
		System.out.println("s1 : " + str1.hashCode());

	}

}
