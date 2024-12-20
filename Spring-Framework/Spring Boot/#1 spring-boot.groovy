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
        - spring-boot-starter-web (tomcat-embed-core, jackson-datatype-jsr (JAVA - JSON converter), 
        - spring-boot-autoconfigure (contains configuration of aop, jpa, redis, rest, cache, web, servlet, jdbc) ,... )
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
    - @SpringBootApplication is Combination of: @ComponentScan, @Configuration, @EnableAutoConfiguration

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

application.yml:
    - Spring Boot also supports YAML based properties configurations to run the application.
      Instead of application.properties, we can use application.yml file. 
      This YAML file also should be kept inside the classpath. The sample application.yml file is given below
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
            - Annotate the forward side of a bidirectional relationship in JPA entities. It tells Jackson to 'include' this property during serialization and to manage its reference.
        - @JsonBackReference :
            - Annotate the reverse side of a bidirectional relationship in JPA entities. It tells Jackson to 'ignore' this property during serialization and to use the reference from @JsonManagedReference to prevent circular dependencies and infinite recursion.
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
        1. Provide implementation of "UserDetails":
            class CustomUserDetails implements UserDetails {}
        2. Provide implementation of "UserDetailsService":
            class CustomUserDetailsService implements UserDetailsService {}
        3. Write security configuration class with all configuration.
          A simple class extends WebSecurityConfigurationAdaption   // deprecated in Spring security 5.7.0-M2

          @Override 
          protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll().and().formLogin()
                .loginPage("/signInPageUrl")
                .loginProcessingUrl("/dologin") // form submit url
                .defaultSuccessUrl("/user/index")
                .failureUrl("/login-fail")  // for wrong credentials
                .and.csrf().disabled();
          }
    - Several methods that we use to configure the behavior of the form login
        - loginPage() : the custom login page
        - loginProcessingUrl() : the url to submit the username and password to
        - defaultSuccessUrl() : the landing page after a successful login
        - failureUrl() : the landing page after an unsuccessful login.
    - How to get login details in handlers? ans: using java.security.Principal
        @RequestMapping("/index")
        public String dashboard(Model model, Principal principal) {
            String userName = principal.getName();
            model.addAttribute("user", user);   // access in view
            return "dashboardPage";
        }
    ---------
Spring Security without the WebSecurityConfigurationAdaption:
    - https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    - In Spring security 5.7.0-M2, we deprecated the WebSecurityConfigurationAdaption,
      as we encourage users to move towards a component-based security configuration.
    - use: SecurityFilterChain

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = tru)
    public class SecurityConfig {

        @Autowired private CustomUserDetailsService customUserDetailsService;
        @Autowired private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        @Autowired private JwtAuthenticationFilter jwtAuthenticationFilter;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf().disabled()
                .authorizeRequests()
                ...
            http.authenticationProvider(daoAuthenticationProvider());
            DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
            return defaultSecurityFilterChain;
        }
        @Bean
        public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) thwos Exception {
            return configuration.getAuthenticationManager();
        }

        @Bean 
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(this.customUserDetailService);
            provider.setPasswordEncoder(this.passwordEncoder());
            return provider;
        }
    }

----------
Pagination:
    - Important interfaces:
        Pageable: 
            - org.springframework.data.domain.Pageable
            - It need 2 fields:
                1. current page (page no)
                2. contacts per page (no of records per page)
        Page:
            - org.springframework.data.domain.Page
        class PageRequest:
            - It is implementation of Pageable.
            - here, using of(int page, int size) method, we can set current page and no of records per page.
    - DAO:
        @Query("from Contact as c where c.user.id =:userId")
        public Page<Contact> findContactsByUser(@Param("userId") int userId, Pageable pageable);
    - Controller:
        public ContactController {
            @GetMapping("/show-contacts/{page}")
            public String showContacts(@PathVariable("page") Integer page, Model m, Principal principal) {
                String userName = principal.getName();
                User user = this.userRepository.getUserByUserNam(userName);

                Pageable pageable = PageRequest.of(page, 5);
                Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(), pageable);

                m.addAttribute("contacts", contacts);
                m.addAttribute("currentPage", page);
                m.addAttribute("totalPages", contacts.getTotalPages() );
            }
        }

-----
search functionality for contacts by loggedIn user:
    - repo:
        public List<Contact> findByNameContainingAndUser(String name, User user);
        // eg. name=> per (eg. searching "personal contact"), User=> user object.
    Principal principal; principal.getName(); // fetch username logged in by spring security service.

