package com.tut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception 
    {
        System.out.println( "Project Started!" );
               
        Address ad = new Address();
        ad.setStreet("Steet1");
        ad.setCity("Ndb");
        ad.setOpen(true);
        ad.setAddedDate(new Date());
        ad.setX(111.222);
        
        System.out.println(ad);
        
        //Add image in Address image variable
        FileInputStream fis = new FileInputStream("src/main/java/nature.jpg");
        byte[] data = new byte[fis.available()];
        fis.read();
        ad.setImage(data);
        
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
        session.save(ad);
        session.getTransaction().commit();
        
        //Type 2 :
//        Transaction tx  =(Transaction) session.beginTransaction();
//        session.save(st);
//        session.getTransaction();
//        tx.commit();
        
        session.close();
        
        System.out.println("Done...");
    }
}
