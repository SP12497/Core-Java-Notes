import java.util.Arrays;

public class _03String {
	public static void main(String[] args) {
		int [] intarr = {72, 101, 108, 108, 111};	// Unicode code points for 'H', 'e', 'l', 'l', 'o'
		String str = new String("Sagar");
		String s1 = "Sagar";
		String s2 = str;
		String str3 = new String("Sagar".toCharArray(), 0, 2); // Sa // accept only characher array
		String str4 = new String(intarr, 0,5) ; // Sa // accept only characher array

		System.out.println(str3);	// Sa
		System.out.println(str4);	// Hello
		System.out.println("str==s1: " + str == s1); // false
		System.out.println("str.equals(s1): " + str.equals(s1)); // true
		System.out.println("str.equals(s1): " + str.compareTo(s1)); // true
		

		System.out.println("hashCode str:" + str.hashCode()); // 79644074
		System.out.println("hashCode s1:" + s1.hashCode()); // 79644074
		System.out.println("hashCode s2:" + s2.hashCode()); // 79644074
		str = "patil";
		System.out.println("hashCode str:" + str.hashCode()); // 106438918
		System.out.println("hashCode s2:" + s2.hashCode()); // 79644074
		// Here, instead of just changing the value, new Address got created for str.
		// This behavior is called String Immutable.

		System.out.println("length() method: " + str.length());
		System.out.println(str.toUpperCase());
		System.out.println(str.indexOf("a")); // 1
		System.out.println(str.charAt(1)); // a
		String s3 = "saGar";
		System.out.println("equalsIgnoreCase: " + s1.equalsIgnoreCase(s3)); // true

		String sentence = "My Name is sagar";
		String words[] = sentence.split(" ");
		System.out.println(Arrays.toString(words));
		for (String w : words) {
			System.out.println(Arrays.binarySearch(words, w) + ": " + w);

		}

		int age = 30;
		String formattedString = String.format("Name: %s, Age: %d", s1, age);
		System.out.println(formattedString); // Name: Sagar, Age: 30

		if (s1 != null)
			System.out.println(s1.length()); // Avoid NullPointerException

		System.out.println("-------");
		str = "Hello, World!";
		System.out.println(str.substring(7)); // "World!" // cut till 7, start from 8
		System.out.println(str.substring(7, 9)); // "Wo!" // end at 9
		System.out.println(str.replace("World", "Sagar")); // Hello, Sagar!
		System.out.println(str.startsWith("Hello")); // Hello, Sagar!
		System.out.println(str.endsWith("World!")); // Hello, Sagar!

		str = "sagar";
		char[] charArr = str.toCharArray();

		for (char c : charArr) {
			System.out.println(c);
		}
		String str2 = Arrays.toString(charArr); // [s, a, g, a, r]
		str2 = new String(charArr); // sagar
		str2 = new String(charArr, 0, 2); // sa
		System.out.println(str2);
		
		
		System.out.println("---compareTo()---");
		/* 
		Returns 3 values:
			-ve:	first obj is smaller
			0:		both are same
			+ve:	secord object is smaller
			
			abc = 	97 98 99
			abe = - 97 98 101	=> 97-97 = 0 | 98-98=0 | 99-101 = -2
			
		*/
		System.out.println("abc".compareTo("abe"));	// -2
		System.out.println("abc".compareTo("ace"));	// -1	// b-c
		String str11 = "apple";
		String str22 = "banana";
		String str33 = "apple";
		System.out.println(str11.compareTo(str22));	// -1
		System.out.println(str22.compareTo(str11));	// 1
		System.out.println(str11.compareTo(str33));	// 0
		System.out.println(str11.compareTo("appl"));// 1
		System.out.println(str11.compareTo("app"));	// 2
		System.out.println(str11.compareTo("appleee"));// -2
	}
}
