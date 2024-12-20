import java.util.Collection;
import java.util.List;
import java.util.Stack;

/*
interface Collection
interface List extends Collection
class Vector implements List
class Stack extends Vector

Stack:
	- is an order list in which insertion and deletion are done at one end,
	  where the end is generally called the top.
	- The element inserted at last, is the first one to get removed,
	  hence it is called as LIFO (List In First Out) Data Structure.
Stack Implementation:
	- Fixed Size Array
	- Dynamic Array
	- Linked List
Operations:
	push(value)
	pop()
	peek()	// last/top value
---------------
Collection:
	size(): int
	add(<E>) : boolean
	addAll(List) : boolean
	remove(<E>) : boolean
	removeAll(List) : boolean
	clear() : void
	forEach(Consumer obj) : void
	iterator() : Iterator<E>  //  it.hasNext() : boolean, it.next() : E
	contains(<E> value) : boolean
	al4.toArray(new Integer[al4.size()]) : Integer[] 
List:
	get(index) : E
	set(index, <E> value) : E	// returns 22 // 22 => 33	set(2, 33)
	indexOf(<E> value) : int
Stack:
	push(E): E
	pop(): E
	peek(): E
	empty(): boolean
	isEmpty()
	search(E): E // returns index start from 1
	
*/
public class _03Stack {
	public static void main(String[] args) {
		Stack<String> books = new Stack<>();
		List<String> books1 = new Stack<>();
		Collection<String> books2 = new Stack<>();

		System.out.println(books.empty()); // true // Stack
		System.out.println(books.isEmpty()); // true // from Vector // Collection also have

		books.push("Java"); // Stack
		books.push("DSA"); // returns "DSA"
		books.add("Python"); // return boolean: true // Vector, Collection

		System.out.println(books);
		System.out.println(books.peek()); // Python

		System.out.println(books.pop()); // Python
		System.out.println(books.search("Java")); // 2 // index start from 1

		System.out.println(books.size()); // 2
		while (!books.isEmpty())
			System.out.print(books.pop() + " ");

	}

}
