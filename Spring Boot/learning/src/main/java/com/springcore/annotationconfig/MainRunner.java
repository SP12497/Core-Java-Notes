package com.springcore.annotationconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainRunner {
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(JavaConfig.class);
		
		Employee emp = context.getBean("employee", Employee.class);
		Teacher teacher = context.getBean("getTeacher", Teacher.class);
		
		emp.working();
		teacher.teaching();
	}
}
