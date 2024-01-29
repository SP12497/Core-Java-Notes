import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ObjectWrite
{

	public static void main(String[] args) throws IOException 
	{
	
		//input is ready- read input
		
//		Book b1 = new Book(1001,"java",900);
//		Book b2 = new Book(1002,"c++",300);
//		Book b3 = new Book(1003,"python",700);
//		Book b4 = new Book(1004,"Oralce",400);
		
		//to create file for write operation
		
		FileOutputStream fos = new FileOutputStream("BookFile.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		int i =0;
		
		Scanner sc = new Scanner(System.in);
		
		while(i<2)
		{
			System.out.println("enter the book id");
			int bookid=sc.nextInt();
			
			System.out.println("enter the book name");
			String bookname=sc.next();
			
			System.out.println("enter the book price");
			float bookprice=sc.nextFloat();
					
			
			Book obj = new Book(bookid,bookname,bookprice);
			
			oos.writeObject(obj);
			i++;
		}
		
//		oos.writeObject(b1);
//		oos.writeObject(b2);
//		oos.writeObject(b3);
//		oos.writeObject(b4);
		
		
		
		System.out.println("object write is over");
		
		fos.close();
		oos.close();		
	}
}
