package Strings;
//Mutable String :
// after changing the data in Immutable string , it will not create new object , 
// when we want to affect on same old object then we use "Mutable String".

//We can create  "StringBuffer" or "StringBuillder" class object

public class MutableString {
	
	public static void main(String[] args) {
		
		StringBuffer sbf1 = new StringBuffer("Sagar");
		StringBuffer sbf2 = new StringBuffer("Sagar");
		
		
		
		//sbf1.append(" Patil");
		
		System.out.println("sbf1 : " + sbf1.hashCode());
		System.out.println("sbf2 : " + sbf2.hashCode());				
	}
}
