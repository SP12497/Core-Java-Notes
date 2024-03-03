package Object_Copy;

public class Clone_ObjectCopy	 
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		ABC obj1 = new ABC();
		obj1.i = 5;
		obj1.j = 6;
		
		ABC obj2 = (ABC) obj1.clone();	
		
		System.out.println("Before Modify :");
		System.out.println("Obj1 : "+ obj1);
		System.out.println("Obj2 : "+ obj2);
		
		obj2.j = 9;
		System.out.println("After Modify :");
		System.out.println("Obj1 : "+ obj1);
		System.out.println("Obj2 : "+ obj2);
		
	}

}

class ABC extends Object implements Cloneable		//by default all classes are extends Objects
{													//implements Cloneable : gives permission to class that allows to clone of object
	int i;
	int j;
	
	@Override
	public String toString() {
		return "ABC [i=" + i + ", j=" + j + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	
}
