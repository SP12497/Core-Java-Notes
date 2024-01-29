import java.util.Scanner;

public class CMain {
	public static void main(String[] args) 
	{
		String s1, s2;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter 2 Strings here :");
		s1 = sc.nextLine(); // Hello Students.
		s2 = sc.nextLine(); // Welcome to String Code.

		int length1 = s1.length();

		System.out.println("String 1 length is : " + length1);

		String s3 = s1 + s2;
		System.out.println("Concated String s3 = s1 + s2 : " + s3);

		String s4 = s1.concat(s2);
		System.out.println("Concated String s4 = s1.concat(s2) : " + s4);


		s1 = s1.toUpperCase();

		System.out.println("String 1 in Upper case is : " + s1);
		
//------------------
		
		String s1 = new String("Swapnil"); 
    	String s2 = new String("Swapnil");
    	
//		StringBuffer s3 = new StringBuffer("Swapnil"); 
//    	StringBuffer s4 = new StringBuffer("Swapnil");
		
    	System.out.println(s1 == s2);		//false		//logic : s1 and s2 address in stack are different // check reference
    	System.out.println(s1.equals(s2));	//true	//logic : s1 and s2 content same.
    	
    	
    	//In general we use == operator for reference comparison 
    	// where as , .equals() method for content comparision.
 
/*
		.equals() method present in object class also meant for reference comparison only 
		based on our requirement we can override for content comparision.
		
		In String class, all wrapper class and all collection classes .equals() method is overridden for content comparision.
*/		

	}
}