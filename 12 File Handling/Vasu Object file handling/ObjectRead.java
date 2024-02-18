import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ObjectRead
{
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		FileInputStream fis = new FileInputStream("BookFile.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Book obj;
		
		//Book obj=(Book) ois.readObject();
		
		//System.out.println(obj);
		//obj.dispBook();
		
		//Book obj1=(Book) ois.readObject();
		//obj1.dispBook();
		try
		{
			while(( obj= (Book) ois.readObject())!=null)
			{
				obj.dispBook();
			}
		}catch(EOFException e)
		{
			System.out.println("eof");
		}
		
		//sort
		//add object into arraylist do the operation
		
		ArrayList<Book> al = new ArrayList<>();
		
		Sortbyprice sp = new Sortbyprice();
		
		Collections.sort(al,sp);
		
		
	}
}


class Sortbyprice implements Comparator<Book>
{

	@Override
	public int compare(Book b1, Book b2) {
		if(b1.price>b2.price)
		return 1;
		else
			return -1;
	}
	
}





