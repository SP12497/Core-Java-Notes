package HQL_Queries;


import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		Transaction tx = session.beginTransaction();
		
		//Delete :
//		Query q = session.createQuery("delete from Student where name =:n");
//		q.setParameter("n", "Chetan");
		
		//Update :
//		Query q = session.createQuery("update Student set course=:c where name=:n");
//		q.setParameter("c", "Angular");
//		q.setParameter("n", "Sagar");
		
		//Join Query 
//
//		Query q = session.createQuery("select q.question, q.questionId , a.answer from Question as q INNER JOIN q.answer as a");
//		List <Object [] > list1 = q.getResultList();
//		
//		for(Object [] arr : list1)
//		{
//			System.out.println(Arrays.toString(arr));
//		}
		
		
		
		
		int r = q.executeUpdate();
		System.out.println("rows affected :" + r);
		
		tx.commit();
		session.close();
		factory.close();
		
		System.out.println("Project Finish");
	}

}
