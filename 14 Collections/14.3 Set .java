//Duplicates are not allowed
//get(index) method is not available
/*
	SET interface :
		HashSet			//insertion order not fix	//only one NULL
		LinkedHashSet  //insertion order fix		//only one NULL
		TreeSet			//sorted form				//no NULL (throws NullPointerException)
*/

import java.util.*;
class GFG {
 
    public static void main(String[] args)
    {
        Set<String> hs = new HashSet<String>();
 
        // Elements are added using add() method
        hs.add("A");	//true	
        hs.add("B");	//true
        hs.add("C");
        hs.add("B");	//false
        hs.add("D");
        hs.add("E");


		System.out.println(hash_Set); [A, B,C, D, E]

		System.out.println("Contains " + hs.contains("D"));	//TRUE
 
        // Iterating though the Set
        for (String value : hs)
            System.out.print(value + ", ");

        System.out.println();
		
		hs.remove("B");
		hs.clear()	//empting the set
	//-----------------
		
		Set<Integer> a = new HashSet<Integer>();
        a.addAll(Arrays.asList(
            new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));
			
			
			
    }
	
}