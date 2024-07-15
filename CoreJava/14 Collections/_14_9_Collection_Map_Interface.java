/*
Java Map Interface:
Source: https://www.javatpoint.com/java-map

Map Interface:
    - An array of key-value pairs.
    - The Map interface, present in the java.util package, represents a mapping between a key and a value.
    - The Map interface is not a subtype of the Collection interface.
    - Therefore, it behaves a bit differently from the rest of the collection types.
    - A map contains unique keys.

Classes Implementing Map Interface:
    - HashMap Class:
		- Insertion order is not fixed.
        - Asynchronous/Not Thread-safe
        - Only one null key is allowed.
        - Introduced in Java 1.2

    - Hashtable Class:
		- Insertion order is not fixed.
        - Synchronous/Thread-safe		
        - No null keys allowed.
        - Introduced in Java 1.2

    - LinkedHashMap Class:
		- Insertion order is maintained.
		- Asynchronous/Not Thread-safe
		- If synchronization is required, you need to handle it explicitly, typically by wrapping them using Collections.synchronizedMap().
        - Introduced in Java 1.4

    - TreeMap Class:
		- Entries are sorted based on natural ordering of keys or by a comparator.
		- Asynchronous/Not Thread-safe
		- If synchronization is required, you need to handle it explicitly, typically by wrapping them using Collections.synchronizedMap().
        - Introduced in Java 1.2

Example Usage:
    Map<String, String> map = new HashMap<>();
    map.put("Name", "Sagar"); // Create (Key, Value)

    // Common Map Methods:
	map.put("age", 26);	   // insert or update key-value pairs into map
    map.toString();        // Returns a string representation of the map
    map.get("key_name");   // Returns the value associated with the key
    map.values();          // Returns a collection of all values
    map.keySet();          // Returns a SET of all keys
    map.entrySet();        // Returns a SET of all key-value pairs

	Map.Entry<String,String> entries
		- entryMap.getKey()
		- entryMap.getValue()

    // Iterating through a Map:
    Set<Map.Entry<String, String>> entries = map.entrySet();
    for (Map.Entry<String, String> entry : entries) {
        entry.getKey();
        entry.getValue();
    }

HashMap Node Structure:
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
	
Additional Important Notes:
	- The Map interface provides three collection views: key set, value collection, and key-value set.
	- Maps cannot contain duplicate keys; each key can map to at most one value.
	- The default implementation of the Map interface is the HashMap.
	- Iterating through maps is not guaranteed to be in any particular order unless explicitly mentioned (e.g., LinkedHashMap, TreeMap).
	- ConcurrentHashMap can be used for thread-safe operations, introduced in Java 1.5.
*/


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class _14_9_Collection_Map_Interface
{
	public static void main(String[] args) 
	{ 
		Map <String,String> map = new HashMap<>();
		
		map.put("Name", "Sagar");		// (Key , Value);
		map.put("Qualification", "Engg");
		map.put("Hobby", "Singing");
		
		System.out.println(map);	//toString()
		System.out.println(map.get("Name"));	// Sagar

		System.out.println("----------------------------------");
		
		System.out.println(map.values());		// [Engg, Singing, Sagar]
		System.out.println(map.keySet());		// [Qualification, Hobby, Name]

		System.out.println("----------------------------------");

		//Display using map.get(key)
		Set<String> keys = map.keySet();		//interface Set
		for (String key : keys) {
			System.out.println(key +"  -->  "+ map.get(key));			
		}

		System.out.println("===================");
		//We can't repeat the key But we can repeat the values
		map.put("Name", "Swapnil");	// change Sagar to Swapnil


		System.out.println("----------------------------------");

		//Display using Map.Entry
		Set < Map.Entry<String,String> > values = map.entrySet();
		// 1 :
		for(Map.Entry<String,String> val : values) {
			System.out.println(val.getKey() +"\t"+val.getValue());
		}

		// 2 :
		for(Map.Entry<String,String> obj : map.entrySet())
			System.out.println(obj.getKey() + "  "+ obj.getValue());
	
	}	
}
