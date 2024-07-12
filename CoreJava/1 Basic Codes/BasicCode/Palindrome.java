// 454 == 454	-> palindrome
// 234 != 432	-> not palindrome

class Palindrome {
	public static void main(String args[]) {
		int r, sum = 0, temp;
		int n = 454;// It is the number variable to be checked for palindrome

		temp = n;

		while (n > 0) {
			r = n % 10; // getting remainder / get last digit of number
			sum = (sum * 10) + r;
			n = n / 10; // remove last digit of number
		}

		if (temp == sum)
			System.out.println("palindrome number ");
		else
			System.out.println("not palindrome");
	}
}
