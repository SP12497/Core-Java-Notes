
public class AbstractClassMethods 
{
	public static void main(String[] args) 
	{
	//	MaheshPhone obj = new MaheshPhone();	// we cannot create object of abstract class with abstract class constructor.
	
	System.out.println("__________________OBJ1______________________");	
		MaheshPhone obj1 = new SureshPhone();	//we can access only those methods which are defines in MaheshPhone class.
		obj1.call();
		obj1.play();
		obj1.text();
		obj1.watch();
	//	obj1.classname();	//we can access only those methods which are defines in MaheshPhone class.
		
	System.out.println("_____________________OBJ2______________________");
		SureshPhone obj2 = new SureshPhone();
		obj2.classname();
		obj2.call();
		obj2.play();
		obj2.text();
		obj2.watch();
		
		
	System.out.println("_____________________OBJ3______________________");
		
		JigneshPhone obj3 = new JigneshPhone();
		obj3.watch();
		
	System.out.println("__________________show(OBJ2)______________________");
		//show(obj2);
		show(obj2);
	System.out.println("__________________show(OBJ3)______________________");
		show(obj3);
		
		
	}
	/*
	 * public static void show(SureshPhone obj) { obj.watch(); }
	 */
	
		public static void show(MaheshPhone obj) { obj.watch(); }	//create parameter of parent abstract class, and call ramesh or suresh by passing object of it.
	

}


abstract class MaheshPhone
{
	public void call()
	{
		System.out.println("Mahesh Calling...");
	}
//	public abstract void play()							// Error. We can not define abstract method. we only declare;
//	{
//		System.out.println("Mahesh Playing...");
//	}

	public void play()							
	{
		System.out.println("Mahesh Playing...");
	}
	public abstract void text();
	public abstract void watch();
}

abstract class RameshPhone extends MaheshPhone 		// Here one more abstract method which is not defined , so this class is also abstract.
{
	public void text()
	{
		System.out.println("Ramesh Texting");
	}
}

 class SureshPhone extends RameshPhone 		// Till here all abstract methods are defined.
{
	 public void watch()
	{
		System.out.println("Watching... by Suresh");
	}
	 public void classname()
	 {
		 System.out.println("In SureshPhone class " );
	 }
}
 
 class JigneshPhone extends RameshPhone 		// Till here all abstract methods are defined.
 {
 	 public void watch()
 	{
 		System.out.println("Watching... by Jignesh");
 	}
 }