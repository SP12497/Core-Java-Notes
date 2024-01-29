// 1) creating our thread using Runnable Interface //

class MyThread implements Runnable 
{
	
	public void run()  //write the task to be performed in run() method
	{
		for(int i=1;i<=10;i++){

			System.out.println("value of i is "+i);
			try{
				Thread.sleep(1000); //sleep of 1sec while printing numbers
			}catch(Exception e){
			}
		}
	}

	public static void main(String[] args) {
		//create object of MyThread class
		MyThread t1=new MyThread();
		Thread thread=new Thread(t1); //passing t1 object as a reference to thread class
		
		thread.start();
		
	}
}
-------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------

//2)  creating our thread using Thread class
class MyAnotherThread extends Thread
{
	public void run()
	{
		//task for thread
		for(int i=10;i>=1;i--)
		{
 
			System.out.println("another thread = "+i);

			try
			{
				Thread.sleep(2000);
			}catch(Exception e)
			{

			}
		}
	}
	
	public static void main(String[] args) {

	//object of AnotherThread
		MyAnotherThread t2=new MyAnotherThread();
		t2.start(); //directly using start() method with t2 object bcoz 
		           //MyAnotherThread class is extending thread class and not implementing runnable interface
	}
}

---------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------

// 3) Various Multithreading operations

class UserThread extends Thread{
	public void run()
	{
		//task for thread...
		System.out.println("this is user defined thread.");
	}
}
class ThreadOp
{
	public static void main(String[] args) {
		System.out.println("program started..");	
		int x=56+34;
		System.out.println("sum is "+x);
		//Thread...
		Thread t=Thread.currentThread();
		String tname=t.getName();
		System.out.println("current running thread is "+tname);
		//setName
		t.setName("MyMain");
		System.out.println(t.getName());
		try{
			Thread.sleep(5000);
		}catch(Exception e){
		};
		System.out.println(t.getId());
		//going to start user defined thread.
		System.out.println("program ended..");	
		UserThread thread=new UserThread();
		thread.start();

	}
}

//  4) Synchronizing two threads(producer & consumer problem)


class Company   //company class
{
	int n;
	boolean f=false;
	// f=false: chance: producer
	//f=true: chance :consumer
	synchronized public void produce_item(int n)throws Exception
	{
		if(f)
		{
			wait();
		}
		this.n=n;
		System.out.println("Produced : "+this.n);
		f=true;
		notify();
	}

	synchronized public int consume_item()throws Exception
	{
		if(!f)
		{
			wait();
		}
		System.out.println("Consumed : "+this.n);
		f=false;
		notify();
		return this.n;
	}
}

class Producer extends Thread  //producer class
{

	Company c;
	Producer(Company c)
	{
		this.c=c;
	}
	public void run()
	{ 
		int i=1;
		while(true)
		{
			this.c.produce_item(i);
			try{Thread.sleep(1000);}catch(Exception e){}
			i++;
		}
	}
}

class Consumer extends Thread  //consumer class
{
	Company c;
	Consumer(Company c)
	{
		this.c=c;
	}

	public void run()
	{
		while(true)
		{
			this.c.consume_item();
			try{Thread.sleep(2000);}catch(Exception e){}
		}
	}
}

class Main     //main class to test program
{
	public static void main(String[] args) {		
		Company comp=new Company();
		Producer p=new Producer(comp);
		Consumer c=new Consumer(comp);
		p.start();
		c.start();

	}
}