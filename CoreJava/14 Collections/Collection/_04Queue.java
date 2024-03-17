import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/*
interface Collection
interface Queue extends Collection
interface Deque extends Queue

class ArrayDeque implements Deque		// Double ended queue
class LinkedList implements Deque		// Double ended queue

abstract class AbstractQueue implements Queue
class PriorityQueue extends AbstractQueue

Queue:
	- just like a real life queue.
	- is a sequence of objects waiting to be server in the sequential order
	  starting from the beginning of the queue
	- The element inserted at last, is the last one to get removed,
	  hence it is called as FIFO/LILO (First In First Out) Data Structure.
	- Insertion : rear end
	  Deletion	: front end of queue
Queue Implementation:
	- Fixed Size array (Circular Array)
	- Dynamic Array
	- Queue using a Linked List
Operations:
	Enqueue	:	offer()	// add end of queue
	Dequeue	:	poll()	// remove from front side
	PEEK 	:  	peek()	// first element on Front end
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
Queue:
  -> Two Implementations of Each Method:
	- Throw Exception: For explicit error handling.
	- Special Value: either true, null or false, for smoother control flow without exceptions.
	-> Throws Exception || Special Value
	- Insert:
		add(e): boolean	||	offer(e): boolean
	- Remove:
		remove(e): e	||	poll(): e
	- Examine:
		element():e	||	peek():e
Deque: 
	-> Two Implementations of Each Method:
		- Throw Exception: For explicit error handling.
		- Special Value: either true, null or false, for smoother control flow without exceptions.
	-> Throws Exception || Special Value
	- Insert:
		addFirst(E element)		|| offerFirst(E element)
		addLast(E element) :	|| offerLast(e)
	- Remove:
		removeFirst(): E 	|| 	pollFirst():E
		removeLast():E		||	pollLast(): E
		push()
		pop()
	- Examine:
		getFirst(): void	||	peekFirst()		peek() 
		getLast()	||	peekLast()
		peek()	// head element // peekFirst() return head or null if list is empty
		size()
	- Fetch and Remove:
		poll()	// fetch first element and remove
		pollFirst()	// fetch first element and remove or null if list is empty
		pollLast(
	- Update:
		set(int index, E element)
	- toString

Deque:
	- is a linear data structure that allows 
	  insertion and deletion of elements on both ends.
	 - Deque is an acronym for double-ended queue.
*/
public class _04Queue {
	public static void main(String[] args) {
//		singleEndedQueue();
//		doubleEndedQueue();
		priorityQ();
		
	}
	
	static void singleEndedQueue() {
		Queue<Integer>	q = new LinkedList<>(); 	// Single ended Queue.	// FIFO
		Deque<Integer>	dq = new LinkedList<>(); 	// D ended Queue.		
		LinkedList<Integer>	ll = new LinkedList<>(); // Double ended Queue
		// insert:
				q.add(11);		// true
				q.offer(33);	// true
				q.offer(22);
				q.offer(33);	// true
				System.out.println(q);	// [11, 33, 22, 33]
				
				q.remove();		// 11
				q.poll();		// 33
				
				
		// Examine:	[22, 33]
				System.out.println(q.peek());		// 22
				System.out.println(q.element());	// 22
				
				while(!q.isEmpty()) 
					System.out.print(q.poll() + " ");
	}
	
	static void doubleEndedQueue() {
		System.out.println("---ArrayDeque---");
		// insertion and deletion of elements on both ends.
		ArrayDeque<Integer> dq = new ArrayDeque<>();
// insert:
		dq.offer(11);	// same: offerLast/addLast/add
		dq.offerFirst(22);
		dq.offerLast(33);
		dq.addFirst(44);
		dq.offer(55);
		dq.add(66);
		System.out.println(dq);

// remove:
		dq.poll();	// delete from start: remove() pollFirst()
		dq.pollLast();	// deleme from last.  
		System.out.println(dq);
		
// Peek:
		System.out.println(dq.peek());	// 22
		System.out.println(dq.peekFirst());	// 22
		System.out.println(dq.peekLast());	// 55
		
	}
	
	static void priorityQ() {
	/* Priority Queue:
	 - it is a special type of queue in which each element is associated with a priority value.
	   And, elements are served on the basis of their priority.
	   That is, higher priority elements are removed first.
	 - eg. senior citizen gets the priority in the queue. So condition is "age".
	 */
		Queue<Integer> pq = new PriorityQueue<>();	// lowest element will get priority
//		Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());	// highest element will get priority
		
		pq.add(6);
		pq.offer(1);
		pq.offer(0);
		pq.offer(7);
		pq.offer(3);
		
		System.out.println(pq);	// [0, 3, 1, 7, 6]
		System.out.println("peek: " + pq.peek());	// 0
		
		System.out.println(pq.poll());	// 0	// remove from front end and highest priority element will come on the first position.
		System.out.println(pq);			// [1, 3, 6, 7]
		System.out.println(pq.poll());	// 1
		System.out.println(pq.poll());	// 3
	}
}
