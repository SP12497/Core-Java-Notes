Spring Initializr:
	- start.spring.io
	- Spring Boot Versions:
		- Snapshot (3.0.0 (SNAPSHOT) )(version under development)
		- Mildstone (3.0.0 (M3) )
		- Release  (3.0.0)
	- Project Metadata:
		Group: like package name
		Artifact: like class name
	- Dependencies:
		For rest api: "Spring Web"
Spring Boot 3: required minimum Java v17

---------
What's the most important goat of spring boot?
-> Help you build PRODUCTION-READY apps QUICKLY
	- Build QUICKLY:
		- Spring Initializr
		- Spring Boot Started Projects
		- Spring Boot Auto Configuration
		- Spring Boot DevTools
	- Be PRODUCTION-READY
		- Logging
		- Different Configuration for Different Environment
			- Profiles, ConfigurationProperties
		- Monitoring (Spring Boot Actuator)
		
		------------
@Autowired: has a relationship - by IOC dependency injection.
@ComponentScan("package name") : Scan all the package and search for anotations like, component, primary, autowired....
@Component: If u want to create a bean/object of a class, mark that class a component.
@Primary: if there are more that 1 implementation presend for a reference, mark priority class as @Primary
	-> @Component @Primary class SBI implements RBI {} // this class get precedence
	-> @Component class HDFC implements RBI {}

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}


==========REST API==========
- DispatcherServlet : Front Controller Pattern
	-logs in debug mode ==>>  mapping serverlet: dispatcherServlet urls=[/]

@Controller:
	- handle http request and return view or json.

@RestController:	
	- return only body not view

@RequestMapping(value="/user", method=GET)
@GetMapping("/user")
@GetMapping(value="/user", produces={"application/json"});

/user/{id}
public void fun(@PathVariable String id) {}

/user		// user?id=01
public void fun(@RequestParam String id) {}
public void fun(@RequestParam("id") String userId) {}

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
-------
// @RestControllerAdvice
@ControllerAdvice	// return customized response to all errors
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocaDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(...) {...}
}


public class ErrorDetails {
	private LocaDateTime timestamp;
	private String message;
	private String details;
	
	public ErrorDetails (...) {...}
}