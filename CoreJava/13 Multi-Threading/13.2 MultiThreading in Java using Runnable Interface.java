/*
What is Multithreading in Java?
 MULTITHREADING in Java is a process of executing two or more threads simultaneously to maximum utilization of CPU. 
 Multithreaded applications execute two or more threads run concurrently. 
 Hence, it is also known as Concurrency in Java. 

 Each thread runs parallel to each other. 

 Mulitple threads don't allocate separate memory area, hence they save memory. Also, context switching between threads takes less time.

https://www.guru99.com/multithreading-java.html#:~:text=MULTITHREADING%20in%20Java%20is%20a,runs%20parallel%20to%20each%20other.

class Thread has start method
interface Runnable has run abstract method
*/


// Thread class or Runnable Interface :

//1. Extending: class Thread implements Runnable
class A extends Thread
{
	@Override
	public void run()
	{
		show();			//calling
	}

	private void show() 
	{
		for (int i = 0; i <=5; i++) 
		{
			System.out.println(" A run()... ");
			try {Thread.sleep(1000); } catch (InterruptedException e) {}
		}
	}
}

//2. Implementing Runnable Interface
class B implements Runnable
{
	@Override
	public void run()
	{
		for (int i = 0; i <=5; i++) 
		{
			System.out.println(" B run()... ");
			try {Thread.sleep(1000); } catch (InterruptedException e) {}
		}
	}
}

public class CMain
{
	public static void main(String[] args) 
	{
		
		A obj1 =  new A();
		obj1.start();		//Thread class
		
		B obj2 =  new B();
		Thread t2 = new Thread(obj2);	//require to convert runnable into Thread , Thread have start method.
		t2.start();		
		
	//3. By Implementing Runnable Interface
		Runnable r = new Runnable() 		//Inner Class obj1
		{
			public void run()
			{
				for (int i = 0; i <5; i++) 
				{
					System.out.println(" Runnable run()... ");
					try {Thread.sleep(1000); } catch (InterruptedException e) {}
				}
			}
		};
		Thread t3 = new Thread(r);
		t3.start();

	//4. By using Anonymous Class
		Thread t4 = new Thread(new Runnable() 
		{
			public void run()
			{
				for (int i = 0; i <=5; i++) 
				{
					System.out.println(" Anonymous Class run()... ");
					try {Thread.sleep(1000); } catch (InterruptedException e) {}
				}
			}
		});
		t4.start();
		
		//5. By using Lambda Expression
				Thread t5 = new Thread( () ->
					{
						for (int i = 0; i <=5; i++) 
						{
							System.out.println(" Lambda Expression run()... ");
							try {Thread.sleep(1000); } catch (InterruptedException e) {}
						}
					}
				);
				t5.start();

		//6. Annonymous Object using lambda expression :
				new Thread( () ->
				{
					for (int i = 0; i <=5; i++) 
					{
						System.out.println(" Annonymous Object using Lambda Expression run()... ");
						try {Thread.sleep(1000); } catch (InterruptedException e) {}
					}
				}
			).start();
	}
}

