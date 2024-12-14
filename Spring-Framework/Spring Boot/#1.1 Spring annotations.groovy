@SpringBootApplication: 
    Marks the main class of a Spring Boot application,
    combining @Configuration, @EnableAutoConfiguration, and @ComponentScan.

@Bean:
    - Indicates that a method produces a bean to be managed by the Spring container.
    - Used inside @Configuration class
    - apply on method to tells that, Object creation by method should manage by Spring IOC Container. 
    - help to get auto injection.

@Configuration: 
    - Indicates that a class provides bean definitions.
    - Indicates that a class declares one or more @Bean methods.
    - provide java based bean creation configuration:
    - ConfigFile:
        @Configuration
        @ComponentScan(basePackages = {"myAnotherPackage" , "myAnotherPackage2"})   // Optinal: if you want to scan multiple packages
        public class MyConfig {
            @Bean 
            public Student getStudent() {   // Bean will be created by method name
                return new Student();
            }

            @Bean
            @Lazy
            public Student createStudent() {
                return new Student();
            }
        }
    - Test:
        @Autowired 
        @Qualifier("getStudent")
        Student student; //  IOC will give object automatic
        student.getName();

@Autowired: 
    - Performs dependency injection on fields, constructors, or methods.
    - Search for object/bean which matching Student type. if found, then inject automatically.
    - @Autowired @Qualifier("student2") Student student; :
        - if multiple beans/objects are presents for same type. Then pass bean/object name.
    - @Lazy: 
        - Lazy initialization:
            - create object when required. Do not create at start of application.

@Component: 
    - Indicates that a class is a Spring component, allowing auto-detection and auto-registration.
    - tells spring container to manage object lifecycle.
    - used to create object automatically.
    - @Controller:
        - Presentation Layer
        - @Component + MVC Controller
    - @Service:
        - Service Layer
        - Indicates that a class is a service component.
        - @Component + Business Logic
    - @Repository:
        - DAO Layer (Data Access Object)
        - Indicates that a class is a repository component (typically for data access).
        - @Component + Data Access Logic

@ComponentScan:
    - Scan provided package and its sub packages and check for configuration like @Component

@EnableAutoConfiguration: 
    - Enables Spring Boot's auto-configuration mechanism.
    - Automatically configure your Spring application based on the JAR dependencies you added in the project.
    - It is generally placed on the main class of the application.
    - It tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
    - For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

--------------------------
@RestController: 
    - Indicates that a class is a REST controller,
    - combining @Controller and @ResponseBody.
    - used to create REST API
    - means class always return Response  ie @ResponseBody, not view ie only @Controller

@RequestMapping: Maps web requests to handler methods.

@Value: Binds a property value to a field or method parameter.

@Transactional: Marks a method, class, or interface as transactional.
    - used to manage transaction in spring boot application.
    - @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    - @Transactional(rollbackFor = Exception.class)
    - @Transactional(noRollbackFor = Exception.class)
    - @Transactional(timeout = 10)
    - @Transactional(value = "transactionManager")
    - @Transactional(rollbackFor = Exception.class, noRollbackFor = ArithmeticException.class)

@PathVariable: Binds a method parameter to a placeholder in the request URL.
@RequestParam: Binds a method parameter to a query parameter in the request URL.
@ModelAttribute: Binds a method parameter or method return value to a named model attribute. 
    - used to read data from view to controller, once form is submitted
@ResponseBody: Indicates that a method return value should be bound to the web response body.
@RequestBody: Binds the body of a web request to a method parameter.
@RequestHeader: This annotation is used to bind HTTP headers to method parameters in the controller method.

@ResponseStatus: This annotation is used to specify the HTTP status code to be returned by the controller method.
@ControllerAdvice: This annotation is used to define global exception handlers.
@RestControllerAdvice: This annotation is used to define global exception handlers for REST controllers.
@ExceptionHandler: Handles exceptions thrown by controller methods.
@CrossOrigin: Configures cross-origin resource sharing (CORS) for web controllers.

@Valid: Indicates that a method parameter should be validated.

@EnableScheduling: Enables scheduling of @Scheduled methods.
@Scheduled: This annotation is used to indicate that a method parameter should be validated. It is commonly used in conjunction with Bean Validation annotations such as @NotNull, @Size, etc.

@EnableAsync: Enables asynchronous method execution.
@Async: Indicates that a method should be executed asynchronously.

@Cacheable: Marks a method for caching results.
@CacheEvict: Marks a method for evicting/removing entries from the cache.

=============
Code:
======

@Configuration
@ComponentScan(basePackages = {"myAnotherPackage" , "myAnotherPackage2"})
public class MyConfig {
    @Bean 
    public Student getStudent() {
        return new Student("Suraj");
    }

    @Bean
    @Lazy
    public Student createStudent() {
        return new Student("Sagar");
    }
}

----
@Controller
public class MyController {
    @Autowired
    @Qualifier("student2")
    private Student student;

    @RequestMapping(value="/home/{sId}", method = RequestMethod.POST)
    @ResponseBody
    public Student home(@RequestBody Student stud, 
                        @PathVariable("sId") Integer studId,
                        @RequestParam("email") String email) {  // <input name="email" />
        return this.student;    // return student object as json response
    }


}