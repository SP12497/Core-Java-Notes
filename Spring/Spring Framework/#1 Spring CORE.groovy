What is Spring :
	Spring is a Dependency Injection framework to make java application loosely coupled.

MVC = Web Framework / Servlet JSP/ UI LAYER
	Spring Core
	Spring Data Integration
	Spring Web
	Spring Boot
	Spring JDBC
	Spring ORM
	ORM Tool = Hybernate

=====================================
ORM Frameworks :
	- TopLink
	- Hibernate
	- iBATISS
	- ORMLite
	- JPOX
-----------------------------------------


									=============
									Spring Core :
									=============

 - package name of the code :
	YOUTUBE : Spring Framework Tutorial in Hindi by LearnCodeWithDurgesh.
	
	#6 First Maven Proj : package com.springcore;
	#9 Injecting Collection Types[ List , Set , Map , Properties ] : package com.springcore.collection;
	#10 Injecting Reference Type : package com.springcore.reference;
	#11 Constructor Injection : package com.springcore.constructor_Injection;
	#12 Ambiguity Problem and its Solution with Constructor Injection (use index no) : package com.springcore.constructor_Injection;
	#14 Lifecycle methods Using XML/Annotation : package com.springcore.lifecycle;
	#18 Autowiring using XML complete Session  : package com.springcore.auto.wire;
	#19 @Autowired Annotation for Autowiring : package com.springcore.auto.wire.annotation;
	#21 Spring Standalone Collections[List,Map,Properties] | Util Schema in Spring : package com.springcore.stand.alone.collection;
	#22 Stereotype Annotations | @Component Annotation | @Value Annotation | Singleton prototype : package com.springcore.stereotype;
	#25 Spring Expression Language | SpEL : package com.springcore.spel;
	#28 Removing Complete XML for Spring Configuration | @Configuration | @ComponentScan | @Bean Annotation : package com.springcore.javaconfig;
				----End----

====================================
Steps to create project :
	1. Create MAVEN quick-start project
	2. Adding dependancy => spring core , spring context
		pom.xml :	
			<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-core</artifactId>
				<version>5.2.5.RELEASE</version>
			</dependency>
				
			<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context</artifactId>
				<version>5.2.5.RELEASE</version>
			</dependency>
	3. Creating beans-java pojo :
	 Plain Old Java Object :
		- It is an ordinary Java object, not bound by any special restriction other than those forced by the Java Language Specification and not requiring any classpath. 
	4. Creating configuration file => config.xml
			<beans	 	xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context= "http://www.springframework.org/schema/context"
						xmlns:p= "http://www.springframework.org/schema/p"
						xmlns:c="http://www.springframework.org/schema/c"
						xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans.xsd
						http://www.springframework.org/schema/context
						http://www.springframework.org/schema/context/spring-context.xsd
					">
			 </beans>
	5. Setter Injection :
			<!-- This is First Object -->
				<bean class="com.springcore.StudentBean" name="student1" >
					<property name="studentId">
						<value>101</value>
					</property>
				
					<property name="studentName" value="Sagar Patil" /> 
								
					<property name="studentCity">
						<value>Nandurbar</value>
					</property>
				</bean>
			
			<!-- This is Second Object -->  <!-- using p schema -->
				<bean class="com.springcore.StudentBean" name="student2" p:studentId="111" p:studentName="Suraj Patil" p:studentCity="Bangalore" />
	
	6. Main class : which can pull the object and use.

SPRING MODULE :
	- Spring Core Modules :
		- Core 
		- beans
		- Context 
		- spEL
		SPRING CORE:
			- Introduction
			- ioc using Dependency Injection (DI)
			- Spring Container
			- Types of Container
			- Bean and Bean Lifecyle
			- DI using Constructor
			- DI using Setter
			- AutoWiring
	- AOP
	- MVC
	- Boot
	- REST
	- JDBC AND JPA
	
	- Aspect
	- Instrumentation
	- Messaging
	- Data Access / Integration :
		- JDBC
		- ORM : Object Relational Mapping
		- JMS : Java Messaging Service
		- OXM : Object XML Mapping
	- WEB :
		- Web
		- Servlet
		- Portlet
		- WebSocket
	- TEST
	
	
============================
IOC container  = Dependency	Injection (inversion of control)
	- Spring IoC is the mechanism to achieve loose-coupling between Objects dependencies.
	- interface ApplicationContext represents IOC container
Spring IOC Container : manage object life cycle, dependancy injection, we get ready to use object
	-> interface ApplicationContext extends Beansfactory  : represents IOC Container
		- class ClassPathXMLApplicationContext		// XML configuration by java class path
		- class AnnotationConfigApplicationContext	// annotation used configuration
		- class FileSystemXMLApplicationContext		// search config file from file system

