package com.cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.Student;

public class FirstLevelCache
{

	public static void main(String[] args) 
	{
		SessionFactory factory = new Configuration().configure(hibernet.cfg.xml).buildSessionFactory();
		
		Session session = factory.openSession();
		
		First Level Session  Data store in session object
		 Here Select Query fires only once
		
		Student student1 = session.get(Student.class, 101);
		System.out.println(student1);
		
		System.out.println(Working....);
		
		Student student2 = session.get(Student.class, 101);
		System.out.println(student2);
		
				
		session.close();
		factory.close();
	}

}
