import java.util.Arrays;

/*
.trim(),		 	.concat(),			.substring(),		 
.replace(), 		.replaceAll(),		.startsWith(),			.endsWith(),		 
.indexOf(),		 	.charAt(),			.equalsIgnoreCase(),	.equals()	 
.split(),		 	.toCharArray(),		.toUpperCase(),		 
.toLowerCase(),	 	.length(),			.hashCode(),		 
.compareTo(),	 	.format(),			.intern(),		 
.binarySearch(), 	.toString()			
*/
public class _03String {
	public static void main(String[] args) {
		int [] uniCodeArr = { 72, 101, 108, 108, 111 };	// Unicode code points for 'H', 'e', 'l', 'l', 'o'
		String s1 = "Sagar";	// added in the string pool then create memory in heap and added into it. // Here 2 obj are created.
		String str = new String("Sagar");	// The string literal "Sagar" is in the pool, but new String("Sagar") creates a new String object in the heap that is not automatically added to the String pool. Therefore, c is a new String instance with the same value as the literal but a different reference.
		String s2 = str;
		String str3 = new String("Sagar".toCharArray(), 0, 2); // Sa // accept only characher array
		String str4 = new String(uniCodeArr, 0, uniCodeArr.length);	// Hello
		String s3intern = new String("Sagar").intern();	// new String("Sagar") creates a new String object in the heap, and .intern() ensures that the resulting reference points to the string in the String pool. If "Sagar" is already in the pool, d will refer to the same pooled instance.

		System.out.println(str3);										// Sa
		System.out.println(str4);										// Hello
		System.out.println("str==s1: " + str == s1);					// false	// comparing based on reference ie. stack address
		System.out.println("str.equals(s1): " + str.equals(s1));		// true		// comparing based on value ie. heap address
		System.out.println("str.compareTo(s1): " + str.compareTo(s1)); 	// true
		System.out.println("str==s1: " + s1 == s3intern);				// true
		System.out.println("str==s1: " + str == s3intern);				// false
		

		System.out.println("hashCode str:" + str.hashCode());		// 79644074
		System.out.println("hashCode s1:" + s1.hashCode());			// 79644074
		System.out.println("hashCode s2:" + s2.hashCode());			// 79644074
		str = "patil";
		System.out.println("hashCode str:" + str.hashCode()); 		// 106438918
		System.out.println("hashCode s2:" + s2.hashCode()); 		// 79644074
		// Here, instead of just changing the value, new Address got created for str.
		// This behavior is called String Immutable.

		System.out.println("length() method: " + str.length());
		System.out.println(str.toUpperCase());	// .toLowerCase(),
		System.out.println(str.indexOf("a")); 					// 1
		System.out.println(str.charAt(1)); 					// a
		String s3 = "saGar";
		System.out.println("equalsIgnoreCase: " + s1.equalsIgnoreCase(s3)); // true

		String sentence = "My Name is sagar";
		String words[] = sentence.split(" ");
		System.out.println(Arrays.toString(words));					// [My, Name, is, sagar]
		for (String w : words) {
			System.out.println(Arrays.binarySearch(words, w) + ": " + w);	// 0: My	// 1: Name	//...
		}

		int age = 30;
		String formattedString = String.format("Name: %s, Age: %d", s1, age);
		System.out.println(formattedString); 						// Name: Sagar, Age: 30

		if (s1 != null)
			System.out.println(s1.length()); 						// Avoid NullPointerException

		System.out.println("-------");
		str = "Hello, World!";
		System.out.println(str.substring(7)); 			// "World!" // cut till 7, start from 8
		System.out.println(str.substring(7, 9)); // "Wo!" // end at 9
		System.out.println(str.replace("World", "Sagar")); // Hello, Sagar!
		System.out.println(str.startsWith("Hello")); 		// true
		System.out.println(str.endsWith("World!"));			// true

		str = "sagar";
		char[] charArr = str.toCharArray();

		for (char c : charArr) {
			System.out.print(c + ",");								// s,a,g,a,r,
		}
		System.out.println();
		String str2 = Arrays.toString(charArr); // "[s, a, g, a, r]"
		str2 = new String(charArr); // sagar
		str2 = new String(charArr, 0, 2); // sa
		System.out.println(str2);
		
		
		System.out.println("---compareTo()---");
		/* 
		Returns 3 values:
			-ve:	first obj is smaller
			0:		both are same
			+ve:	first obj is bigger ie second object is smaller
			
			abc = 	97 98 99
			abe = - 97 98 101	=> 97-97 = 0 | 98-97=+1 | 99-101 = -2
			
		*/
		System.out.println("abc".compareTo("abe"));	// -2
		System.out.println("abc".compareTo("ace"));	// -1	// b-c
		String str11 = "apple";
		String str22 = "banana";
		String str33 = "apple";
		System.out.println(str11.compareTo(str22));					// -1
		System.out.println(str22.compareTo(str11));					// 1
		System.out.println(str11.compareTo(str33));					// 0
		System.out.println(str11.compareTo("appl"));	// 1	// total length 5-4
		System.out.println(str11.compareTo("app"));	// 2
		System.out.println(str11.compareTo("appleee"));// -2	// total length 5-7
	}
}
