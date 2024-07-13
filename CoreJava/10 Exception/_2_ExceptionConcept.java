import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2_ExceptionConcept {
	public static void main(String[] args) {
		int i, j, k = 0;
		int a[] = new int[4]; // array of 4
		// int b[] = {11,22};
		i = 8;
		j = 2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Enter denominator :");
			j = Integer.parseInt(br.readLine());

			k = i / j; // Error when denominator is j = 0. then it will throw an exception.
			System.out.println("K = " + k);

			for (int c = 0; c <= 10; c++) { // array size is 4 and we are iterating and assign more than 4 values.. ie ArrayIndexOutOfBoundsException
				a[c] = c + 1;
			}

			for (int value : a) {
				System.out.println(value);
			}
		} catch (IOException e) {
			System.exit(0); // skip finally block.
			System.out.println("IOException: some IO Error. ==> ");
		} catch (ArithmeticException e) { // It catch only arithmetic errors
			System.out.println("ArithmeticException: Cannot Divide by Zero  ==> " + e);
		} catch (ArrayIndexOutOfBoundsException e) { // It catch Array related errors.
			System.out.println("ArrayIndexOutOfBoundsException: Exceeded array index... Maximum no of values is 4 ==> " + e);
		} catch (Exception e) { // It catch all the errors
			System.out.println("Exception: Unknown exception" + e);
		}

		finally { // no matters what output, but this code always execute.
			System.out.println("----Finally Bye----");
		}
	}
}
