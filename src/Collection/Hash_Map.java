package Collection;

/*
Map
 - key and value

- put
- set
- get
- entryset() get key and value
- getKey() - print the key
- getvalue() - display the value
- keyset() - pull out all keys


Iterator - iterface

  
Map.Entry - interface
*/


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hash_Map 
{
	
	public static void main(String[] args) 
	{
		
		HashMap <Integer , String> m = new HashMap<>();
		
		m.put(1, "DAC Blr");
		m.put(2, "DBDA BLR");
		m.put(3, "DAC PUNE");
		m.put(4, "DBDA PUNE");
		m.put(5, "DAC NOIDA");
		
	//Display all type 1
		System.out.println(m);
		
	//Display all type 2	
		for(Map.Entry obj : m.entrySet())
		{
			System.out.println(obj.getKey() + "  "+ obj.getValue());
		}
	
//get value by key
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Key to display value :");
		int pid = sc.nextInt();
		
		String val = m.get(pid);
		
		System.out.println(val);
		
//delete key	
		System.out.println("Enter key to delete :");
		pid = sc.nextInt();
		m.remove(pid);
		System.out.println(m);
		
		
		
		
		
		
		
	}

}