-------------
IntelliJ spring puglin: Spring Assistant
-----------
JWT Authentication Token:
    - TEST: //https://jwt.io/
    - dependency: 
        1. groupId=io.jsonwebtoken, artifactId=jjwt
        2. if java > v8 : groupId=javax.xml.bind, artifactId=jaxb-api
    - JSON WEB TOKEN used to authenticate users.
    - JWT gains popularity because of token not cookies and no session.
    - When request first time comes to server, then server validates the token and if the validation request fails the request is rejected.
    - Steps: 
        - Check the JWT is well formed or not
        - check signature
        - validate the standard claims
        - Check the client permission.

    Authentication:
        Purpose: Authentication verifies the identity of a user or system.
        How It Works: It involves checking the credentials provided (such as username and password) against a trusted source or database. If the credentials match, the user is authenticated.
        Outcome: After successful authentication, the system confirms that the user is who they claim to be.
        Examples: Passwords, biometrics (fingerprints, facial recognition), and security tokens.
    Authorization:
        Purpose: Authorization determines what an authenticated user or system can do or access.
        How It Works: Once a user is authenticated, the system checks their permissions to decide whether they are allowed to access certain resources or perform specific actions.
        Outcome: After successful authorization, the system grants or denies access based on the users permissions.
        Examples: Access control lists (ACLs), role-based access control (RBAC), and policy-based access control (PBAC).

    1. Write security configuration class with all configuration.
        A simple class extends WebSecurityConfigurationAdaption   // deprecated in Spring security 5.7.0-M2
        @Override 
        protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .csrf().disabled()
            .cors().disabled()
            .authorizeRequests()
            .antMatchers("/token").permitAll()
            .anyRequest().authenticated()
            .sessionManagement().sessionCreationPolicy(sessionCreationPolicy.STATELESS)
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        } 
    2. Create @Component class jwtUtils {} // https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
        


-----------
JWT :


--------
Payment Integration: (RozorPay)
    - dependencies: rozorpay-java, 
    - flow: 
        1. once click on pay: first create order id on backend and return order id to frontend
        2. 
    - code: https://razorpay.com/docs/payments/server-integration/java/payment-gateway/build-integration/

------------
Spring JDBC: check "Spring\Spring Framework\#2 Spring JDBC.groovy"

--------
Creating Automatic API:
    - It eliminated Controller and Service layer. 
      Only User Entity and Repository Layer. And Create automatic api to each Repository classes.
    - dependencies: 
        Rest Repositories: Exposing Spring Data repositories over REST via Spring Data REST.
        Spring Web (for normal web functionality)
        MySQL Driver
        Spring Data JPA
    - application.properties:
        spring.jpa.hibernate.ddl-auto=update
        spring.datasource.url=jdbc:mysql://120.0.0.1:3306/bootjdbc
        spring.datasource.username=root
        spring.datasource.password=root
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        #spring.jpa.show-sql:true
        spring.data.rest.basePath=api/
        server.port=8081
    - Repository:
        @Repository
        public interface BookRepository extends JpaRepository<Book, Integer> {}
        
        @Repository
        @RepositoryRestResource(path="mystudents", collectionResourceRel="Students")    //  http://localhost:8081/api/mystudents    // all list of users will come inside Students
        public interface UserRepository extends JpaRepository<User, Integer> {}
    - Test:
        GET: localhost:8081/api
            this will returns all API linked to Db tables.
        GET: http://localhost:8081/api/      // returns all books
        GET: http://localhost:8081/api/books?size=1
        POST: http://localhost:8081/api/books       // CREATE
            {
                "title": "My Book", "content": "This is content."
            }
        POST: http://localhost:8081/api/books       // UPDATE
            {
                "id": 3,
                "title": "My Book", "content": "This is content."
            }

-------
Top Questions:
1. Change embedded tomcat server port in Spring boot?
    - default 8080, we can change by using the server.port=8081 in the application.properties
2. Can we override or replace the embedded tomcat server in spring boot?
    - ./Questions/Q2.png

-------
Spring Acutators:
    - https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html
    - Spring Boot Actuator is a sub-project of Spring Boot, which provides production-ready features to help you monitor and manage your application.
    - It includes built-in endpoints and metrics that can be used to monitor the health, performance, and other operational aspects of your Spring Boot application.
    - Practical:
        - add dependency:
            spring-boot-starter-actuator
        - will get default endpoint: "/actuator"
            - only limited features are enabled by default
                eg. "/actuator/health"
        - How to expose other feature endpoins of actuators?
            - application.properties:
                management.endpoints.web.exposure.include=*
                management.endpoints.web.health.show-details=always   // /actuator/health
                // management.endpoins.web.base-path=/admin    # change actuator endpoint from "/actuator" to "/admin"
        - How to add Custom Information?
        @Controller
        public class MyDBHealthService implements HealthIndication {        // find in /actuator/health
            public static final String DB_SERVICE = "Database Service";
            public boolean isHealthGood {
                return true;
            }
            @Override
            public Health health() {
                if(isHealthGood())
                    return Health.up().withDetails(DB_SERVICE, "Database service is running up").build();
                return Health.down().withDetails(DB_SERVICE, "Database service is running down").build();
            }
        }


