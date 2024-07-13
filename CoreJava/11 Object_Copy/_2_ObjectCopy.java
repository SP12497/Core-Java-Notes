/* 
There are 2 ways of copying an object:
   1] Shallow Copy: Copies the object reference.
   2] Deep Copy: Copies the values of the object.
   3] Clone(): By implementing the Cloneable interface, we can create a shallow or deep copy.

 Shallow Copy:
 - Copies the values of the references in the class.
 - The copied object shares the same references as the original.
 - Changes to the original object's referenced objects will reflect in the shallow copy.

 Deep Copy:
 - Copies the actual values of the object's fields.
 - The copied object does not share references with the original.
 - Changes to the original object's referenced objects will not reflect in the deep copy.

 In summary:
 - A shallow copy has the same reference (==) as the original.
 - A deep copy has equivalent content (.equals()) but different references.

 Example:
 - If a change is made to the value of a shallow copied reference, the copy reflects that change.
 - If a change is made to the value of a deeply copied reference, the copy does not reflect that change.
*/

class CObj
{
	int i;
	int j;
	@Override
	public String toString() {
		return "CObj [i=" + i + ", j=" + j + "]";
	}
}


public class _2_ObjectCopy
{
	public static void main(String[] args) 
	{

System.out.println("------------Shallow Copy------------");
		
		CObj obj1 = new CObj();
		obj1.i = 5;
		obj1.j = 6;
		
		CObj obj2 = new CObj();	// or CObj obj2 = Obj1;
		obj2 = obj1;	// Here both the reference (obj1 and obj2) pointing to the same object (same memory) is called as shallow copy means another name of obj1 is obj2 //copies reference
		//CObj obj2 = obj1;
		
		System.out.println("Before Modify :");
		System.out.println("Obj1 : " + obj1.hashCode() +"  :  " + obj1);	//5 6
		System.out.println("Obj2 : " + obj2.hashCode() +"  :  " + obj2);	//5 6

		obj2.j = 9;
		System.out.println("After Modify :");
		System.out.println("Obj1 : " + obj1.hashCode() +"  :  " + obj1);	//5 9
		System.out.println("Obj2 : " + obj2.hashCode() +"  :  " + obj2);	//5 9


System.out.println("\n-------------Deep Copy------------");
		
		CObj obj3 = new CObj();
		obj3.i = 1;
		obj3.j = 2;

		CObj obj4 = new CObj();		// Here both the reference (obj3 and obj4) pointing to the different object (different memory)
		obj4.i = obj3.i;
		obj4.j = obj3.j;
		
		
		System.out.println("Before Modify :");
		System.out.println("obj3 : " + obj3.hashCode() +"  :  " + obj3);	//1 2
		System.out.println("obj4 : " + obj4.hashCode() +"  :  " + obj4);	//1 2
		
		obj3.j = 9;
		System.out.println("After Modify :");
		System.out.println("obj3 : "+ obj3.hashCode() +"  :  " + obj3);		//1 9
		System.out.println("obj4 : "+ obj4.hashCode() +"  :  " + obj4);		//1 2
	}
}