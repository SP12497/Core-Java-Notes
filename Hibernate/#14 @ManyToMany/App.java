package com.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App
{
	public static void main(String[] args) 
	{
		System.out.println("Project Started");
		Configuration cfg = new Configuration();
		cfg.configure("hibernet.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Emp e1 = new Emp();
		Emp e2 = new Emp();
		
		e1.setEid(101);
		e1.setEname("Sagar");
		
		e2.setEid(102);
		e2.setEname("Yogesh");
		
		Project p1 = new Project();
		Project p2 = new Project();
		
		p1.setPid(201);
		p1.setPname("Train Security");
		
		p2.setPid(202);
		p2.setPname("Chat Box");
		
		List<Emp> elist = new ArrayList<Emp>();
		List<Project> plist = new ArrayList<Project>();
		
		elist.add(e1);
		elist.add(e2);
		
		plist.add(p1);
		plist.add(p2);
		
		e1.setPlist(plist);
		p2.setElist(elist);
		
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(e1);
		session.save(e2);
		session.save(p1);
		session.save(p2);
		
		tx.commit();
		session.close();
		
		System.out.println("Done");
				
	}
	
	

}
