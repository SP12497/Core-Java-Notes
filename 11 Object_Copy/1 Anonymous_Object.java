// Anonymous_Object :
public class CMain 
{
	public static void main(String[] args) 
	{
		 A obj = new A();		//Reference Object.
		 obj.i=10;
		 obj.show();

System.out.println("---------------------");
		new A().i =20;
		new A().show();

System.out.println("---------------------");
		
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
		this.i = i;
		System.out.println(this.i);
	}
}