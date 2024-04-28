What is Spring Boot?
    - Spring boot is module of Spring from which we speed up the development.
    - Spring Boot makes it easy to create stand-alone production-grade spring based applications that you can "just run"
    - It provides an easier and faster way to set up, configure and run both simple and web-based applications.
    =>  Spring Framework + Embedded Servers - Configurations = Spring Boot
    - Convention over Configuration software design style.
    - It decreases the effort of the developer.
    - Scan the class path and find the dependency, it will automatically configure the things.

Advantages of Spring Boot:
    - It creates stand-alone Spring applications that can be started using "Java -jar".
    - Embedded Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
    - Provide opinionated "starter" dependencies to simplify your build configuration
    - Automatically configure Spring and 3rd party libraries whenever possible.
    - Provide production-ready features such as metrics, health checks, and externalized configuration
    - Absolutely no code generation and no requirement for XML configuration.

How Spring Boot magic works?
    - Starter POM:
        - spring-boot-starter-web (tomcat-embed-core, jackson-datatype-jsr (JAVA - JSON converter), spring-boot-autoconfigure (contains configuration of aop, jpa, redis, rest, cache, web, servlet, jdbc) ,... )
        - spring-boot-starter-data-jpa (contains, spring-orm, hibernate-core,...)
    - When we add starter jars, then it pull all the jars
        - JAR contains "META-INF/spring.factories"
        - This spring.factories all configuration for the specific JAR.
        - The configuration should be enabled or disabled based on some condition. 
            - @ConditionalOnBean(DataSource.class)
            - @ConditionalOnClass(JpaRepository.class)
            - @ConditionalOnMissingBean({JpaRepositoryFactoryBean.class, ...})
            - @ConditionalOnProperty(prefix="spring.data.jpa.repositories", name="enabled", ...)
            public class JpaRepositoryAutoConfiguration {}

How to start with Spring Boot:
    1. Create a maven project and add starter dependencies
    2. Use Spring Initializr (start.spring.io)
    3. Use IDE like STS
    4. Spring Boot command line interface (CLI).


@SpringBootApplication
    - Spring application start from this class.
    - Contains: @ComponentScan, @Configuration, @EnableAutoConfiguration


View Practical In Boot:
    - Dependency: tomcat-embed-jasper
    - application.properties:
        spring.mvc.view.prefix=/views/
        spring.mvc.view.suffix=.jsp
    - View: src/main/webapp/views/home.jsp
    - Controller:   
        @RequestMapping("/home") public String myHome() { return "home"; }

application.properties:
    - It is a central configuration file where you can specify various settings for your application.
    - It allows you to configure properties such as database connection details, server port, logging levels, and other application-specific settings.
    - These properties are read by the Spring framework and used to customize the behavior of your application at runtime without needing to modify the source code.
    - application.properties file:
        server.port = 8081  // by default 8080 //  java –jar <JARFILE> --server.port=8080
        spring.application.name = demoservice

YAML File
 - Spring Boot supports YAML based properties configurations to run the application. Instead of application.properties, we can use application.yml file. This YAML file also should be kept inside the classpath. The sample application.yml file is given below
- application.yml file:
    spring:
        application:
            name: demoservice
    server:
        port: 9090
-----------
JPA Repository: 
    - #1.2.1 Spring JPA.txt
    - #1.2.2 REST API with JPA practical.txt
-----------
Spring Boot DevTools:
    - dependency: spring-boot-devtools
    - DevTools stands for Developer tools.
    - The aio of the module is to try and improve the development time while working with the spring boot application.
    - Spring Boot DevTools pick up the changes and restart the application.
    - Dependency: spring-boot-devtools  
    - Features:
        - Property Defaults (remove caching for testing)
        - Automatic Restart
        - Live Reload
        - Remote Application, etc
--------

API (Application Programming Interface):
    - dependency: (spring-boot-starter-web)
    - It is a set of rules that allow programs to talk to each other. The developer creates the API on the server and allows the client to talk to it.
    - A set of constraints to be used for creating Web services.
    - Constrains:
        - Client-Server
        - Stateless (server wont store client info)
        - Cacheable
        - Layered
    - The important methods of HTTP are: GET, PUT, POST, DELETE
    - HTTP also defines the following standard status code:
        400: Bad Request
        404: Resource not found
        200: Success
        201: Created
        401: Unauthorized
        500: Server error 

How to create REST API?
    - There are two ways:
        1. Using RestController:
            - Used when Controller class handler's only returns response body, not views.
            @RestController:
            public class BookController {
                @RequestMapping(value="/books", method=RequestMethod.GET)
                public String getBooks() { }
            }
        2. Using ResponseBody:
            - Used when Class contains handlers which returns views and response body.
            @Controller:
            public class BookController {
                @ResponseBody
                @RequestMapping(value="/books", method=RequestMethod.GET)
                public String getBooks() { return "bookname"}
            }



--------
Bidirectional Relationships:
    - below annotations provided by the Jackson library, which is commonly used for JSON processing in Java applications, including those built with Spring Boot.
        - @JsonManagedReference
            - Annotate the forward side of a bidirectional relationship in JPA entities. It tells Jackson to include this property during serialization and to manage its reference.
        - @JsonBackReference :
            - Annotate the reverse side of a bidirectional relationship in JPA entities. It tells Jackson to ignore this property during serialization and to use the reference from @JsonManagedReference to prevent circular dependencies and infinite recursion.
    - eg.
        @Entity public class Book {
            @Id int bookId;
            @JsonManagedReference OneToOne Author author;
        }

        @Entity public class Author {
            @Id int authorId;
            @JsonBackReference @OneToOne Book book;     // stop to jumb,
        }
        - Without annotions: This will avoid nesting => {bookId:"", address: {book:{address:{{...}}}}}
        - With Annotation: {bookId:"", address: {authorId:"" } }

----
File Uploading: using interface MultipartResolver
    - application.properties:
        spring.servlet.multipart.enable=true    # enabling multipart uploads
        spring.servlet.multipart.max-file-size=100MB
        spring.servlet.multipart.file-size-threshold=1KB
    - RestController:
        @Postmapping("/upload-file) 
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            syso(file.getOriginalFilename());
            syso(file.getSize());
            return ResponseEntity.ok("working");
        }
    - Postman:
        POST: localhost:8080/upload-file
        body->form-data => 
            key:File=file | value=select image


---
Server side form validation:
    - dependency: 
        Bean validation API (artifact: validation-api)  // this is just a specifications
        Hibernate Validator Engine Relocation Artifact (artifact: hibernate-validator)
    - Bean:
        public class LoginData {
            @NotBlank(message = "User name can not be empty!!")
            @Size(min=3, max=12, message = "User name must be between 3-12 characters!!")
            private String userName;
            
            // @Pattern(regexp = "add email regular expression here", message = "Invalid Email!!")
            @Email(regexp = "add email regular expression here")    // have default message
            private String email;

            @AssertAgreed(message = "Must agree terms and conditions !!")
            private boolean agreed; // form check box check
        }
    - Controller: 
        @PostMapping("process")
        public String processForm(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult result) {    
            if(result.hasErrors()) {
                syso(result);
                return "form";
            }
            syso(loginData)
            return "successPage";
        }
        // @Valid annotation will validate the response before call comes in handler
        // all errors will come inside BindingResult


---
Spring Boot Security:
    - dependency: spring-boot-starter-security:
    - Practical: 3 steps:
        1. Create: class CustomUserDetails implements UserDetails
        2. Create: class CustomUserDetailsService implements UserDetailsService