package Object_Copy;

public class Anonymous_Object 
{
	public static void main(String[] args) 
	{
//		A obj = new A();		//Reference Object.
//		 obj.i=10;
//		 obj.show();
	
//---------------------
//		new A().i =1;
//		new A().show();
		
//------------------------
		
		new A().show(11);		//Anonymous Object.
		 
		
	}

}


class A
{
	int i;
	
	public void show()
	{
		System.out.println(i);
	}
	
	public void show(int i)
	{
		System.out.println(i);
	}
	
	
}