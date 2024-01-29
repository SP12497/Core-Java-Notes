import java.util.HashMap;
import java.util.Map;

public class MapBookExample
{

	public static void main(String[] args) 
	{
	
		
		Book b1 = new Book(1001,"java",900);
		Book b2 = new Book(1002,"c++",300);
		Book b3 = new Book(1003,"python",700);
		Book b4 = new Book(1004,"Oralce",400);
		
		
		HashMap<Integer, Book> hm = new HashMap<>();
		//add
		
		hm.put(1, b1);
		hm.put(2, b2);
		hm.put(3, b3);
		hm.put(4, b4);
		
				
		
		//display
		
		for(Map.Entry m: hm.entrySet())
		{
			
			Book obj=(Book) m.getValue();
			
			 		
			System.out.print(m.getKey()+ " ");
			obj.dispBook();
		}
		
		
		//update
		
		
		
		//delete
		
		
		
		//
		
		//sort
		
		
		
	}
	
}
