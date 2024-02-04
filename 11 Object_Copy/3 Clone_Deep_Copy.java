//Clone as a Deep Copy
/*
Deep Cloning in Java
	In Java, when the cloning process is done by implementing the Cloneable interface it is called Deep Cloning. 
	In this type of cloning, an exact copy of all the fields of the original object will be created. 
	But in case, the original object has references to other objects as fields then a copy of those objects will also be created by calling the clone() method. 
	This makes the cloned object independent of the original object and any changes made in any of the object won’t be reflected on the other.
*/


class ABC extends Object implements Cloneable		//by default all classes are extends Objects
{													//implements Cloneable : gives permission to class that allows to clone of object(deep copy of obj)
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

public class Main	
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		ABC obj1 = new ABC();
		obj1.i = 5;
		obj1.j = 6;
		
		ABC obj2 = (ABC) obj1.clone();
		
		System.out.println("Before Modify :");
		System.out.println("Obj1 : " + obj1.hashCode() +"  :  " + obj1);
		System.out.println("Obj2 : " + obj2.hashCode() +"  :  " + obj2);

		obj2.j = 9;
		System.out.println("After Modify :");
		System.out.println("Obj1 : " + obj1.hashCode() +"  :  " + obj1);
		System.out.println("Obj2 : " + obj2.hashCode() +"  :  " + obj2);
		
	}
}
