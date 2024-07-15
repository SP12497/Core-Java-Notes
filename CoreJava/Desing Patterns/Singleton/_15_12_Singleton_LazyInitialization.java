//Singleton Design Pattern : Lazy Initialization -> Object created when required

//but not thread safe 

class ABC 
{
	static ABC obj ;//  Declaration only	 
	private ABC()   //private... No default constructor to create object
	{
		System.out.println("Instance Created");
	}
	

	static public ABC getInstance()
	{
		if(obj==null) 
		{
			obj = new ABC();	//Instance Created	//Lazy Initialization
		}
		return obj;	//return only one object every time.
	}
}

public class _15_12_Singleton_LazyInitialization
{
	public static void main(String[] args) 
	{
		//ABC obj = new ABC(); //Constructor is private so not allowed		
		
		ABC obj1 = ABC.getInstance();
		ABC obj2 = ABC.getInstance();
		
	}
}