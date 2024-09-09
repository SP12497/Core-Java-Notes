import java.util.Arrays;

public class _01Character {
	public static void main(String[] args) {
//		char c = "A";	// Type mismatch: cannot convert from String to char
		char c = 'A';
		Character obj = 'a';
		Character obj2 = new Character('1');
		char c2 = obj.charValue();
		
		System.out.println(c + " " + obj + " " + obj2);	// A a 1
		System.out.println((int)c + " " + (int)obj + " " + (int)obj2);	// 65 97 49
		
		char ws = ' ';
		System.out.println(Character.isWhitespace(ws));	// true
		
		System.out.println(Character.toUpperCase(c));	// A	
		// System.out.println(obj.toUpperCase());		// Error: The method toUpperCase(char) in the type Character is not applicable for the arguments ()
		System.out.println(obj.toUpperCase(obj));		// A		// toUpperCase is a static method. So, we can call it using class name or object name.
		System.out.println(Character.toUpperCase(obj));	// A
		System.out.println(Character.toUpperCase(obj2));// 1
		
		System.out.println(Character.isDigit(obj2));	// true
		System.out.println(Character.isLetter(obj));	// true
		System.out.println(Character.isLowerCase(obj));	// true
		
		
		char arr[] = {'a', 'b', 'c'};
		System.out.println(Arrays.toString(arr));	// "[a, b, c]"
		arr = "Sagar".toCharArray();
		System.out.println(Arrays.toString(arr));	// "[S, a, g, a, r]"
		
		
		
		
	}
}
