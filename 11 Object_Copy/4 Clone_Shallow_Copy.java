//Clone as a Shallow Copy

/*
Shallow Cloning
In Java, when the cloning process is done by invoking the clone() method it is called Shallow Cloning. 
It is the default cloning process in Java where a shallow copy of the original object will be created with exact field. 

In case the original object has references to some other objects as fields, then only the references of that object will be cloned instead of new object creation. 

In other words, if you change the value of the cloned objects then it will be reflected in the original as well. 
Thus, shallow cloning is dependent on the original object.

*/

class ABC			//Shallow Copy of class ABC
{													//implements Cloneable : gives permission to class that allows to clone of object(deep copy of obj)
	int i;
	int j;
	
	@Override
	public String toString() {
		return "ABC [i=" + i + ", j=" + j + "]";
	}	
}

class AA implements Cloneable		//Deep copy of Class AA but shallow of ABC
{
	ABC obj ;

	AA(ABC obj) {
		super();
		this.obj = obj;
	}
	
	@Override
	public String toString() {
		return "AA [obj=" + obj + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}

//Clone_ObjectCopyMain : 
public class CMain	 
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		ABC abc = new ABC();			//abc address : 100
		abc.i = 5;
		abc.j = 6;
		
		AA obj1 = new AA(abc);			//obj1 address : 200 //abc address : 100


		AA obj2 = (AA) obj1.clone();	//obj2 address : 300 //abc address : 100

		System.out.println("Before Modify :");
		System.out.println("Obj1 : " + obj1.hashCode() +"  :  " + obj1);
		System.out.println("Obj2 : " + obj2.hashCode() +"  :  " + obj2);

		abc.j = 9;
		
		System.out.println("After Modify :");
		System.out.println("Obj1 : " + obj1.hashCode() +"  :  " + obj1);	//j = 9;
		System.out.println("Obj2 : " + obj2.hashCode() +"  :  " + obj2);	//j = 9;
		
	}
}