Dependency Injection can be done in 2 ways :
	- Using Setter Injection / Property Injection
	- Using Constructor Injection
Configuration files :
	- Where we declare beans and its dependancy
	
Data Types supported by IOC (Dependencies) :
		1. Primitive Data Types
		2. Collection Types :
			List, Set, Map, and Properties
		3. Reference Type :
			Other class Object

------------------------			
create object using xml:
------------------------
- config.xml : using Setter Injection
<beans>
	<bean class="com.springcore.Student" name="student">
		<property name="sId">
			<value>01</value>
		</property>
		<property name="sName">
			<value>Sagar Patil</value>
		</property>
		<property name="sAge" value="26" />

	</bean>
</beans>

- config.xml : using Constructor Injection
<beans>
	<bean class="com.springcore.Student" name="student">
		<constructor-arg>
			<value>Sagar</value>
		</constructor-arg>

		or 
		<constructor-arg value="Sagar" type="String" />		// index="0" means first parameter
	</bean>
</beans>
- Main class:
	ApplicationContext context = new ClassPathXMLApplicationContext("config.xml");	//  IOC dependancy injection
		// AbstractApplicationContext context = new ClassPathXMLApplicationContext("config.xml");	//  IOC dependancy injection
	Student student = (Student) context.getBean("student");
		// Student student = context.getBean("student", Student.class);
	System.out.println(student);

=====================================
#13 : Spring Bean Life Cycle Methods :   
  (Code : package com.springcore.lifecycle)
	- Spring provide two important methods to every bean:
		- public void init()	:
			- Initialization code, loading config, connection db, webservice etc
		- public void destroy()	:
			- clean up code
	
 -> Spring Bean life cycle Working:
	- START: 
		- create object
		- Initialization happen eg. Student name = "Sagar"...
		-> init() do initialization or configuration
		- perform operation (Then we Read and use the bean)
		-> destroy()	clean up code once bean operation is completed
	- END

 - We can implement using following 3 types:
	1. Using XML
		- create init and destroy method in Samosa pojo class. we can you any function name instead of init and destroy
		<bean class="com.springcore.Samosa" name="s1" 
			init-method="init" destroy-method="destroy">
			<property name="price" value="10" />
		</bean>
		- Main class:
			AbstractApplicationContext context = new ClassPathXMLApplicationContext("packagename");
			Samosa s1 = (Samosa) context.getBean("s1");	// creation of bean
			context.registerShutdownHook();	// destroy the bean
	2. Using Interface
		- interface InitializingBean => public void afterPropertiesSet() throws Exception {}		//for init method
		- interface DisposableBean	 => public void destroy() throws Exception {} //for destroy method
		eg. public class Pepsi implements InitializingBean, DisposableBean {
				private double price;
				public setPrice(double price){ this.price = price;}
				public void afterPropertiesSet() throws Exception {}
				public void destroy() throws Exception {}
			}
			<bean class="com.springcore.Pepsi" name="p1" >
				<property name="price" value="10" />
			</bean>
	3. Annotations
		- @PostConstruct
		- @PreDestroy
		Note: above both annotation are part of Java EE. 
			And since java EE has been deprecated in Java 9 and removed in Java 11 
			we have to add an additional dependancy to use.
			dependancy: artifactId= javax.annotation-api
	eg.
	public class Pepsi implements InitializingBean, DisposableBean {
		private double price;
		public setPrice(double price){ this.price = price;}
		@PostConstruct public void start() {}
		@PreDestroy public void end() {}
	}
	- by default disable, enable using below line in xml file:
		<context:annotation-config />

=====================================
#17 : Autowiring :
	- Feature of spring framework in which spring container inject the dependencies automatically.
	- It works only with reference only. (works on object only)
		can't be used to inject primitive and string values.
	- Manual wiring in xml :	<ref bean=""/>
		Spring container will do automatically wiring using @Autowired  
		eg. Object A depends on B
		spring container will inject Object B into A (A -----wiring----- B )

	There are 2 Types :
		1. XML :	code: package com.springcore.auto.wire;
			Autowiring modes: eg. <bean autowire="byName" ...>
				- no
				- byName	: search object by reference/object name eg. Address address; (search by "address")
					eg. <bean class="com.springcore.Address" name="address" >    </bean>
					<bean class="com.springcore.Employee" name="emp1" autowire="byName" />   // search for object by property name "address" // class Employee {Address address;}
				- byType: 
					search obj by reference type/class name : Address class
					Note: will get error, if multiple object are presents for Address class.
				- constructor: use constructor injection, above 2 will call setter injection
				- autodetect	: deprecated since Spring 3.
		2. Annotations		: code: package com.springcore.auto.wire.annotations;	
			- @Autowired : 
				- we can use autowire annotation using 3 types:
					1. On Property	:	class Employee { @Autowired private Address address; }
					2. On Setter	: 
						class Employee { private Address address; 
							@Autowired public void setAddress(Address address) { this.address = address; }
						}
					3. On constructor: 
						class Employee { private Address address; 
							@Autowired public Employee(Address address) { this.address = address; }
						}
				- It works on byType
					
