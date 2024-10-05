// the no divided by 1 or itself is called as prime no. ie. 2,3,5,7,9,11,13,17,19

public class Prime_Number {
	public static void main(String[] args) {
		int num = 6;

		boolean isPrime = true;

		for (int i = 2; i <= num / 2; i++) // starts from 2. because
		{
			if (num % i == 0) {
				isPrime = false;
				break;
			}
		}

		if (isPrime) {
			System.out.println(num + " is prime");
		} else {
			System.out.println(num + " is not prime");
		}
	}

}
