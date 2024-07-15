import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
interface Collection
interface List extends Collection
class LinkedList implements Serializable, Clonable, Iterable<E>, Deque<E>, Queue<E>

LinkedList:
	- is a linear data structure, in which elements are represented as
	  objects and stored in non-contiguous memory locations.
	- don't need size
	- Types:
		- Singly LL		:	Node{data, nextNodeAddress}
		- Doubly LL		:	Node{previousNodeAddress, data, nextNodeAddress}
		- Circular LL
---------------
Collection:
    size(): int
    isEmpty(): boolean
	add(<E>) : boolean
	addAll(List) : boolean
	remove(<E>) : boolean
	removeAll(List) : boolean
	clear() : void
	forEach(Consumer obj) : void
	iterator() : Iterator<E>  //  it.hasNext() : boolean, it.next() : E
	contains(<E> value) : boolean
	toArray(new Integer[size]) : Integer[] 
List:
	get(index) : E
	set(index, <E> value) : E	// returns 22 // 22 => 33	set(2, 33)
	indexOf(<E> value) : int

Deque: 
	-> Two Implementations of Each Method:
		- Throw Exception: For explicit error handling.
		- Special Value: either true, null or false, for smoother control flow without exceptions.
	-> Throws Exception || Special Value
	- Insert:
		|	Exception					|| Boolean						|
		|push()	add()					|| offer()						|
		|void addFirst(E element)		|| boolean offerFirst(E element)|
		|addLast(E element) : Exception	|| offerLast(e)					|
	- Remove:
		pop()				||	poll()
		removeFirst(): E 	|| 	pollFirst():E
		removeLast():E		||	pollLast(): E
	- Examine:
		getFirst(): void	||	peekFirst()		peek() 
		getLast()			||	peekLast()
		peek()	// head element // peekFirst() return head or null if list is empty
		size()
	- Fetch and Remove:
		poll()	// fetch first element and remove
		pollFirst()	// fetch first element and remove or null if list is empty
		pollLast(
	- Update:
		set(int index, E element)
	- toString
	
	
Create quick List:
	List<Integer> ls = Arrays.asList(11,22,33);
	
*/
public class _02LinkedList {
	public static void main(String[] args) {
		Collection<Integer> ll2 = new LinkedList<>();
		List<Integer> ll1 = new LinkedList<>();
		Deque<Integer> ll3 = new LinkedList<>();
		LinkedList<Integer> ll = new LinkedList<>();    // Java Collection implements Double LL.

		ll.add(11);
		ll.addFirst(22);		// void
		ll.offerFirst(33);	// true
		ll.offerLast(66);
		System.out.println(ll);	// [33, 22, 11, 66]
		
		ll.removeFirst();		// returns 33
		ll.pollLast();			// returns 66
		ll.remove();			// remove first
		
		
		
		System.out.println(ll);
		System.out.println(ll.peek());
		System.out.println(ll.indexOf(11));	// 1
			
	}
	

}
