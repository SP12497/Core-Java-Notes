 package Collection;

import java.util.LinkedList;

public class LinkedListExample 
{
	public static void main(String[] args) 
	{
	//Queue is also same.
		LinkedList <Integer> ll = new LinkedList();
		
		ll.add(10);
		ll.add(20);
		ll.add(30);
		ll.add(40);
		ll.add(50);
		
		System.out.println("LinkedList : " + ll);
		
		ll.addFirst(5);
		ll.addLast(60);
		System.out.println("LinkedList : " + ll);
		
		//ll.add(index, element);
		ll.add(3, 100);
		System.out.println("LinkedList : " + ll);
		
		//ll.set(index, element)
		ll.set(2, 99);
		System.out.println("LinkedList : " + ll);
		
		

	}
}
