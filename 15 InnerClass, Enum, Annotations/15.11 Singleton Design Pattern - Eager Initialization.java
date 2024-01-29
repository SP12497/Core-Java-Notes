//Singleton Design Pattern : Eager Initialization  -> object created before required, memory wastage

class ABC 
{
	static ABC obj =  new ABC(); //static	//Eager Initialization	: declare and created

	private ABC()   //private... No default constructor to create object
	{
		System.out.println("Instance Created");
	}
	
	static public ABC getInstance()
	{
		return obj;	//return only one/same object every time.
	}
}


public class SingletonClass
{
	public static void main(String[] args) 
	{
		//ABC obj = new ABC();		//Constructor is private so not allowed		

		ABC obj1 = ABC.getInstance();
		ABC obj2 = ABC.getInstance();
		
		System.out.println("Obj1 : " + obj1.hashCode());
		System.out.println("Obj2 : " + obj2.hashCode());
	}
}