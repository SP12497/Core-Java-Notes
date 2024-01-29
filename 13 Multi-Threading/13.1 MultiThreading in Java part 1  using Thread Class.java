//We can achieve Multi-Threading using
	//	- class Thread
	//	- interface Runnable

/*
What is Multithreading in Java?
 MULTITHREADING in Java is a process of executing two or more threads simultaneously to maximum utilization of CPU. 
 Multithreaded applications execute two or more threads run concurrently. 
 Hence, it is also known as Concurrency in Java. 

 Each thread runs parallel to each other. 

 Mulitple threads don't allocate separate memory area, hence they save memory. Also, context switching between threads takes less time.

https://www.guru99.com/multithreading-java.html#:~:text=MULTITHREADING%20in%20Java%20is%20a,runs%20parallel%20to%20each%20other.

*/

class A extends Thread
{
	public void show()
	{
		for (int i = 0; i <=10; i++) 
		{
			System.out.println(" A show()... ");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() 
	{
		show();
	}
}

class B extends Thread
{
	public void show() 
	{
		for (int i = 0; i <=10; i++) 
		{
			System.out.println(" B show()... ");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run()
	{
		show();
	}
}

public class CMain
{
	public static void main(String[] args) 
	{
		//both A and B prints simultaneously
		A obj1 =  new A();
		obj1.start();
		//obj1.start();		//error, thread already started.
		
		B obj2 =  new B();
		obj2.start();
		
	}
}