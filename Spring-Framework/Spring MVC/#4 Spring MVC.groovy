What is Spring MVC: (Model-View-Controller)
    - A spring MVC is a sub framework of Spring framework which is used to build a web application.
    - It is build on the top of Servlet API
    - It follows the MVC design pattern
    - It implements all the basic features of a core spring framework
      like IOC (Inversion of control), Dependency Injection.
    
Why Spring MVC:
    - Separate each role model, view and controller.
    - Powerful configuration
    - It is sub framework of Spring framework. Use of Spring core features like IOC etc.
    - Rapid application development.
    - it is flexible, easy to test and much features.

MVC Design pattern:
    - Model : Data
      View: Presents data to user (html, jsp, etc)
      Controller: Interface b/w model and view. (Servlet, etc)
    - Way to organize the code in our application.

Problem without MVC design pattern:

---------
Configure Tomcat and Eclipse:
  - download tomcat: eg. tomcat 9
  - Eclipse: Window-> Preferences:
    - Server -> Runtime Environments:
      - Add: Apache Tomcat v9.0: select tomcat 9 home directory by zip extracted folder
    - Validate:
      window-> show view ->  server  : here, we can start tomcat

Create Project:
  - New Maven Project: Internal: quickstart 
  - suppose project name is: springmvc
  - if project showing Red cross, Set Tomcat to this project:
    - Right click on Project:
      - Build Path: Java Build Path: Libraries:
        - Add Library: Server Runtime: Apache Tomcat 9.0  // Now tomcat dependency added in depedency folder
  - Add pom dependency: spring-webmvc  // once added, update Maven Project
  - RUN: localhost:8080/<project-name>

Sprinv MVC application creation steps: (refer: "#4.1 MVC Working.png")
  1. Configure the Dispatcher Servlet in web.xml
  2. Create Spring configuration file
  3. Configure View Resolver
  4. Create Controller
  5. Create a View to show (page)

Step 1: Configure the dispatcher servler in web.xml : 
  - src/main/java/webapp/WEB.xml:
    <web-app> 
        <!-- Configure dispatcher Servler-->
        <servlet>
            <servlet-name>spring</servlet-name> <!-- any name -->
            <servlet-class>org.springframework.web.servler.DispatcherServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>spring</servlet-name> <!-- Dispatcher servlet name -->
            <url-pattern>/</url-pattern>    <!-- '/' means all, '/student' means only start with student path related-->
        </servlet-mapping>
    </web-app>

Step 2: Create Spring configuration file:
  - create xml file: WEB-INF/<Dispatcher servlet name>-servlet.xml
    - WEB-INF/spring-servlet.xml: 
      <?xml version="1.0" encoding="UTF-8" ?>
      <beans	 	xmlns="http://www.springframework.org/schema/beans"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:context= "http://www.springframework.org/schema/context"
						xmlns:p= "http://www.springframework.org/schema/p"
						xmlns:c="http://www.springframework.org/schema/c"
						xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans.xsd
						http://www.springframework.org/schema/context
						http://www.springframework.org/schema/context/spring-context.xsd">

            <context:component-scan base-package="springmvc.controller" /> <!-- for automatic @Controller class object creation-->
            <!-- Step 3: Create View Resolver-->
            <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" name="viewResolver">
                <properties name="prefix" value="/WEB-INF/views"></properties>
                <properties name="suffix" value=".jsp"></properties>
            </bean>
			</beans>

Step 4. Create Controller:
  - package: src/main/java/<base package>/controller
  @Controller
  public class HomeController {
    @RequestMapping("/home")
    public String home() {
      return "index";  // /WEB-INF/views/index.jsp // 
      // String "index" will go to FrontController ie dispatcher servlet
      // then, dispatcher servlet will send to View Resolver and View Resolver will retun the View ie index.jsp page.
      // then, FrontController will return the index.jsp as response.

    }
  }

Step 5. Create a View to show (page):
  - /WEB-INF/views/index.jsp
    - Write some jsp/html code

Run and Call: localhost:8080/springmvc/home


