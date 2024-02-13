package com.tut;

import org.hibernate.*;
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
       
        Configuration cfg = new Configuration();
        cfg.configure("hibernet.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        
        Certificate c1 = new Certificate();
        c1.setCourse("hybernet");
        c1.setDuration("2 Months");
        Student s1 = new Student();
        s1.setRollNo(101);
        s1.setName("Sagar");
        s1.setCerti(c1);
        
        Certificate c2 = new Certificate();
        c2.setCourse("Spring");
        c2.setDuration("6 Months");
        Student s2 = new Student();
        s2.setRollNo(102);
        s2.setName("Suraj");
        s2.setCerti(c2);
        
        Transaction tx = session.beginTransaction();	//permanent/physical changes
        session.save(s1);
        session.save(s2);
        
        tx.commit();

        
       session.close(); 
        System.out.println("Done...");
    }
}
