package com.springcore.auto.wire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyRunner {

	static public void printEmp() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/springcore/auto/wire/autoconfig.xml");
		Employee employee = ctx.getBean("employee", Employee.class);
		
		System.out.println(employee);
	}
}
