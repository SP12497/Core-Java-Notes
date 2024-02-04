import java.util.Scanner;

/*
String:
	Once we creates a string object, we can't perform any changes in the existing object.
	if we are trying to perform any changes with those changes a new object (new memory) will be created .
	this non changeable nature is nothing but immutability.
StringBuffer:
	Once we creates a StringBuffer object we can perform any type of changes in the existing object.
	this changeble nature is nothing but mutability of the StringBuffer object.

Immutable means after changing the value of object, new object (new memory) is created.
It does not change the same value, bcoz old object (memory) is also pointed by the another objects.
*/

public class Main 
{
	public static void main(String[] args) {
		
		String s1 = "SP";
		System.out.println("s1 : " + s1.hashCode());
		s1="Patil";
		System.out.println("s1 : " + s1.hashCode());



		String str1 = "Sagar";		//Sagar added in the string pool then create memory in heap and added into it. //Here 2 obj are created.
		String str2= "Sagar";		//Sagar available in string pool then it will return the heap address of str1 to str2

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
	}
}
