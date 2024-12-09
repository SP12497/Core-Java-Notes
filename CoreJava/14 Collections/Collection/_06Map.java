/*
interface Map

interface SortedMap extends Map
class TreeMap implements SortedMap

class HashMap implements Map
class LinkedHashMap extends HashMap

Map:
	- A map contains value on the basis of key, i.e. key and value pair
	- Each key and value pair is known as an entry.
	- A Map contains unique keys.
	- A Map is useful if you have to search, update or delete elements on the basis of a key.
	
Map Implementation:
	- HashMap:
		- Does not maintain any order of its elements.
		- Allows one null key and multiple null values.
		- Asynchronized by default, so it's not thread-safe, but can be synchronized externally if needed.
		- Use: Generally preferred for its fast access and retrieval.
	- LinkedHashMap:
		- Uses a combination of hash table and linked list to maintain the insertion order.
		- Allows one null key and multiple null values.
		- Use: Suitable when you need predictable iteration order along with fast access.
	- TreeMap:
		- Maintains elements in sorted order based on the natural ordering of keys or a provided Comparator.
		- Does not allow null keys but allows multiple null values.
		- Use: Useful when you need to maintain elements in a specific sorted order.

---------------
interface Map:
	put(K key, V value): Inserts a key-value pair into the map. If the key already exists, the previous value associated with the key is replaced with the new value.
	get(Object key): Retrieves the value associated with the specified key. Returns null if the key is not present in the map.
	containsKey(Object key): Returns true if the map contains a mapping for the specified key, otherwise returns false.
	containsValue(Object value): Returns true if the map contains one or more keys mapped to the specified value.
	remove(Object key): Removes the mapping for the specified key from the map if it is present.
	keySet(): Returns a Set view of the keys contained in the map. Changes to the set are reflected in the map, and vice versa.
	entrySet(): Returns a Set view of the key-value mappings contained in the map. Each element in the set is a Map.Entry object representing a key-value pair.
	values(): Returns a Collection view of the values contained in the map. Changes to the collection are reflected in the map, and vice versa.
	size(): Returns the number of key-value mappings in the map.
	isEmpty(): Returns true if the map contains no key-value mappings.
	clear(): Removes all of the mappings from the map.
	getOrDefault(5 , -1);: 

What is ConcurrentHashMap?
	- ConcurrentHashMap is a thread-safe implementation of the Map interface.
	- It allows multiple threads to read and write concurrently without any blocking.
	- It achieves this by dividing the map into segments and locking only a portion of the map during updates.
	- ConcurrentHashMap does not throw ConcurrentModificationException if the map is modified while iterating over it.
	- It is a good choice for a multi-threaded environment where multiple threads are reading and writing to the map concurrently.
	eg. ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
	
HashMap vs. ConcurrentHashMap:
	- HashMap is not thread-safe, while ConcurrentHashMap is thread-safe.
	- HashMap allows multiple threads to read and write concurrently, but it may lead to data corruption if not synchronized externally.
	- ConcurrentHashMap allows multiple threads to read and write concurrently without any external synchronization.
	- HashMap is faster than ConcurrentHashMap for single-threaded applications.
	- ConcurrentHashMap is slower than HashMap for single-threaded applications due to the overhead of maintaining thread-safety.
	- HashMap throws ConcurrentModificationException if the map is modified while iterating over it, while ConcurrentHashMap does not.
	- HashMap is suitable for single-threaded applications or external synchronization, while ConcurrentHashMap is suitable for multi-threaded applications.
	- HashMap is not suitable for multi-threaded applications without external synchronization, while ConcurrentHashMap is suitable for multi-threaded applications.
*/
// Map.of("msg", "received"); check this method
// Map<String, Object> user = new HashMap<>(); user.put("city", "NDB");
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class _06Map {
	public static void main(String[] args) {

//	1. Declaration:
//		Map<String, String> map = new HashMap<>();
//		Map<String, String> map = new TreeMap<>();
		Map<String, String> map = new LinkedHashMap<>();

		HashMap<String, String> concreteMap = new HashMap<>();
		// SortedMap<Character, Integer> smap = new TreeMap<>();	// SortedMap only allows TreeMap


		// var map = new HashMap<String, String>(); // JAVA 10

		Map<Integer, Integer> unmodifiableMap = Collections.unmodifiableMap(new HashMap<>());	// Constant map
		Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>()); // Using a synchronized map (for thread-safety)	// Make entire map lock/synchronized
		Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();	// ConcurrentHashMap is a thread-safe // Lock only a portion (segment) of the map during updates

// 	2. Declaration + Initialization: using instance block
		Map<String, String> map1 = new HashMap<String, String>() { // empty '<>' cannot be used with anonymous classes
			{
				put("key1", "value1");
				put("key2", "value2");
				// Add more initial elements as needed
			}
		};

//	3. Insert/Update:
		map.put("Name", "Sagar"); // Insert // returns null
		map.put("Name", "Patil"); // Update // returns "Sagar"
		map.put("Age", "26");
		map.put("Game", "Cricket");


//	4. GET:
		System.out.println(map.get("Age")); // 26 // if not present: null
// 	5. toString
		System.out.println(map.toString()); // {Game=Cricket, Age=26, Name=Patil}
		System.out.println(map); 			// {Game=Cricket, Age=26, Name=Patil}

//	6. contains/present key or value:
		System.out.println(map.containsKey("Name")); 		// true
		System.out.println(map.containsValue("Cricket")); // true

		String str = null, gkey = "Game";
		if (map.containsKey(gkey))
			str = map.get(gkey);
		System.out.println("str game: " + str);

//	7. check empty:
		System.out.println(map.isEmpty()); // false
		System.out.println(concreteMap.isEmpty()); // true

//	8. Remove/delete:
		System.out.println(map.remove("Age")); // 26
		System.out.println(map.remove("Age")); // null

//	9. entry:
		System.out.println(map.entrySet()); 	// returns Map.Entry objects => [Game=Cricket, Name=Patil]
		System.out.println(map.keySet()); 		// [Game, Name]
		System.out.println(map.values()); 		// [Cricket, Patil]

// 10. Size
		System.out.println(map.size()); // 2
		System.out.println(concreteMap.size()); // 0 <- empty

// 11. Iteration:
// 11.1 Map.Entry<> 
		// interface Map { interface Entry { getKey(); getValue(); }}
		// Map.Entry<String, String> entry // store only 1 value
		// map.entrySet() // list of (Entry)
		Set<Map.Entry<String, String>> set = map.entrySet();
		set.forEach(System.out::println); // Game=Cricket Name=Patil

		for (Map.Entry<String, String> entry : map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());

// 11.2 keySet();
		for (String key : map.keySet())
			System.out.println(key + "-" + map.get(key));

// 11.3 Only Values:
		for (String val : map.values())
			System.out.print(val + " ");

// 12: constant map:
		// unmodifiableMap.put(1, 11);	// java.lang.UnsupportedOperationException
		Map<Integer, Boolean> mp = new HashMap<> ();
		mp.put(1,true);
		mp.put(2,false);
		mp.put(3,false);		
		// make Map as constant
		Map<Integer, Boolean> um = Collections.unmodifiableMap(mp);
		um.put(4, true);	// java.lang.UnsupportedOperationException
// 13. // Compare 2 maps
		boolean areEqual = mp.equals(um);

// ConcurrentHashMap:
		Map<Integer, String> map = new HashMap<>();		// This can cause data inconsistency in a multi-threaded environment because HashMap is not synchronized.
		// Thread 1
		map.put(1, "One");
		// Thread 2 (simultaneously)
		map.put(2, "Two");


		Map<Integer, String> cmap = new ConcurrentHashMap<>();	// ConcurrentHashMap allows safe concurrent access without the need for additional synchronization.
		// Thread 1
		cmap.put(1, "One");
		// Thread 2 (simultaneously)
		cmap.put(2, "Two");

	}
}
