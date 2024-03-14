//Singleton Design Pattern : Double-Checked Locking Singletons -> This will reduce the resources
//multiple thread runs same time so it happens that multiple getInstance method calls same time so multiple obj is created.  So overcome this problem we use synchronized function. Thats why it is called as Double-Checked Locking Singletons.

class ABC {
	static ABC obj;// Declaration only

	private ABC() // No default constructor to create object
	{
		System.out.println("Instance Created");
	}

	static public ABC getInstance() // Double-Checked Locking
	{
		if (obj == null) // check1
		{
			// multiple thread runs same time so it happens that multiple getInstance method
			// calls same time so multiple obj is created. So overcome this problem we use
			// synchronized function

			synchronized (ABC.class) {
				if (obj == null) // check2 after synchronized
				{
					obj = new ABC(); // Instance Created
				}
			}
		}
		return obj; // return only one object every time.
	}
}

public class SingletonClass {
	public static void main(String[] args) {
		// ABC obj = new ABC(); //Constructor is private so not allowed

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				ABC obj = ABC.getInstance();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				ABC obj = ABC.getInstance();
			}
		});

		t1.start();
		t2.start();

	}
}