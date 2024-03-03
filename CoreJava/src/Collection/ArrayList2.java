package Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class ArrayList2
{

	public static void main(String[] args) 
{

	ArrayList<Person> al = new ArrayList<>();
	
	
	addPersonOject(al);
	dispPersonObject(al);

	removeObject(al);
	
	dispPersonObject(al);
	
	updateObject(al);
	
	dispPersonObject(al);
	
	SortingObject(al);
	
	dispPersonObject(al);
	
	
	

	
//	Iterator it = al.iterator();
//	
//	while(it.hasNext())
//		{
//		
//		Person obj = (Person) it.next();
//		obj.disp();
	
			
		//System.out.println(it.next());
	
	
}
	
	
	private static void addPersonOject(ArrayList<Person> al)
	{
		Person p1 = new Person(1001,"shan");
		Person p2 = new Person(1008,"raj");
		Person p3 = new Person(1003,"kumar");
		Person p4 = new Person(1009,"siju");
		
		al.add(p1);
		al.add(p2);
		al.add(p3);
		al.add(p4);
		
		System.out.println("Added Successsfully");
		
	}
	
	
	private static void dispPersonObject(ArrayList<Person> al)
	
	{
		for(Person obj :al)
		{
			System.out.println(obj);
			
			obj.disp();
		}
		
		
	}
	

	private static void removeObject(ArrayList<Person> al) 
{

	Scanner s = new Scanner(System.in);
	int pid;
	System.out.println("enter pid to remove");
	
	pid = s.nextInt();
	
	int pos = 0;
	for(Person obj :al)
	{
		if(obj.id == pid)
		{
			
			pos = al.indexOf(obj);
			
		}
		
		
	}
	
	al.remove(pos);
	System.out.println("given id object is removed");
	
}

	private static void updateObject(ArrayList<Person> al)
{

	Scanner s = new Scanner(System.in);
	int pid;
	System.out.println("  Enter selected pid to update name ");
	
	
	
	pid = s.nextInt();
	
	System.out.println("  Enter Name :");
	String cname = s.next();
	
	int pos =0;
	for(Person obj :al)
	{
		if(obj.id == pid)
		{
			
			obj.name = cname;
			
		}
		
		
	}
	
}

	private static void SortingObject(ArrayList<Person> al)
{

	Sortbyid s = new Sortbyid();
	
	Collections.sort(al,s);
	
	System.out.println("object after sorting");
}


}

class Sortbyid implements Comparator<Person>
{

	@Override
	public int compare(Person p1, Person p2) {
		if(p1.id>p2.id)
			
		return 1;
		else
			return -1;
	}
	
}