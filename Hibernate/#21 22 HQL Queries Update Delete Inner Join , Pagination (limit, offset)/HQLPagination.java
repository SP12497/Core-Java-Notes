// HQL limit and offset property of query

package com.pagination;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.tut.*;

import com.tut.Student;

public class HQLPegination 
{
	public static void main(String[] args) 
	{
		SessionFactory factory = new Configuration().configure("hibernet.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		
		//import org.hibernate.query.Query;
		Query query = session.createQuery("from Student");
		
		//implementing pegination using hibernate
		query.setFirstResult(2); //index start from row no// ie offset (2)
		query.setMaxResults(4); //total no of records after 2nd row // limit(4);
		
		List<Student> list = query.list();
		
		for(Student st : list)
		{
			System.out.println(st.getRollNo() + " : " + st.getName());	
		}
		
		session.close();
		factory.close();
		
	}

}
