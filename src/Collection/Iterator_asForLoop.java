package Collection;

import java.util.ArrayList;
import java.util.Iterator;

public class Iterator_asForLoop 
{
	public static void main(String[] args) 
	{
		ArrayList <Person> al = new ArrayList<Person>();
		
		Person p1 = new Person(101, "Sagar");
		Person p2 = new Person(102, "Nilesh");
		Person p3 = new Person(103, "Shubham");
		Person p4 = new Person(104, "Yash");
		
		//we can add() , remove(), search(), sort()
		al.add(p1);
		al.add(p2);
		al.add(p3);
		al.add(p4);
		
		System.out.println("----by Iterator----");
		Iterator it = al.iterator();
		while(it.hasNext())
		{
				System.out.println(it.next());		//this required to string method.
		//----OR----		
//
//				Person obj = (Person) it.next();			//iterator is Super class and Person is Sub class. 
//															//so we have to typecast it from higher to lower.
//				obj.disp();							//this required disp();
		
		}
		
		System.out.println("----by Advance For Loop----");
		for(Person obj : al)
		{
			System.out.println(obj);
		}
		
		
		
	
		
		
		
		
		
	}
	

}


