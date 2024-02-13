/*
	Map Interface :
		- Array of key value pair
		- The Map interface present in java.util package represents a mapping between a key and a value. 
		- The Map interface is not a subtype of the Collection interface. 
		- Therefore it behaves a bit differently from the rest of the collection types. 
		- A map contains unique keys.
			
			- HashMap Class : Asynchronous/ Not Thread safe		//Insertion order is not fix.	//only one NULL allow //1.2
			- HashTable Class : Synchronous/Thread safe				//Insertion order is not fix.	//no NULL	//1.2

			- LinkedHashMap Class : Insertion order is fixed.
			- TreeHashMap : Insertion in sorted format.

	Map <String,String> map = new HashMap<>();
		map.put("Name", "Sagar");	//create	//(Key , Value);
		map.set("Name", "Suraj");	//update
		map					//toString
		map.get("key_name")		//value
		map.keySet()		//list of keys
		map.values()		//list of values
		map.entrySet();		//list of keys and values
	
	Set <  Map.Entry<String,String> > val  = map.entrySet(); :
		val.getKey() ;
		val.getValue();
		
		
Each HashMap node contains :
	Node<K , V> :
		int hash;
		K key;
		V value;
		Node<K,V> next;
*/



import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main
{
	public static void main(String[] args) 
	{ 
		Map <String,String> map = new HashMap<>();
		
		map.put("Name", "Sagar");		//(Key , Value);
		map.put("Qualification", "Engg");
		map.put("Hobby", "Singing");
		
		System.out.println(map);	//toString()
		System.out.println(map.get("Name"));

		System.out.println("----------------------------------");
		
		System.out.println(map.values());		//[Engg, Singing, Sagar]
		System.out.println(map.keySet());	//[Qualification, Hobby, Name]

		System.out.println("----------------------------------");

//Display using map.get(key)
		Set<String> keys = map.keySet();		//interface Set
		for (String key : keys)
		{
			System.out.println(key +"  -->  "+ map.get(key));			
		}

	System.out.println("===================");
		//We can't repeat the key But we can repeat the values
		map.put("Name", "Swapnil");	//change Sagar to Swapnil
		map.set("Hobby","Dancing");	//change Singing to Dancing


	System.out.println("----------------------------------");

//Display using Map.Entry
	Set < Map.Entry<String,String> > values = map.entrySet();
	// 1 :
	for(Map.Entry<String,String> val : values)
	{
	    System.out.println(val.getKey() +"\t"+val.getValue());
	}

	// 2 :
	for(Map.Entry obj : map.entrySet())
	{
		System.out.println(obj.getKey() + "  "+ obj.getValue());
	}
	
	}	
}