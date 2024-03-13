// Java program to illustrate error in case
// of unhandled exception
class Main {
    public static void main(String[] args)
    {
        Thread.sleep(10000);
        System.out.println("Hello Geeks");
    }
}

// Output: 
// error: unreported exception InterruptedException; must be caught or declared to be thrown


// ================================================



// Java program to illustrate throws
class Main {
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(5000); // wait 5 sec, then execute next line
		System.out.println("Hello Geeks");
	}
}

// Output: 
// Hello Geeks