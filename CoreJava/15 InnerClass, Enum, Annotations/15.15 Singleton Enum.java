//Singleton Design Pattern : Enum Initialization 

enum Abc	//special type of class
{
	INSTANCE;	//OBJECT
	
	int i;
	public void show()
	{
		System.out.println("i = " + i);
	}
}

public class SingletonClass
{
	public static void main(String[] args) 
	{
		Abc obj1 = Abc.INSTANCE;
		obj1.i= 5;
		obj1.show();	//5
		
		
		Abc obj2 = Abc.INSTANCE;
		obj2.i = 10;
		
		obj1.show();	//10
		obj1.show();	//10
	}
}