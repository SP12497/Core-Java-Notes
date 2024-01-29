import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AlistExample
{
	
	
	static void updatebookprice(ArrayList<Book> al)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter the book id");
		int bookid=sc.nextInt();
		
		for(Book obj : al)
		{
			if(bookid == obj.bid)
			{
				obj.price -= obj.price*.1f;
				
				obj.dispBook();
			}
			
			
			
		}
		
		
		
	}
	

	public static void main(String[] args) 
	{
				
		
		Book b1 = new Book(1001,"java",900);
		Book b2 = new Book(1002,"c++",300);
		Book b3 = new Book(1003,"python",700);
		Book b4 = new Book(1004,"Oralce",400);
		
	//add
		
		ArrayList<Book> al = new ArrayList<>();
		
		
		ArrayList<Integer> a = new ArrayList<>();
		
		al.add(b1);
		al.add(b2);
		al.add(b3);
		al.add(b4);
		
		
		a.add(40);
		
	//display
		
		for(Book obj : al)
		{
			//System.out.println(obj);
			obj.dispBook();
		}
		
		
		Iterator it = al.iterator();
		
		while(it.hasNext())
		{
						
			 Book obj=(Book) it.next();
			 
			 obj.dispBook();
			 
			
			
		}
		
	//update
		
		updatebookprice(al);
		
		
		
		
	//delete
		
		
	//sort
		
		
		
		
	}
}
