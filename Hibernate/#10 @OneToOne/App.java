package com.QueAns;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App
{
	public static void main(String[] args) 
	{
		System.out.println("Project Started...");
				
		Configuration cfg = new Configuration();
		cfg.configure("hibernet.cfg.xml");
		SessionFactory factory =  cfg.buildSessionFactory();
		
		Answer a1 = new Answer();
		a1.setAnswerId(201);
		a1.setAnswer("Java is Programming Language");
		Question q1 = new Question();
		q1.setQuestionId(101);
		q1.setQuestion("What is Java");
		q1.setAnswer(a1);
	
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(q1);	//store in session
		session.save(a1);
		
		
		//Display Question :
		Question newQ = session.get(Question.class, 101);
		System.out.println(newQ.getQuestion());
		System.out.println(newQ.getAnswer().getAnswer());
				
		tx.commit();	//Store in database
		session.close();
		
	}
}
