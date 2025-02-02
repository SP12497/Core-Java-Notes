import java.util.Scanner;
/*
In this example:
    next(): 'Sagar' - Reads the next token from the input as a string until the next whitespace or delimiter.
	nextLine(): 'Sagar Patil' - Reads a line of text as a string until the newline character.
	nextInt(): '28' - Reads the next token from the input as an integer.
	nextDouble(): '5.11' - Reads the next token from the input as a double.
	nextBoolean(): 'true' - Reads the next token from the input as a boolean.
    next().charAt(0): 'S' - Reads the first character of the next token.

Make sure to import java.util.Scanner at the beginning of your Java file. 
This example covers various scenarios for reading different types of input using the Scanner class in Java.
*/

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reading string input
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);

        // Reading integer input
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        System.out.println("Age: " + age);

        // Reading double input
        System.out.print("Enter your height (in meters): ");
        double height = scanner.nextDouble();
        System.out.println("Height: " + height + " meters");

        // Reading boolean input
        System.out.print("Are you married? (true/false): ");
        boolean isMarried = scanner.nextBoolean();
        System.out.println("Married: " + isMarried);

        // Reading character input
        System.out.print("Enter your gender (M/F): ");
        char gender = scanner.next().charAt(0); // Reading the first character
        System.out.println("Gender: " + gender);

        // Reading input until a delimiter is encountered
        System.out.print("Enter a sentence (end with '.'): ");
        String sentence = scanner.next(); // Reads until the next whitespace or delimiter
        System.out.println("Sentence: " + sentence);

        scanner.close();
    }
}
