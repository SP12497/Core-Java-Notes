============================
1. Singleton:
    - Default Scope
    - Only 1 instance created per IOC.
    - Eagerly initialized by IOC (means at the time of application startup, object get created). 

@RestController
public class TestController1 {  // TestController1 hashCode: 111
    @Autowired User user;   // User hashCode: 222
}

@RestController
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TestController2 {  // TestController1 hashCode: 333
    @Autowired User user;   // User hashCode: 222
}

@RestController
@Scope("singleton")
public class User {}    // User hashCode: 222

============================
2. Prototype:
    - Each time new Object is created.
    - Its Lazily initialized, means when object is created only when its required.
@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
public class TestController1 {   // TestController1 hashCode: 333
    @Autowired User user;       // User hashCode: 444
    @Autowired Student student; // Student hashCode: 111
}

@Component
@Scope("prototype")
public class User {     // User hashCode: 222 | 444
    @PostConstruct
    public void init() {
        System.out.println("User object hashcode" + this.hashCode());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User object hashcode" + this.hashCode());
    }
}

@Component  // singleton : Eager: at start of application
public class Student {  // Student hashCode: 111
    @Autowired
    User user;          // User hashCode: 222
}

============================
3. Request:
    - New Object is created for each HTTP request.
    - Lazily initialized.

-- Example 1:
    - when someone hit http url
@RestController
@Scope("request")
public class TestController1 {   // TestController1 hashCode: 111
    @Autowired User user;       // User hashCode: 222       // per request 1 object.    // New obj will get created in each HTTP request
    @Autowired Student student; // Student hashCode: 333
}

@Component
@Scope("request")
public class User {     // User hashCode: 222
    @PostConstruct
    public void init() {
        System.out.println("User object hashcode" + this.hashCode());
    }
}

@Component
@Scope("prototype")
public class Student {  // Student hashCode: 333
    @Autowired
    User user;          // User hashCode: 222
}

--------
-- Example 2:
@RestController
@Scope("singleton")     // Eager initialized
public class TestController1 {
    @Autowired User user;       // User not present at the time of spring boot IOC container application is staring and creating testController1 
}

@Component
@Scope("request")       // Lazy initialized
public class User {}

-> Error creating bean with name testController1: Unsatisfied dependency
- Spring created Request scope bean only when there is active HTTP request present.
  Since singleton, is eagerly initialized, there is no active HTTP request present in
  current thread. So it wont create a bean for User.
--------
-- Example 3:
    - Use proxymode to resolve Example 2.
@RestController
@Scope("singleton")     // Eager initialized
public class TestController1 {
    @Autowired User user;
}

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)       // proxyMode: Create proxy/dummy object for TestController1's User
public class User {}


============================
4. Session:
    - New object is created for each HTTP session.
    - Lazily initialized.
    - When user accesses any endpoint, session is created.
      Remain active, till it does not expire.

@RestController
@Scope("session")     // Lazy initialized
public class TestController1 {      // Each session: Many HTTP Request: Contains Single Object.
    @Autowired User user;
}

@Component      // singleton: Early initialization
public class User {}