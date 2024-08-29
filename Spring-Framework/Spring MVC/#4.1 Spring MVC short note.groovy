FrontController / DispatcherServler : 
    - Single point of entry for all requests
    - Centralizes request handling
Controller:
    - Handles requests
    - Processes requests
    - Returns model and view
ViewResolver: 
    - Maps logical view names to actual views
    - Resolves logical view names to actual views
ViewTemplate:
    - Renders the view
    - Returns the response

RequestMapping:
    - @RequestMapping("/home")
    public String home() {
        return "index";
    }
    - @RequestMapping(name="/home", method=RequestMethod.GET, produces="application/json", consumes="application/json")

    - @PathVariable:
        // Input: localhost:8080/home/Sagar/India
        @RequestMapping("/home/{name}/{country}")
        public String home(@PathVariable("name") String name, @PathVariable("country") String country) {
            return "index";
        }
    - @RequestParam:    // Query parameter
        // Input: localhost:8080/home?name=Sagar
        @RequestMapping("/home")
        public String home(@RequestParam("name") String name) {
            return "index";
        }
    - @RequestBody: :
        // Payload: {"name": "Sagar", "country": "India"}
        @RequestMapping("/home")
        public String home(@RequestBody User user) {
            return "index";
        }
    // - RequestAttribute:
    //     // Input: localhost:8080/home
    //     @RequestMapping("/home")
    //     public String home(@RequestAttribute("name") String name) {
    //         return "index";
    //     }
    - @ResponseBody:
        // Don't look for view. Directly return response
        @RequestMapping("/home")
        @ResponseBody
        public String home() {
            return "My name is Sagar";
        }
    - @ResponseStatus:
        - This annotation is used to specify the HTTP status code to be returned by the controller method.
        @RequestMapping("/home")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public String home() {
            return "index";
        }
    - @ExceptionHandler:
        - Handles exceptions thrown by controller methods.
        @ExceptionHandler(Exception.class)
        public String handleException(Exception e) {
            return "error";
        }
    - @RequestHeader:
        - This annotation is used to bind HTTP headers to method parameters in the controller method.
        @RequestMapping("/home")
        public String home(@RequestHeader("User-Agent") String userAgent) {
            return "index";
        }
    - @CrossOrigin:
        - This annotation is used to enable Cross-Origin Resource Sharing (CORS) for a specific controller or controller method.
        @CrossOrigin(origins = "http://localhost:4200")     // Accept Request from Angular Application.
        @RequestMapping("/home")    // url: http://localhost:8080/home
        public String home() {
            return "index";
        }
    - @CookieValue:
        - This annotation is used to bind HTTP cookies to method parameters in the controller method.
        @RequestMapping("/home")
        public String home(@CookieValue("JSESSIONID") String sessionId) {
            return "index";
        }
    - @SessionAttributes:
        - This annotation is used to specify the attributes that should be stored in the session.
        @Controller
        @SessionAttributes("name")
        public class MyController {
            @RequestMapping("/home")
            public String home() {
                return "index";
            }
        }
    - @Valid:
        - This annotation is used to validate the request body in the controller method.
        @RequestMapping("/home")
        public String home(@Valid @RequestBody User user) {
            return "index";
        }
    - Scheduling:
        - The @Scheduled annotation in Spring Boot is used to schedule a method to be executed at a fixed rate, with a fixed delay, or according to a cron expression. 
          When you use @Scheduled, you must first enable scheduling in your application by adding the @EnableScheduling annotation to a configuration class.
        - @EnableScheduling:
            - This annotation is used to enable scheduling in the application.
            @Configuration
            @EnableScheduling
            public class SchedulingConfig {
                // Additional configuration if needed
            }
            - OR you can place @EnableScheduling on your main application class:
            @SpringBootApplication
            @EnableScheduling
            public class MyApplication {
                public static void main(String[] args) {
                    SpringApplication.run(MyApplication.class, args);
                }
            }
        - @Scheduled:
            - This annotation is used to schedule a method to be executed at a fixed rate.
            @Component
            public class ScheduledTasks {
                @Scheduled(fixedRate = 5000)  // Execute every 5 seconds
                // @Scheduled(fixedDelay = 5000, initialDelay = 2000)  // Execute 5 seconds after the previous execution completes, with an initial delay of 2 seconds
                public void scheduleTask() {
                    System.out.println("Task executed at fixed rate.");
                }
            }
    - Async:
        - @EnableAsync:
            - This annotation is used to enable asynchronous processing in the application.
            @EnableAsync
            @Configuration
            public class AppConfig {
                // Configuration for async processing if needed
            }
        - @Async:
            @Service
            public class MyService {
                @Async
                public void asyncTask() {
                    System.out.println("Task executed asynchronously.");
                    // Simulate a long-running task
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Task was interrupted.");
                    }
                    System.out.println("Async task completed.");
                }
            }    

