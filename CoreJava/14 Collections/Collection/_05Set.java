/*
interface Collection
interface Set extends Collection

abstract class AbstractSet implements Set

class TreeSet extends AbstractSet
class HashSet extends AbstractSet implements Set
class LinkedHashSet extends HashSet

abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>


Set:
	- it cannot contain duplicate elements. It models the mathematical set abstraction.
	- Set Interface contains only methods inherited from Collection 
	- adds the restriction that duplicate elements are prohibited.
Set Implementation:
	- HashSet:
		- Asynchronous/Not Thread-safe
		- Implemented using a hash table. stores elements in a hash table
		- Insertion Order is Not Fixed
		- Allows Only One NULL
		- Provides constant-time performance for basic operations (add, remove, contains)
	- LinkedHashSet:
		- Extends HashSet.
		- stores elements in a hash table
		- Asynchronous/Not Thread-safe
		- Allows null elements.
		- Maintains insertion order of elements using a doubly-linked list.
		- Slower insertion and removal compared to HashSet, but faster iteration
	- TreeSet:
		- Implemented using a red-black tree (self-balancing binary search tree).
		- synchronous/Not Thread-safe
		- Does not allow null elements.
		- Maintains elements in sorted order, either natural ordering or using a custom Comparator provided at construction time.
	- EnumSet
	- CopyOnWriteArraySet

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
*/

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

public class _05Set {
	public static void main(String[] args) {
//		SetImplementations();
//		SetWithCustomObjectWIthDuplicated();
		SetWithCustomObjectWIthoutDuplicated();
	}
	
	static void SetImplementations() {
		Set<Integer> set = new HashSet<>();
//		Set<Integer> set = new LinkedHashSet<>();
//		Set<Integer> set = new TreeSet<>();
		
		System.out.println(set.add(10));	// true
		System.out.println(set.add(10));	// false
		set.add(20);
		set.add(70);
		set.add(50);
		set.add(30);
		System.out.println(set);	// [50, 20, 70, 10, 30]
		
		set.remove(20);
		System.out.println(set.contains(50));	// true
		System.out.println(set.size());	// 4
	}
	
	static void SetWithCustomObjectWIthDuplicated() {
		Set<Book> books = new HashSet<>();
		books.add(new Book(1, "C++", 100));		// true
		books.add(new Book(1, "C++", 100));		// true // new memory got created
		books.add(new Book(2, "Java", 500));
		
		System.out.println(books);	// Storing duplicates.
		// [Book [id=1, name=C++, price=100], Book [id=1, name=C++, price=100], Book [id=2, name=Java, price=500]]
	}
	
	static void SetWithCustomObjectWIthoutDuplicated() {
		Set<BookSet> books = new HashSet<>();	// implement custom equals() and hashcode()
		books.add(new BookSet(1, "C++", 100));		// true
		books.add(new BookSet(1, "C++", 100));		// true // new memory got created
		books.add(new BookSet(2, "Java", 500));
		
		System.out.println(books);	// [Book [id=1, name=C++, price=100], Book [id=2, name=Java, price=500]]
	}
}

class BookSet {
	int id;
	String name;
	int price;
	BookSet(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);	// do hashing based on ID field.
	}
	
	@Override		// Define our own checks for equals
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o ==null || getClass() != o.getClass()) return false;
		BookSet b = (BookSet) o;
		return id == b.id;
	}
}

class Book {
	int id;
	String name;
	int price;
	Book(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
