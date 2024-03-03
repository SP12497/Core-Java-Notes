package Collection;

import java.util.Stack;

public class StackExample 
{
	public static void main(String[] args) {
		
		Stack <Integer> s = new Stack<>();
		
		s.push(10);
		s.push(20);
		s.push(30);
		s.push(40);
		s.push(50);
		
		System.out.println("Stack : " + s);	//s.toString
		

		System.out.println("Pop() :" + s.pop());
		System.out.println("Stack : " + s);		

		System.out.println("Peek : " + s.peek());
		
		int pos = s.search(20);
		System.out.println("Search(20) : " + s.search(20););
		
		System.out.println("Stack : " + s);

		
	}

}