---
Pass data from Controller to View:
        - Model:
            @RequestMapping("/home")
            public String home(Model model) {
            model.addAttribute("name", "Sagar");
            List<String> frnds = new ArrayList<String>();
            frnds.add("Yogesh");
            frnds.add("Nilesh");
            frnds.add("Amol");
            model.addAttribute("friends", friends);
                return "index";
            }
        - ModelAndView:
            @RequestMapping("/home")
            public ModelAndView home() {
                ModelAndView modelAndView = new ModelAndView(); // Note: Model we have to pass as param but not ModelAndView

                modelAndView.setViewNam("index");

                modelAndView.addAttribute("name", "Sagar");
                List<String> frnds = new ArrayList<String>();
                frnds.add("Yogesh");
                frnds.add("Nilesh");
                frnds.add("Amol");
                modelAndView.addAttribute("friends", friends);
                return modelAndView;
            }
        -- Display:
            JSP :   
                <% 
                    String name = (String) request.getAttribute("name");
                    List<String> friends = request.getAttribute("friends");
                %>
        - @ModelAttribute:
            -  @ModelAttribute : performs 3 tasks => 1.@RequestParam 2.user.setEmail(email); 3. model.addAttribute
            - @ModelAttribute User user :  // helps to remove the boilerplate code of creating User object and setting the data in it.
                1. Take data from request and bind to User object 
                2. Add/bind User object to Model object
                3. Return User object to view as Model.
            - Example:
                @Controller
                public class ContactController {
                    @ModelAttribute   // share Model data with all RequestMapping methods in this ContactController class
                    public void commonDataForModel(Model m){  // This function will get call before @RequestMapping methods and will add data to Model object.
                        m.addAttribute("Header1", "My Header1");
                        m.addAttribute("Header2", "My Header2");
                    }
                    
                    @RequestMapping("/signup")
                    public string showForm() {
                        return "signUpPage";  // In this page, will get Header1 and Header2 properties
                    }
                    
                    @RequestMapping(path="/processform", method=RequestMethod.POST)
                    public String redirectFormByClassModelAttribute(@ModelAttribute User user, Model model) {
                        return "successPage"; // In this page, will get user, Header1 and Header2 properties
                    }
                }
---
RedirectView:
    1. HttpServletResponse
        @RequestMapping("/one")     // localhost:8080/one
        public String first(HttpServletResponse response) {  // not recommended in Spring
            response.sendRedirect("two");       // redirect to "localhost:8080/two"
            return "";
        }
    2. redirect prefix
        @RequestMapping("/one")
        public String first() {
            return "redirect:/two"; // check redirect keywork and call path /two
        }
        @RequestMapping("/two") 
        public String second() { return "signUpPage"; }
    3. RedirectView:
        @RequestMapping("/one")
        public RedirectView first() {
            RedirectView redirectView = New RedirectView();
            redirectView.setUrl("two");
            // redirect.setUrl("https://www.google/com");
            return redirectView; // check redirect keywork and call path
        }
    4. RedirectAttributes:
        // RedirectAttributes is used to pass data from one request to another request
        @RequestMapping("/one")
        public String first(RedirectAttributes redirectAttributes) {
            redirectAttributes.addFlashAttribute("name", "Sagar");
            return "redirect:/two";
        }
        @RequestMapping("/two")
        public String second(@ModelAttribute("name") String name) {
            return "signUpPage";
        }
    5. 4. In JSP page:
        <% response.sendRedirect("two"); %>
        <% response.sendRedirect("https://www.google.com"); %>

---
interface MultipartResolver: 
    - Resolves multipart requests
    - helps in uploading files
    @RequestMapping(value="/uploadimage", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("profile") CommonMultipartResolver file) {
      syso(file.getSize());
      file.getContentType(); // getName(), getOriginalFilename(), getStorageDescription, 
      byte[] data = file.getBytes();
      String path = s.getServletContext().getRealPath("/")+ "WEB-INF"+ File.separator + "image" + file.separator + file.getOriginalFilename();
      syso(path);   // D:\projects\SpringMVC\src\main\webapp\WEB-INF\image\image.jpg
      FileOutputStream fos = new FileOutputStream(path);
      fos.write(data);
      fos.close();
      return "fileSuccessStatusPage"
    }