#20 : @Qualifier	: code: package com.springcore.auto.wire.annotations;
		- There may be a situation, when you create more than one bean of the same type 
		  and want to wire only one of them with a property. 
		  In such cases, you can use the @Qualifier annotation along with @Autowired 
		  to remove the confusion by specifying which exact bean will be wired.
			
	@Autowired
	@Qualifier("address2")		//check "byName"	// 
	private Address address;


#21 : Spring Standalone Collections[List,Map,Properties] 
		- It uses util schema.
		
#22 : Stereotype Annotation :	code :package com.springcore.stereotype;
		@Component : on the class	
			- same as xml <bean/>
			- create object automatically.
			- config.xml :  <context:component-scan base-package="Only_package_name" />
			  or @ComponentScan()
			- it by default create object as classRoom class name using camelcase (student)
			- @Component class Student{}  then object name is : (student) by default
			  Student s = context.getBean("student", Student.class);
			- @Component("stud") class Student{}	// obj name is stud.

		@Value :
			- on the property.
			- Used to set the value into the variable/property
			@Component("stud") class Student{
				@Value("Sagar")
				String name;
				
				@Value("#{addressId1}")			
				private List<String> address;
			}


#24 Beans Scope :
	@Component						// Scope annotation always use with Component Annotation
	@Scope("singleton")	class Student{}
		singleton : only one obj of one class. // by default
		prototype : one class , many object
		request	  : HTTP request
		session
		globalsession
	xml:	<bean class="" name="" scope="" />

	practical:
		Student s1 = context.getBean("student", Student.class);
		Student s2 = context.getBean("student", Student.class);
		System.out.println(s1.hashCode() + " : " + s2.hashCode() );		// singleton: both hashCode are same // In Prototype: both are different 

================================
#25 Spring Expression Language | SpEL
	- https://www.javatpoint.com/spring-expression-language-tutorial
	- Durgesh Code Package : package com.springcore.SpEL;
		- Supports Parsing and executing expresssion with the help of @Value annotations
		- Spring have some parsers classes which will give the expresssion answer
	- There are 2 ways to resolve the expressions:
	1. Using @Value annotation:
		eg.
			@Value("#{expresssion}")
			@Value("#{3+5}")
			@Value("#{8>6?88:66}")
			@Value("#{}")
			
		- Expression can be Classes, Variable,(static /non static) Methods,Constructors and Objects
			and Symbols like char, numerics, operators, keywords, 
			and special symbols which return a value.
	
		Q) How to invoke static method and variable?
			T(class).method(param)  ->	@Value("#{ T(java.lang.Math).sqrt(144) }")
			T(class).variable		->	@Value("#{ T(java.lang.Math).PI }")

		Q) How to create Object?	->	@Value("#{ new java.lang.String('Sagar Patil')}")
			new Object(value)
	
	2. Using Expression class:
		ExpressionParser parser = new SpelExpressionParser();  
		Expression exp = parser.parseExpression("'Welcome SPEL'.concat('!')");  
		String message = (String) exp.getValue();  
		System.out.println(message);
		

#28 Removing complex XML for Spring Configuration	:
	@Configuration :	
		- used on Java configuration Class
		- Mark class as XML file
	
	@ComponentScan	:
		- set Package (which package to scan?)
		- eg :  @ComponentScan(basePackages = "com.springcore.javaconfig")
			- It will scann the package and check @Controller and some other annotations and create a object for those.
	
	@Bean :
		- on method which return object;
		
	We have 3 types to create Dependency Injection :
		1. XML Config file			:	<bean>
		2. @Component Annotation	:	@Component class Student{}
		3. @Bean Annotation			:	
		
		Practical:
			@Component class Student{}
			class Teacher{ Subject subject;  public Teacher(Subject subject){ this.subject = subject} }
			class Subject{}

			@Configuration 	// mandate
			@ComponentScan(basePackages = "com.springcore.annotationconfig")		// create object for Student
			public class JavaConfig {
				@Bean
				public Subject getSubject() {
					Subject subject = new Subject();
					return subject;
				}

				@Bean(name = {"teacher", "t1", "teacherObj"})
				public Teacher getTeacher() {
					Teacher teacher = new Teacher(getSubject());
					return teacher;
				}
			}
			public class MainRunner {
				public static void main(String[] args) {
					ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
					Employee emp = context.getBean("employee", Employee.class);
					Teacher teacher = context.getBean("getTeacher", Teacher.class);	// "getTeacher", "teacher", "t1", "teacherObj"
				}
			}
