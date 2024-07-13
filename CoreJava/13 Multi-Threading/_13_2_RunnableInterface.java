
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

public class _13_2_RunnableInterface
{
	public static void main(String[] args) 
	{
		A obj1 =  new A();
		obj1.start();		//Thread class
		
		B obj2 =  new B();
		Thread t2 = new Thread(obj2);	// require to convert runnable into Thread. Thread have start method.
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