---
- Exception Handling:
    - 3 Important Annotations:
        - @ExceptionHandler
        - @ControllerAdvice
        - @ResponseStatus
        
        - @ExceptionHandler(Exception.class)
            public String handleException(Exception e) {
                return "error";
            }
        - @ControllerAdvice
            public class GlobalExceptionHandler {
                @ExceptionHandler(Exception.class)
                public String handleException(Exception e) {
                    return "error";
                }
            }
        - @ResponseStatus(HttpStatus.ACCEPTED)
            public String handleException(Exception e) {
                return "error";
            }

        - There are 2 ways to handle exceptions in Spring MVC:
            1. For single Controller:
              @Controller class MyController {
                // ... handler methods
                
                @ExceptionHandler public String allExceptionHandlers(Model model) { model.addAttribute("message", "exception occured"); return "error_page"} // Handle all types of exception
                @ExceptionHandler({NullPointerException.class, NumberFormatException.class}) public String allExceptionHandlers() { return "error_page"} // handler only 2 types of exception.
                @ExceptionHandler({value=NullPointerException.class}) public String allExceptionHandlers() { return "error_page"} // handler only NullPointerException exception.
                @ExceptionHandler({value=NumberFormatException.class}) public String allExceptionHandlers() { return "error_page"} // handler only NumberFormatException exception.
          
                @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
                @ExceptionHandler(value= Exception.class)
                public String allExceptionHandlersWithStatus(Model model) { model.addAttribute("message", "exception occured"); return "error_page"} // Handle Exception and all child Exception classes.
              }
            2. For all controllers:
              @ControllerAdvice   // if any error occurs in any Controller, this advice class will get called automatically
              class MyGlobalExceptionHandler {
                @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
                @ExceptionHandler({value=NullPointerException.class}) public String nullPointerHandler(Model model) { model.addAttribute("message", "Null pointer exception occured"); return "error_page"} // handler only NullPointerException exception.
          
                @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
                @ExceptionHandler({value=NumberFormatException.class}) public String numberFormatHandler(Model model) { model.addAttribute("message", "Number format exception occured"); return "error_page"} // handler only NumberFormatException exception.
          
                @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
                @ExceptionHandler(value= Exception.class)
                public String allExceptionsHandler(Model model) { model.addAttribute("message", "exception occured"); return "error_page"} // Handle Exception and all child Exception classes.
              }
---
interceptor: (#4.3.1 MVC Interceptor.png)
    - Interceptor is used to perform operations before and after the request is processed.
    - Class and Methods:
        class HandlerInterceptorAdaptor implements HandlerInterceptor
        - boolean preHandle(): Before the actual handler is executed
            - return true: continue with the handler execution
            - return false: stop the execution of handler
        - void postHandle(): After the handler is executed
            - Allows to manipulate the model
        - void afterCompletion(): After the request is completed
            - Allows to perform resource cleanup
    - Implementation:
        @Component
        // class MyInterceptor implements HandlerInterceptor    // Implement all 3 methods
        public class MyInterceptor extends HandlerInterceptorAdapter {
            private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
        
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String name = request.getParameter("user");
                if (name != null && name.startsWith("d")) {
                    response.setContentType("text/html");
                    response.getWriter().println("<h1>Invalid name.. Name should not start with 'd'.</h1>");
                    return false;  // Prevents the request from proceeding
                }
                return true;  // Allows the request to proceed
            }
        
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
                                ModelAndView modelAndView) throws Exception {
                if (modelAndView != null) {
                    modelAndView.addObject("name", "Sagar");
                }
            }
        
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                        Exception ex) throws Exception {
                String requestURI = request.getRequestURI();
                String method = request.getMethod();
                
                if (ex != null) {
                    logger.error("Request to {} failed with exception: {}", requestURI, ex.getMessage());
                } else {
                    logger.info("Request to {} completed successfully with status {}", requestURI, response.getStatus());
                }
        
                // Optional: Cleanup or additional logic after request completion
            }
        }
    - Register Interceptor:
        @Configuration
        public class WebConfig extends WebMvcConfigurerAdapter {
            @Autowired
            private MyInterceptor myInterceptor;
        
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(myInterceptor);
            }
        }
    
---
Catching in Spring Boot:
    - @EnableCaching: Enable caching in the application
    - @Cacheable: Cache the result of a method
    - @CacheEvict: Remove the cached result of a method

    - 
        @Configuration
        @EnableCaching
        public class CacheConfig {
            // Additional cache configurations can be added here
        }
    - 
        @Service
        public class ProductService {

            @Cacheable("products")
            public Product getProductById(Long id) {        // If the product with the given ID is already cached, the method will not be executed and the cached product will be returned.
                // Simulate a time-consuming operation, like a database call
                System.out.println("Fetching product with ID: " + id);
                return new Product(id, "Sample Product");
            }

            @CacheEvict(value = "products", key = "#id")
            public void updateProduct(Long id, Product product) {   // This method will update the product in the database and then evict the cached product with the given ID.
                // Update the product in the database or any other data store
                System.out.println("Updating product with ID: " + id);
            }

            @CacheEvict(value = "products", allEntries = true)
            public void clearCache() {  // This will clear all entries in the "products" cache
                // This will clear all entries in the "products" cache
                System.out.println("Clearing all product cache entries.");
            }
        }

``` 
