package com.springcore.annotationconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.springcore.annotationconfig")
public class JavaConfig {
	
	@Bean
	public Teacher getTeacher() {
		Teacher teacher = new Teacher();
		return teacher;
	}
}
