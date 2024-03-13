//Divide the string into multiple string is called as Split string.
public class Main {
	public static void main(String[] args) {
		String sp = ("Sagar, Suraj, Bharati, Yogita");

		System.out.println(sp);

		String names[] = sp.split(","); // String splited by comma.

		System.out.println("name[2] :" + names[2]); // name[2] : Bharati

		System.out.println("---------------------------");

		for (String val : names) // Advance for loop.
			System.out.println(val);
	}
}
