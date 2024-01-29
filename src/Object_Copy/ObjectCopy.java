package Object_Copy;

/*There are 3 types of Coping of Object :
	1] Shallow Copy
	2] Deep Copy
	3] Clone()
*/

public class ObjectCopy 
{
	public static void main(String[] args) 
	{

System.out.println("------------Shallow Copy------------");
		
		CObj obj1 = new CObj();
		obj1.i = 5;
		obj1.j = 6;
		
		CObj obj2 = obj1;		// Here both the reference (obj1 and obj2) pointing to the same object (same memory)
		
		System.out.println("Before Modify :");
		System.out.println("Obj1 : "+ obj1);
		System.out.println("Obj2 : "+ obj2);
		
		obj2.j = 9;
		System.out.println("After Modify :");
		System.out.println("Obj1 : "+ obj1);
		System.out.println("Obj2 : "+ obj2);
		
		
System.out.println("------------Deep Copy------------");
		
		CObj obj3 = new CObj();
		obj3.i = 1;
		obj3.j = 2;
		
		CObj obj4 = new CObj();		// Here both the reference (obj1 and obj2) pointing to the same object (same memory)
		obj4.i = obj3.i;
		obj4.j = obj3.j;
		
		
		System.out.println("Before Modify :");
		System.out.println("obj3 : "+ obj3);
		System.out.println("obj4 : "+ obj4);
		
		obj3.j = 9;
		System.out.println("After Modify :");
		System.out.println("obj3 : "+ obj3);
		System.out.println("obj4 : "+ obj4);
		
		
		
		
		
	}
}


class CObj
{
	int i;
	int j;
	@Override
	public String toString() {
		return "CA [i=" + i + ", j=" + j + "]";
	}
	
	
	
}