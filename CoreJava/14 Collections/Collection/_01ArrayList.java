import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/*
interface Collection		// add(<E>)
interface List extends Collection	// List have: get(), add(index, Object), set(), indexOf()
class ArrayList implements List
class Vector implements List
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
	al4.toArray(new Integer[al4.size()]) : Integer[] 
List:
	get(index) : E
	set(index, <E> value) : E	// returns 22 // 22 => 33	set(2, 33)
	indexOf(<E> value) : int

Difference:
	Vector:
		- synchronized	/ Threas Safe
		- lower performance / low speed of execution
		- Increase the capacity by 100% when size exceeded
	ArrayList:
		- non-synchronized	/ Not Thread Safe 
		- better performance / high speed of execution
		- Increase the capacity by 50% when size exceeded	/ int newCapacity = (oldCapacity * 3)/2 + 1; 
	
Create quick List:
	List<Integer> ls = Arrays.asList(11,22,33);
	
*/
public class _01ArrayList {
	public static void main(String[] args) {
		int size = 5;
// 1. Declaration
		Collection<String> cl = new ArrayList();
		List<String> ls = new ArrayList<>();
		List<String> vector = new Vector<>();
		ArrayList anytype = new ArrayList<>(); // we can store any value. // Default constructor without parameterized
		ArrayList<String> al1 = new ArrayList<>(); // Default constructor
		ArrayList<String> al2 = new ArrayList<>(size); // parameterized constructor 

//	2. Declaration + Initialization :
		List<Integer> ls1 = Arrays.asList(new Integer[] { 11, 22, 33 }); // int not support
		List<Integer> ls2 = Arrays.asList(11, 22, 33); // int not support
		ArrayList<Integer> al3 = new ArrayList<>(ls1);
		ArrayList<Integer> al4 = new ArrayList<>(Arrays.asList(11, 22, 33)); // parameterized constructor by List

		ArrayList<Boolean> bl = new ArrayList<Boolean>() {
			{	// Instance block
				add(true);
				add(false);
				add(true);
			}
		};

//	3. Insertion:
		// Collection
		cl.add("SAGAR");
		cl.add("Patil"); // add end of the list
		cl.add("Tejas"); // add at first index
		cl.add("SAGAR"); // duplicates are allowed
		cl.addAll(Arrays.asList("AA", "CC", "BB"));			// copy
		// ls1.add(2, 30); // Collection don't have this method.
		

		// List
		 // ls1.add(1); // java.lang.UnsupportedOperationException, bcoz list don't have implementation.
		ls.add("Sagar");
		
		// anytype:
		anytype.add(Integer.valueOf(1));
		anytype.add(11);
		anytype.add(String.valueOf("Sagar"));
		anytype.add(Character.valueOf((char) 65));
		anytype.add("Patil");
		anytype.add(true);
		anytype.add(null);
		anytype.add(3, null);
		
// 	4. isEmpty:
		System.out.println("isEmpty:" + cl.isEmpty());	// false
		System.out.println("isEmpty:" + al2.isEmpty());	// true

//  5. Update:
		// cl.set(1, "Hi");		// Collection don't have set()
		System.out.println("set: " + ls1.set(1, 888)); // Output> set: 22 // 22=>888
		// ls1.set(50, 123);	// List: java.lang.ArrayIndexOutOfBoundsException

//  6. Printing:
		System.out.println("anytype:" + anytype);
		// For Each Loop:
		for (String string : cl) {
			System.out.println(string);
		}
		// stream api:
		System.out.println("cl.forEach()");
		cl.forEach(System.out::println);
		
		// Iterator:
		System.out.println("Iterator:");
		Iterator<String> it= cl.iterator();
		while(it.hasNext())
			System.out.print(it.next() + " ");
		
//  7. Deep copy
		//List<Integer> ls5 = Arrays.asList(al4);	// Type mismatch: cannot convert from List<ArrayList<Integer>> to List<Integer>
		List<Integer> ls5 = new ArrayList<>(ls2);	// Deep Copy: way 1
		ls5.addAll(ls2);	// Deep Copy: way 2
		ls5.add(5);
		ls5.set(2, 66);
		System.out.println("removeAll"+ ls5.removeAll(ls2)); // Remove all occurrence of list elements from ls5;
		System.out.println("ls5"+ ls5);
		
//  8. Remove:
		cl.remove("Sagar");		// false, case sensitive
		cl.remove("SAGAR");	// first occurrence of SAGAR will get remove
		cl.removeAll(Arrays.asList("SAGAR"));	// remove all SAGAR occurrence.
		System.out.println(cl.remove(String.valueOf("Patil"))); 	// returns true
		System.out.println(cl.remove("absent"));					// returns false, bcoz not present
		System.out.println(cl);
		
		
		System.out.println("anytype after removeAll:" + anytype);
		
// 9. get value by index:
		// cl.get(1);		// Collection class don't have get method.
		System.out.println("List get(1):" + ls1.get(1));
		System.out.println("Arraylist get(1):" + al4.get(2));
		// al4.get(50);	// java.lang.IndexOutOfBoundsException // List have java.lang.ArrayIndexOutOfBoundsException if we got out of array.

// 10. get index by value:
		System.out.println("indexOf 11: "+ls1.indexOf(11));	// 0
		System.out.println("indexOf 33: "+ls1.indexOf(33));		// 2
		System.out.println("indexOf invalid"+ls1.indexOf(2222));// -1
		
// 11. check value:
		System.out.println("contains: " + cl.contains("AA"));		// true
		System.out.println("contains: " + cl.contains("Absent"));	// false


//  12. Iterate:
		for(int i=0; i<ls1.size(); i++) {
			System.out.print(ls1.get(i) + " ");
		}
		
//  13. Covert ArrayList => array:
		
		Integer num [] = al4.toArray(new Integer[al4.size()]);

// 14. Clear array:
		al4.clear();
	}
}
