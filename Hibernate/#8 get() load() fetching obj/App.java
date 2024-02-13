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
        
        //fetch delails of student 101
        	// We have 2 method to fetch : 1. get , 2. load
        
        //1. Get method:
//        Student s1 = session.get(Student.class, 101);		//DB call here
//        Student s2 = session.get(Student.class, 101);
//        Student s3 = session.get(Student.class, 103);		//DB call here
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
        
        //load method
        Student s11 = session.load(Student.class, 101);
        Student s22 = session.load(Student.class, 102);
        System.out.println(s22);	//DB call here
        
        
        
        
        
        
        
        System.out.println("Done...");
    }
}
