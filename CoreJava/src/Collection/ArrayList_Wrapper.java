package Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


//ArrayLisst is a Dynamic Array.

public class ArrayList_Wrapper 
{
	public static void main(String[] args)
	{

//-----Integer Array List :
		
//		ArrayList <Integer> al = new ArrayList <Integer> ();
//		
//		al.add(1);
//		al.add(2);	
//		al.add(3);	
//		al.add(4);	
//		al.add(5);	
//		al.add(6);	
//	
//		System.out.println("Array Values by Advance For Loop :");
//		for(Integer ele : al )
//		{
//			System.out.println(" " + ele);
//		}
//		
//	System.out.println("Array Values by Iterator :");
//		
//		Iterator it = al.iterator();		//Iterator is an Interface. and iterator() is a method.
//		
//		while(it.hasNext())		//Check arraylist has any next element.
//		{
//			System.out.println(" "+ it.next());		//display next data
//		}	
//	
		
//------------String Array List :
		
//		ArrayList<String> als = new ArrayList<String>();
//		System.out.println("Array of string is :");
//		als.add("Pune");
//		als.add("Bangalore");
//		als.add("Kharghar");
//		displayArrayList(als);
//		
//		//----------------
//				
//		createArrayList(als);
//		displayArrayList(als);
	
		
//------Object ArrayList
		
		ArrayList<Object> alo = new ArrayList<Object>();
		alo.add("Pune");
		alo.add("Bangalore");
		alo.add("10");
		alo.add("33.33");
		alo.add("C");
		
		System.out.println("Before Modified :");
		displayArrayListObj(alo);
		
		
		System.out.println("After Modified :");
		alo.set(2, "Mumbai");
		displayArrayListObj(alo);
		
		System.out.println("After Removed :");
		alo.remove(2);
		displayArrayListObj(alo);
		
				
	}

	private static void displayArrayListObj(ArrayList<Object> alo) 
	{
		for(Object obj : alo)
		{
			System.out.println("  " + obj);
		}
	}

	private static void displayArrayList(ArrayList<String> als)
	{
		for(String str : als)
		{
			System.out.println("  " + str);
		}
		
	}

	private static void createArrayList(ArrayList<String> als) 
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter size of Array : ");
		int n = sc.nextInt();
		
		String cname;
		System.out.println("Enter " +n+ " String elements :");
		for (int i=0 ; i<n ; i++)
		{
			cname = sc.next();
			als.add(cname);
		}
	}


	
	
}
