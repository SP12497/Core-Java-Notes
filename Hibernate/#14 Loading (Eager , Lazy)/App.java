package com.QueAns;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App2 {
	public static void main(String[] args) {
		System.out.println("Project Started...");

		Configuration cfg = new Configuration();
		cfg.configure("hibernet.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

//		Question q1 = new Question();
//		q1.setQuestionId(101);
//		q1.setQuestion("What is Java?");
//
//		Answer a1 = new Answer();
//		a1.setAnswerId(201);
//		a1.setAnswer("Java is OO Programming Language");
//		a1.setQuestion(q1);
//		
//		Answer a2 = new Answer();
//		a2.setAnswerId(202);
//		a2.setAnswer("Java is Platform Independent");
//		a2.setQuestion(q1);
//		
//		Answer a3 = new Answer();
//		a3.setAnswerId(203);
//		a3.setAnswer("Java is High Level PL");
//		a3.setQuestion(q1);
//		
//		List <Answer> list = new ArrayList<Answer>();
//		list.add(a1);
//		list.add(a2);
//		list.add(a3);
//		
//		q1.setList(list);
		
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

//		session.save(q1); 
//		session.save(a1);
//		session.save(a2);
//		session.save(a3);











//Types of loading	:
	//1. Lazy loading	: By default
	//2. Eager Loading	: @OneToMany(fetch = FetchType.EAGER)
		
	//This is Lazy Loading	: 
			// Select query called when necessary. 2 select query(Question and Answer)
//		Question q = (Question) session.get(Question.class, 101);	
//		System.out.println(q.getQuestionId());	//here Select query calls only for question
//		System.out.println(q.getQuestion());	
//		
//		System.out.println(q.getList().size());	//here we call answer //now here Select query calls answer
		
	
	
		//This is Eager Loading		: specify eager loading attribute in Question class	
			//Only 1 select query calles for Question and Answer.
				Question q = (Question) session.get(Question.class, 101);	
				System.out.println(q.getQuestionId());	//here Select query calls only for question
				System.out.println(q.getQuestion());	
				
				//System.out.println(q.getList().size());	//now here Select query calls answer
				
		tx.commit(); // Store in database
















		

//		Question q = session.get(Question.class, 101);
//		System.out.println(q.getQuestion());
//		for(Answer a : q.getList())
//		{
//			System.out.println(a.getAnswer());
//		}
//		
		session.close();
		
		System.out.println("done...");

	}
}
