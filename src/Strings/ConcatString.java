package Strings;

import java.util.Scanner;

public class ConcatString {
	public static void main(String[] args) 
	{
		String s1, s2;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter 2 Strings here :");
		s1 = sc.nextLine(); // Hello Students.
		s2 = sc.nextLine(); // Welcome to String Code.

		int length1 = s1.length();

		System.out.println("String 1 length is : " + length1);

		// String s3 = s1.concat(s2);
		String s3 = s1 + s2;

		System.out.println("Concated String is : " + s3);

		s1 = s1.toUpperCase();

		System.out.println("String 1 in Upper case is : " + s1);

	}
}