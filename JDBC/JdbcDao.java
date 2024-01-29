import java.sql.*;

class Student
{
	int roll_no;
	String name;
}

class StudentDAO
{
	Connection con = null;
	
	public void connectDB() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbStudent" , "root"	, "");
	}
	public Student getStudent(int rollNo) throws Exception
	{
		Student s = new  Student();
		
		s.roll_no = rollNo;
		
		Class.forName("com.mysql.jdbc.Driver");
		String query = "Select username from students where userid =  " + rollNo ;
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbStudent" , "root"	, "");
		
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);

		rs.next();
		String name = rs.getString("userName");

		s.name = name;
		
		return s;
	}

	public void addStudent(Student s) throws SQLException
	{		
		String query = "Insert into students (userid,userName) value( ?,? )";

		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, s.roll_no);
		st.setString(2, s.name);

		int count = st.executeUpdate();
		
		System.out.println(count + " row/s are added");			
	}

	
}

public class JdbcDao
{
	public static void main(String[] args) throws Exception 
	{
		StudentDAO dao = new StudentDAO();
		dao.connectDB();	//connect Database
	
		//select and where clause
		Student s1 = dao.getStudent(301);	//Select command
		System.out.println(s1.roll_no + "  :  " + s1.name);
		
		
		//Insert command
		Student s2 = new Student();
		s2.roll_no = 301;	s2.name = "Kunal";
		//dao.addStudent(s2);
		
		
		
	}
}
