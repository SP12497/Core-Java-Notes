package com.sqlquery;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class SQLExample 
{
	public static void main(String[] args) 
	{
		SessionFactory factory = new Configuration().configure("hibernet.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		
		//This is SQL Query :
		String q = "Select * from Student";
		
		NativeQuery nq = session.createSQLQuery(q);
		
		List<Object []> list = nq.list();
		
		for(Object[] student : list)
			System.out.println(Arrays.toString(student));
		
		session.clear();
		factory.close();
	}
}
