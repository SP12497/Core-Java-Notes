/*There are 3 types of Coping of Object :
	1] Shallow Copy : copy the obj reference
	2] Deep Copy	: copy the value of obj	
	3] Clone()	: by implementing Cloneable Interface : we can create shallow or deep copy.
*/

/*
 A shallow copy just copies the values of the references in the class. 
 A deep copy copies the values. given:
 
 In this case the shallow copy has the same reference (==) and 
 the deep copy only has an equivalent reference (.equals()).

 If a change is made to the value of a shallow copied reference, then the copy reflects that change because it shares the same reference. 
 If a change is made to the value of a deeply copied reference, then the copy does not reflect that change because it does not share the same reference. 
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


public class CMain
{
	public static void main(String[] args) 
	{

System.out.println("------------Shallow Copy------------");
		
		CObj obj1 = new CObj();
		obj1.i = 5;
		obj1.j = 6;
		
		//CObj obj2 = Obj1; 
		
		CObj obj2 = new CObj();
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