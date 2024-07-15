//Singleton Design Pattern : Lazy Initialization with thread safe
//	Thread-Safe Singletons -> to make the global access method synchronized 
// so that only one thread can execute this method at a time. 
//using synchronized we make getInstance as Thread Safe

class ABC 
{
	static ABC obj ;//  Declaration only	 
	
	private ABC()   // No default constructor to create object
	{
		System.out.println("Instance Created");
	}

	//static public ABC getInstance()	//Lazy Initialization
	static public synchronized ABC getInstance()//Thread-Safe Singletons
	{
		if(obj==null)
		{
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			
			obj= new ABC();	//Instance Created	
		}
		return obj;	//return only one object every time.
	}

}

public class _15_13_Singleton_ThreadSafe_Synchronized
{
	public static void main(String[] args) 
	{
		//ABC obj = new ABC(); //Constructor is private so not allowed		
	
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ABC obj1 = ABC.getInstance();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				ABC obj2 = ABC.getInstance();
			}
		});
		
		
		t1.start();
		t2.start();
		
	}
}