import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class JdbcPreparedExample
{

	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
	  //loading driver
		
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		
		//create connectin
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dacfeb2020","root","cdacacts");  
         
		System.out.println("connection got");
		
        //create prepared statement
		
		
		//insert
		
		String q1 = "insert into book values(? ,?,?)";
		
		PreparedStatement pmt = con.prepareStatement(q1);
		
        Scanner sc = new Scanner(System.in);
		
		System.out.println("enter the book id");
		int bookid=sc.nextInt();
		
		System.out.println("enter the book name");
		String bookname=sc.next();
		
		System.out.println("enter the book price");
		float bookprice=sc.nextFloat();
		
		pmt.setInt(1, bookid);
		pmt.setString(2, bookname);
		pmt.setFloat(3, bookprice);
		
		int nrow=pmt.executeUpdate();
		
		System.out.println(nrow+ " " + "inserted");
		
		
		
		//select single or multiple
			
		
		String q2 = "select * from book ";
		
		
		
        
		
		
		
		PreparedStatement pmt1 = con.prepareStatement(q2);
		
	//	pmt.setInt(1, bookid);
		
		
		//execute the query
		
		ResultSet rs=pmt1.executeQuery();
		
		//iterate 
		
//		while(rs.next())
//		{
//			
//			System.out.println(rs.getInt(1)+rs.getString(2)+rs.getFloat(3));
//			
//		}
		
		
	
		
		ArrayList<Book> al = new ArrayList<>();
		Book obj;
		
		while(rs.next())
		{
			
			obj = new Book(rs.getInt(1),rs.getString(2),rs.getFloat(3));
			al.add(obj);
			
		}
		
		System.out.println("data from arraylist");
		
		for(Book o:al)
		{
			
			o.dispBook();
			
		}
		
		
		//update
		
		//delete
		
		//mysql procedure using callable statement
		
		     
         
         
         
			
	}
	
}