-------------
Unit Testing: (Junit 5 + Assertj Core)
    - dependency:
        spring-boot-starter-test
    - https://junit.org/junit5/docs/current/user-guide/
      https://assertj.github.io/doc/
        - JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
    - Where to write test cases?
        - /src/test/java/com.package/MySpringBootApplication.Tests
        @SpringBootTest
        class MySpringBootApplication {
            private Calculator c = new Calculator();

            @Test
            void testSum() {
                int expectedResult = 17;
                int actualResult = c.doSum(12, 3, 2);
                assertThat(actualResult).isEqualTo(expectedResult);
            }

            @Test       // mocha: test()
            @disabled   // mocha: skip()
            void testCompareNums() {
                Boolean actualResult = c.compareTwoNums(3, 3);
                assertThat(actualResult).isTrue();
            }
        }
    - How to Test Db services?
    @SpringBootTest
    class PersonRepoTest {
        @Autowired
        private PersonRepo personRepo;

        @Test
        void isPersonExistsById() {
            Person p = new Person(11, "Sagar", "NDB");
            personRepo.save(p);
            Boolean actualResult = personRepo.isPersonExistsById(123);  // 
            assertThat(actualResult).isTrue();
        }

        @AfterEach
        void tearDown() {
            personRepo.deleteAll();
        }

        @BeforeEach
        void setUp() {}
    }
    - How to change db connections for test cases?
        - You can add custom configuration for test cases:
        - /src/test/resources/application.properties
        - Here, you can add H2 in memory database configuration for testing purpose.
    - Mockito: Used to mock other services:
        - Eg. Write a test cases for PersonService, Without connecting to database by PersonRepo.
            So here, we have to mock PersonRepo.
        - ServiceClass:
            @Service
            public class PersonService {
                @Autowired
                private PersonRepo personRepo;

                public PersonService(PersonRepo personRepo) {   // constructor
                    this.personRepo = personRepo;
                }

                public List<Person> getAllPerson() {    // get all persons from DB
                    return this.personRepo.findAll();
                }
            }
        - Mockito Test:
            @ExtendWith(MockitoExtension.class)
            public class PersonServiceTest {
                @Mock   // @Autowired: will give actual DB connection object.
                PersonRepo personRepo;

                // @Autowired: internally PersonService has Autowired mocked PersonRepo. 
                private PersonService personService;

                @BeforeEach
                void setUp() {
                    this.personService = new PersonService(this.personRepo);
                }

                @Test
                void getAllPerson() {
                    personService.getAllPerson();
                    verify(personRepo).findAll();
                }
            }

----------
MongoDB Connections:
    - dependency:
        - spring-data-mongodb
    - application.properties:
        spring.data.mongo.host=localhost
        spring.data.mongo.port=27017
        spring.data.mongo.database=mydb
    - Document: (Entity in relational DB)
        @Document(collection="students")
        public class Student {
            private int id;
            private String name;
            private String city;
            private String college;
        }
    - Repo:
        public interface StudentRepository extends MongoRepository<Student, Integer> {}
    - Controller:
        @RestController
        @RequestMapping("/student")
        public class MyController {
            @Autowired
            private StudentRepository studentRepository;

            @PostMapping("/")
            public ResponseEntity<?> addStudent(@RequestBody Student student) {
                Student s1 = this.studentRepository.save(student);
                return ResponseEntity.ok(s1);
            }

            @GetMapping("/")
            public ResponseEntity<?> addStudent(@RequestBody Student student) {
                return ResponseEntity.ok(this.studentRepository.findAll());
            }
        }

------
Profiles:
    - It is a way to segregate parts of your application configuration and make it only available in certain environments.
    - manage environment based configurations
    - Practical:
        - application.properties:
            spring.profiles.active=dev     // higher priority, so port will be 9000
            server.port:8080        // Default
        - application-dev.properties 
            server.port:9000        // for dev environent // 
        - application-prod.properties 
            server.port:9090

-----------
Content-Negotiation:
    - consume and produce XML Data.
    - by default spring boot will consume and produce JSON
    - dependency: jackson-dataformat-xml
    - Code:
        @Configuration
        public class ContentConfig implements WebMvcConfigurer {
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.favorParameter(true)
                        . parameterName('mediaType')
                        .defaultContentType(MediaType.APPLICATION_JSON)
                        .mediaType("json", MediaType.APPLICATION_JSON)
                        .mediaType("xml", MediaType.APPLICATION_XML)
            }
        }
    - Test:
        http://localhost:8080/api/users                 =>  Default JSON response
        http://localhost:8080/api/users?mediaType=json  =>  JSON response
        http://localhost:8080/api/users?mediaType=xml   =>  XML response