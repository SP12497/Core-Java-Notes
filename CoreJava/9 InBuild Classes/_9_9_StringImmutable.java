import java.util.Scanner;

/*
String:
	Once we creates a string object, we can't perform any changes in the existing object.
	if we are trying to perform any changes with those changes a new object (new memory) will be created .
	this non changeable nature is nothing but immutability.
StringBuffer / StringBuilder:
	Once we creates a StringBuffer or StringBuilder object, we can perform any type of changes in the existing object.
	this changeble nature is nothing but mutability of the StringBuffer object.

Immutable means after changing the value of object, new object (new memory) is created.
It does not change the same value, bcoz old object (memory) is also pointed by the another objects.
*/

public class _9_9_StringImmutable 
{
	public static void main(String[] args) {
		
		String s1 = "SP";
		System.out.println("s1 : " + s1.hashCode());
		s1="Patil";
		System.out.println("s1 : " + s1.hashCode());



		String str1 = "Sagar";		//Sagar added in the string pool then create memory in heap and added into it. //Here 2 obj are created.
		String str2= "Sagar";		//Sagar available in string pool then it will return the heap address of str1 to str2. // Here, no objects are created

		//both pointing to same address 
		System.out.println("str1 : " + str1.hashCode());
		System.out.println("str2 : " + str2.hashCode());

		
//   here memory created in HEAP and store "Sagar" in memory.
//	suppose "Sagar" stored with memory address is 1001.
//	then both str1 and str2 are pointing to the same memory location ie. 1001 addresss in heap.

		System.out.println("2----------------------------------");
		str1 ="Suraj";	// Now here new memory is created for str1 at another address.		
		System.out.println("str1 : " + str1.hashCode());
		System.out.println("str2 : " + str2.hashCode());

		System.out.println("3----------------------------------");
		String obj = new String("Sagar");
		System.out.println("obj : " + obj.hashCode());
		System.out.println("str2 : " + str2.hashCode());
//		System.out.println("str1 : " + str1.hashCode());

// ----------------------------------
		String a = "aa";		// String literals in Java are automatically added to the String pool.
		String b = "aa";		// here. b will refer to the same string literal "aa" already in the String pool.
		System.out.println(a == b);		// true

		// Creates a new String instance in the heap, not in the pool:
		String c = new String("aa");		// The string literal "aa" is in the pool, but new String("aa") creates a new String object in the heap that is not automatically added to the String pool. Therefore, c is a new String instance with the same value as the literal but a different reference.
		System.out.println(a == c);		// false
		
		String d = new String("aa").intern();	// new String("aa") creates a new String object in the heap, and .intern() ensures that the resulting reference points to the string in the String pool. If "aa" is already in the pool, d will refer to the same pooled instance.
		System.out.println(a == d);		// true
		System.out.println(c == d);		// false
				
		String name = new String("Nilesh").intern();	
		String name2 = new String("Nilesh").intern();
		System.out.println(name == name2);	// true
	/*
	 In this example, a and b both reference the same string instance retrieved from the String Pool, so a == b evaluates to true. 
	 Similarly, a and d reference the same string instance after interning d, so a == d also evaluates to true.

	However, 
		- it's important to note that while string literals are automatically interned, 
		- strings created using the new keyword (like String c = new String("aa")) are not interned by default. 
		  You need to explicitly call intern() to add them to the String Pool, as shown in the example.
	 */
	}
}