----------------------------------
# Sending data from Controller to View.
  - When controller is returning the view name, same time we can return the data.
    View Resolver will bind that data to view.
  - There are 2 ways we can pass the data: (Angular: One way data binding | same in spring: controller to view)
    1. Model:
      - addAttribute(String key, Object value);
      // return "viewName"; // controller function
    2. ModelAndView:
      - addObject(String key, Object value);
      - setViewName(String viewName) : void
      // return modelAndView; // controller function
  - View / jsp: 
    // HttpServletRequest request;
    Object obj = request.getAttribute("key")
    String name = request.getAttribute("name")

------------------
Model Practical:
  - HomeController:
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
ModelAndView Practical:
  - HomeController:
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

------------
jsp view file:
  - index.jsp:
    <body>
      <h1>This is Home Page</h1>
      <% 
        String name = (String) request.getAttribute("name");
        List<String> friends = request.getAttribute("friends");
      %>
      <h2>Welcome <%=name%> <h2/>
      <p>
        <% for(String str : friends) {
          out.println(s);
        }
        %>
      </p>
    </body>

jsp view file using Expression Language:
  - index.jsp:
    <%@page isELIgnored="false" %>
    <!DOCTYPE html>
    <body>
      <h1>This is Home Page</h1>
      <% 
        #{name}         <!-- expression language -->
        #{friends}
      %>
      <h2>Welcome <%=name%> <h2/>
      <p>
        <% for(String str : friends) {
          out.println(s);
        }
        %>
      </p>
    </body>

---------------
JSTL language in view:  (JavaServer Pages Tag Library )
  - pom dependency: jstl

  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
  <body>
    <% #{friends} %>
    <c:forEach var="frnd" items="${friends}"> 
      <h1>${frnd}</h1>
    </c:forEach>
  </body>

----------
Mapping Request With @RequestMapping:
  - You use the @RequestMapping annotation to map URLs such as /appointments onto an "entire class" or a particular "handler method".
  - Typically the class-level annotation maps a specific request path (or path pattern) onto a form controller, 
    with additional method-level annotations narrowing the primary mapping
    for a specific HTTP method request method("GET", "POST",...,etc) or an HTTP parameter condition.

  @Controller
  @RequestMapping("/appointments")
  public class AppointmentController {
    @RequestMapping("/all") // GET: localhost:8080/<project_name>/appointments/all
    public void getAllAppointments() {}

    @RequestMapping(path = "/{day}", method = RequestMethod.POST) // POST: localhost:8080/<project_name>/appointments/monday
    public void getAppointmentsByDay() {}
  }

----------------
# Share data from View To Controller:
  - signUpPage.html
  - Controller:
@Controller
public class ContactController {
  @RequestMapping("/signup")
  public string showForm() {
    return "signUpPage";
  }

  // @RequestMapping(path="/processform", method=RequestMethod.POST)   // redirected from signUpPage.html
  // public string processFormByClass(HttpServletRequest request) {
  //   String email = request.getParameter("email"); // get data from view
  //   return "";
  // }

  // @RequestMapping(path="/processform", method=RequestMethod.POST)   // redirected from signUpPage.html
  // public string processFormByClass(@RequestParam("email") String email,
  //   @RequestParam(name = "username", required = false) String name,
  //   @RequestParam("password") String password
  // ) {
  //   System.out.println("name");
  //   return "";  // if you want redirect to another page, then pass page name in redurn. If you want to transfer data, we are use Model/ModelAndView class
  // }

  // @RequestMapping(path="/processform", method=RequestMethod.POST)   // redirected from signUpPage.html
  // public string redirectFormByClassRedirectToView(@RequestParam("email") String email,
  //   @RequestParam(name = "username") String name,
  //   @RequestParam("password") String password,
  //   Model model
  // ) {
  //   User user = new User();
  //   user.setEmail(email);
  //   user.setUsername(username);
  //   user.setPassword(password);
  //   model.addAttribute("user", user);
  //   return "successPage";
  // }

  //@ModelAttribute : performs 3 tasks => 1.@RequestParam 2.user.setEmail(email); 3. model.addAttribute
  // @ModelAttribute User user :  // helps to remove the boilerplate code of creating User object and setting the data in it.
      // 1. Take data from request and bind to User object 
      // 2. Add User object to Model object
      // 3. Return User object to view as Model.
  @RequestMapping(path="/processform", method=RequestMethod.POST)
  public String redirectFormByClassModelAttribute(@ModelAttribute User user, Model model) {
    return "successPage"; // this page will get the data
  }
}

