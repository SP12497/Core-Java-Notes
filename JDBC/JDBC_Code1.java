package JDBC;
//1. Import the package
import java.sql.*;

public class JDBC_Code1
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		String url = "jdbc:mysql://localhost:3306/aliens";
		String uname = "root";
		String pass = "";
		String query = "select userName from students where userid=3";
		
		//2. a. Load the Drivers (liabrary) :  // https://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.38
		// 2 b. Register the Drivers :
		Class.forName("com.mysql.jdbc.Driver");
		
		//3.	Establish the connection :
		Connection con = DriverManager.getConnection(url, uname, pass);
		
		// 4.	Create the statement :
		Statement st =  con.createStatement();
		
		//5.	Execute the Query :
		ResultSet rs = st.executeQuery(query);		//executeQuery returns ResultSet
		
		//6.	Process Result	:
		rs.next();		//move the cursor/pointer to the next line

		String name = rs.getString("userName");
		
		System.out.println(name);
		
		
		//7.	Close
		st.close();
		con.close();	
	}

}
