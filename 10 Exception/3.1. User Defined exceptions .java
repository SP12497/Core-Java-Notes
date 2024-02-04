
//A Class that represents user-defined expception 
class MyException extends Exception {
	public MyException(String s) {
		// Call constructor of parent Exception
		super(s);
	}
}

//A Class that uses above MyException 
public class Main {
	// Driver Program
	public static void main(String args[]) {
		try {
			// Throw an object of user defined exception
			throw new MyException("GeeksGeeks");
		} catch (MyException ex) {
			System.out.println("Caught");

			// Print the message from MyException object
			System.out.println(ex.getMessage());  // GeeksGeeks
			System.out.println(ex);	// myPackage.MyException: GeeksGeeks
			ex.printStackTrace();
			/* 
			myPackage.MyException: GeeksGeeks
				at myPackage.Main.main(Main.java:17)
			*/
			System.out.println(ex.getCause());
		}
	}
}
