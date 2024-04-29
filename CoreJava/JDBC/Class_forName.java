import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Class.forName is used to load the class not instance
 */
public class CMain2 
{

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		//Abc obj =  new Abc();
		
		//Class.forName("Abc");	//Load the class ie when class is loaded static blocks are automatically called
		Class.forName("Abc").newInstance();	//Load the class and create the instance
		
		
	//In JDBC
		//to load the driver we use
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//OR
			Class.forName("com.mysql.jdbc.Driver");
			
		
		
	}

}	

class Abc 
{
	static	//call automatically when class is loaded
	{
		System.out.println("In Static");
	}
	
	//Instance block //call automatically when Instance/object is created
	{
		System.out.println("In Instance");
	}
}