public class User {
  String email;     // keyname must be same as signUpPage.html name field name to use @ModelAttribute
  String username; 
  String password;
  // add getters/setters
}
// Display:
  successPage.jsp:  <h1>Welcome ${user.username}</h1>

// --------Custom data for model
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

// ------
How to redirect in Spring MVC?
    - Use case: we can use for error validation, eg. If request payload is not valid, will redirect to error page. if valid then update DB.
1. HttpServletResponse (Feature of Servlet Framework)
    @RequestMapping("/one")
    public String first(HttpServletResponse response) {  // not recommended in Spring
        response.sendRedirect("two");       // redirect to "/two"
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
4. In JSP page: <% response.sendRedirect("two"); %>

//------------
File Uploading in Spring MVC: Using interface MultipartResolver
  dependency: common-fileupload, commons-io
  - spring-servlet.xml:
    <bean name="multipartResolver" class="org.springframework.web.multipart.commons.CommonMultipartResolver"
  - fileForm.jsp:
    <form action="uploadimage" method="post" enctype="multipart/form-data" >
      <input type="file" name="profile" />
      <button>Submit</button>
    </form>
  - Controller:
    @RequestMapping(value="/uploadimage", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("profile") CommonMultipartResolver file) {
      syso(file.getSize());
      file.getContentType(); // getName(), getOriginalFilename(), getStorageDescription, 
      byte[] data = file.getBytes();
      String path = s.getServletContext().getRealPath("/")+ "WEB-INF"+ File.separator + "image" + file.separator + file.getOriginalFilename();
      syso(path);
      FileOutputStream fos = new FileOutputStream(path);
      fos.write(data);
      fos.close();
      return "fileSuccessStatusPage"
    }
  
----------
@PathVariable:
  - this annotation is used to bind method parameter to URI template variable.
  @RequestMapping("/books/{bookId}/{userName}")
  public String handler(@PathVariable("bookId") int bookId, @PathVariable("userName") int name) {}  // localhost:8080/project_name/book/33

@ExceptionHandler:
  There are 2 ways to handle exceptions:
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
    @ControllerAdvice   // if any error occurs in any Controller, this class will get called automatically
    class MyGlobalExceptionHandler {
      @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
      @ExceptionHandler({value=NullPointerException.class}) public String nullPointerHandler(Model model) { model.addAttribute("message", "Null pointer exception occured"); return "error_page"} // handler only NullPointerException exception.

      @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
      @ExceptionHandler({value=NumberFormatException.class}) public String numberFormatHandler(Model model) { model.addAttribute("message", "Number format exception occured"); return "error_page"} // handler only NumberFormatException exception.

      @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
      @ExceptionHandler(value= Exception.class)
      public String allExceptionsHandler(Model model) { model.addAttribute("message", "exception occured"); return "error_page"} // Handle Exception and all child Exception classes.
    }

----------
Interceptors:
  - Used to pre-processing and post-processing the request.
  - Interceptor is used to perform operations before and after the request is processed.
  - #4.3.1 MVC Interceptor.png
  - Structure:
    interface HandlerInterceptor
      - boolean preHandler(): before Handler
        if true: go to handler
        if false: return the request/ no precessing
      - void postHandler(): After Handler/Before View
      - void afterCompletion(): after View
    class HandlerInterceptorAdaptor implements HandlerInterceptor
  - Example:
    - spring-servlet.jsp:
    <mvc:interceptor>
      <mvc:mapping path="/welcome" />
      <bean class="springmvcexample.MyInterceptor" ></bean>
    </mvc:interceptor>
  // class MyInterceptor implements HandlerInterceptor { // must implements all 3 interceptor methods.}
  class MyInterceptor extends HandlerInterceptorAdaptor {
    @Override
    public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
      String name = request.getParameter("user");
      if(name.startWith("d")) {
        response.setContectType("text/html");
        response.getWriter().println("<h1>Invalid name.. Name should not start with d.</h1>");
        return false;
      }
      return true;
    }

    @Override
    public void postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, 
      ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("name", "Sagar");
      }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
      Exception ex) throws Exception {
      }
  }