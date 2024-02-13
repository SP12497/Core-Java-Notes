package com.tut;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Project Started!" );
        
        
      //Create student
        Student st = new Student();
        st.setId(101);
        st.setName("Sagar");
        st.setCity("Nandurbar");
        
        System.out.println(st);
        
        //create Session : 
        	// Type 1 :
        //SessionFactory factory = new Configuration().configure("hibernet.cfg.xml").buildSessionFactory();
        	//Type 2 :
        Configuration con = new Configuration();
        con.configure("hibernet.cfg.xml");		//if project in right place then no need to mention file name, it auto detect

        SessionFactory factory =  con.buildSessionFactory();
		

        //create session to save data
        Session session = factory.openSession();

        //Type 1
        session.beginTransaction();
        session.save(st);
        session.getTransaction().commit();
        
        //Type 2 :
//        Transaction tx  =(Transaction) session.beginTransaction();
//        session.save(st);
//        session.getTransaction();
//        tx.commit();			
                
        session.close();
    }
}
