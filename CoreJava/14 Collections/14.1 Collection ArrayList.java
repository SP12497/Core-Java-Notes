// Interface : Collection (Only for array) and List(Only for array), Set(only for), Map (only for map)
// Class : Collections, ArrayList , Vector 

// List : default size is 10.

// ArrayList : 			int newCapacity = (oldCapacity * 3)/2 + 1;  
//	Introduced in 1.2 // Dynamic Array
//	Increase the capacity by 50% when size exceeded
//	ASynchronised / Not Thread Safe //Fast

//Vector :
//	Introduced in 1.0 //Dynamic Array
//	Increase the capacity by 100% when size exceeded
//	Synchronised /Threas Safe  //Slow

import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

class CMain {
	public static void main(String[] args) {
		// There are 3 types of initialization of arraylist
		// 1. Normal way
		// Collection c = new ArrayList();
		Collection<Integer> c = new ArrayList<>(); // <Generics>
		c.add(11);
		c.add(11);
		c.add(22);
		c.add(33);
		// c.add(2, 88); // error, not supported in Collection, supported in List
		// c.add("Sagar");
		// c.add(3.4);
		c.remove(33); // [11, 11, 22]
		c.remove(new Integer(11)); // [11,22]
		System.out.println(c.contains(22));
		// for(Object o : c)
		// {
		// System.out.println(o);
		// }

		// c.forEach(System.out::println); //Method reference(::) =>its know we have to
		// do println(i); bcoz of functional interface

		// 2. Using aslist method() using List Interface
		List<Integer> ls1 = Arrays.asList(11, 33, 22, 55, 44);
		ls1.add(2, 88);
		// ls1.forEach(System.out::println);

		// 3. Double Brace Initialization using List Interface
		List<Integer> ls2 = new ArrayList<Integer>() {
			{ // instance block
				add(33);
				add(66);
				add(99);
			}
		};
		ls2.forEach(System.out::println); // java 8
	}
}