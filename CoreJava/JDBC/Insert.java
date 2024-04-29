//Note : Changes data every time when execute the code :


import java.sql.*;

public class Insert
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		String url = "jdbc:mysql://localhost:3306/aliens";
		String uname = "root";
		String pass = "";
		
	//type 1 : 
		//String query = "insert into students values (4, 'Yugal')";

	//type 2 :
//		int userid = 6;
//		String username = "Suraj";
//		String query = "insert into students values (" + userid  + " ,' "  +  username + " ') ";		// '' for string
//						//"insert into students values (1 , 'Sagar' )"
		

	//type 3 : this is used when we have to add multiple rows.
			//use PrepareStatement when database is ready
		int userid = 7;
		String username = "Yogesh";
		String query = "insert into students values ( ? , ? )";
	
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		
		
// for type 1 and 2 : 
		//Statement st =  con.createStatement();

	//use executeUpdate(query) for DML commands.. which returns no data.. 	(for insert , update , delete)
	// int executeUpdate(query){}  so it returns no of rows affected			
//		int count = st.executeUpdate(query);
		
		// // ResultSet rs = st.executeQuery(query);	// for select 

//for type 3 :
		PreparedStatement st = con.prepareStatement(query);	//It is used when we use ? mark in querys
		st.setInt(1,userid);		//fill 1st question mark
		st.setString(2,username);	//fill 2st question mark

		int count = st.executeUpdate();  // do not pass query here


		System.out.println(count + " row affected");
		
	//close the resources
		st.close();
		con.close();	
	}

}
