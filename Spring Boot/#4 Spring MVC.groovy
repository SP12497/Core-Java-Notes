What is Spring MVC: (Model-View-Controller)
    - A spring mvc is a sub framework of Spring framework which is used to build a web application.
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
      View: Presents data to user
      Controller: Interface b/w model and view
    - Way to organize the code in our application.

Problem without MVC design pattern

---------
Configura Tomcat and Eclipse:
  - download tomcat: eg. tomcat 9
  - Eclipse: Window-> Preferences:
    - Server -> Runtime Environments:
      - Add: Apache Tomcat v9.0: select tomcat 9 home directory by zip extracted folder
    - Validate:
      window-> show view ->  server  : here, we can start tomcat

Create Project:
  - New Maven Project: Internal: quickstart 
  - suppose project name is: springmvc
  - Set Tomcat to this project if project showing red cross:
    - Right click on Project:
      - Build Path: Java Build Path: Libraries:
        - Add Library: Server Runtime: Apache Tomcat 9.0  // Now tomcat dependency added in depedency folder
  - Add pom dependency: spring-webmvc  // once added, update Maven Project
  - RUN: localhost:8080/<project-name>

Sprinv MVC application creation steps: (refer: "#4.1 MVC Working.png")
  1. Configure the dispatcher servler in web.xml
  2. Create Spring configuration file
  3. Configure View Resolver
  4. Create Controller
  5. Create a View to show (page)

Step 1: Configure the dispatcher servler in web.xml
  - src/main/java/webapp/WEB.xml:
    <web-app> 
        <!-- Configure dispatcher Servler-->
        <servlet>
            <servlet-name>spring</servlet-name> <!-- any name -->
            <servlet-class>org.springframework.web.servler.DispatcherServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>spring</servlet-name> <!-- Dispatcher servlet name -->
            <url-pattern>/</url-pattern>    <!-- / means all, /student means only start with student path related-->
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
      return "index";  // views/index.jsp // 
      // String "index" will go to FrontController ie dispatcher servlet
      // then, dispatcher servlet will send to View Resolver and View Resolver will retun the View ie index.jsp page.
      // then, FrontController will return the index.jsp as response.

    }
  }

Step 5. Create a View to show (page):
  - WEB-INF/views/index.jsp
    - Write some jsp/html code

Run and Call: localhost:8080/springmvc/home


----------------------------------
# Sending data from Controller to View.
  - When controller is returning the view name, same time we can return the data.
    View Resolver will bind that data to view.
  - There are 2 ways we can pass the data: (Angular: One way data binding: controller to view)
    1. Model:
      - addAttribute(String key, Object value);
    2. ModelAndView:
      - addObject(String key, Object value);
      - setViewName(String viewName) : void
  - View: 
    // HttpServletRequest request;
    Object obj = request.getAttribute("key")

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
        return "index";
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
JSTL language in view:
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
  - Typically the class-level annotation maps a specific request path (or path pattern) onto a form controller, with additional method-level annotations narrowing the primary mapping
    for a specific HTTP method request method("GET", "POST",...,etc) or an HTTP parameter condition.

  @Controller
  @RequestMapping("/appointments")
  public class AppointmentController {
    @RequestMapping("/all") // GET: localhost:8080/<project_name>/appointments/all
    public void getAllAppointments() {}

    @RequestMapping(path = "/{day}", method = RequestMethod.POST) // POST: localhost:8080/<project_name>/appointments/all
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

  @RequestMapping(path="/processform", method=RequestMethod.POST)
  public string processForm() {
    return "signUpPage";
  }
}
