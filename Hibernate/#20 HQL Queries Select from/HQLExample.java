package HQL_Queries;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.Student;

public class HQLExample 
{
	public static void main(String[] args) 
	{
		System.out.println("Project Start");
		 Configuration cfg = new Configuration(); 
		 cfg.configure("hibernet.cfg.xml");
		  SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		//String query = "from Student";
		//String query = "from Student where course = 'java'";
		String query = "from Student where course = :x";
		//String query = "from Student as s where s.course = :x and s.name = :y";
		
		Query q = session.createQuery(query);
		q.setParameter("x", "Java");
		//q.setParameter("y", "Suraj");
		
		//single select = unique
		//multi select = list
		List<Student> list = q.list();
		for(Student s : list)
		{
			System.out.println(s.getName());
		}
		session.close();
		factory.close();
		
		System.out.println("Project Finish");
	}

}
