
//1. Import the package
import java.sql.*;

public class Select
{
	public static void main(String[] args) throws Exception 
	{
		String url = "jdbc:mysql://localhost:3306/mydbStudent";	//mydbStudent is Database name
		String uname = "root";
		String pass = "";
		//String query = "Select userName from students where rollno = 101";  //It returns only one row so use only one display command	
		String query = "select * from students";		//It return all tables rows so we use while loop to print
		
	//2. a. Load the Drivers (liabrary) :  
		//https://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.38
	// 2 b. Register the Drivers :
		Class.forName("com.mysql.jdbc.Driver");
		
	//3.	Establish the connection :
		Connection con = DriverManager.getConnection(url, uname, pass);
		
	// 4.	Create the statement :
		Statement st =  con.createStatement();  //It is used for normal statements no ? mark
			//	PreparedStatement ps = con.prepareStatement(query);  // It is used when we use ? mark in queries.
		
	//5.	Execute the Query :
		ResultSet rs = st.executeQuery(query);		//This will return ResultSet (whole Table ie rows and columns)
		
	//6.	Process Result	:
	
		//for Single row
//		rs.next(); //move cursor
						   // userid				//userName
//		System.out.println( rs.getInt(1) + " : " + rs.getString(2));
	
		//for more than one row :
		//Type 1 :
//		while(rs.next())
//		{
//			String name = rs.getString("userName");
//			String id = rs.getString("userid");
//				//String id = Integer.toString(rs.getInt("userid"));
//			
//			System.out.println( id + "  " + name );
//			
//		}
		
		////Type 2 :
//		String userData= "";
//		while(rs.next())
//		{				 // userid				//userName
//			userData = rs.getInt(1) + " : " + rs.getString(2);
//			
//			System.out.println(userData );
//			
//		}
		
		
	//7.	Close
		st.close();
		con.close();	
	}

}
