What is AOP?
    - Aspect Oriented Programming.
    - AOP is Programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns.
    - Process of applying services or external services as transaction management or logging to our application classes without modifying the code.
    - This services also called cross cutting concern.
    - Aspect-Oriented Programming (AOP) complements Object-Oriented Programming (OOP) by providing another way of thinking about program structure.

Understanding AOP with example
    - suppose, we have method called makePayment(), if in future some condition we have to add before or after the payment, 
      again we have to modify the makePayment() ie. core logic.
      What if, will get a service, using that, we can perform pre or post operation for makePayment(). So that, we dont have to touch makePayment().
      So, that why , AOP is introduced.
 
Terminology of AOP, Aspect, Advice, Joinpoint, Pointcut etc
    1. Aspect: (AOP class : class MyAspect)
        - An aspect is a modularization of a concern that cuts across multiple classes.
        - An aspect is a modularization of a concern that cuts across multiple objects.
        - Aspects in Spring are typically defined using a combination of regular classes annotated with @Aspect.
        - Example: Logging, transaction management, and security are common examples of aspects.
    2. Join Point: (the method, for which we have to run AOP logic. : makePayment() )
        - A join point is a specific point in the execution of your program, such as the execution of a method or handling an exception. 
          In Spring AOP, a join point always represents a method execution.
        - Example: The makePayment() method execution can be considered a join point.
    3. Advice: (methods in AOP class: printBefore(), printAfter())
        - Advice represents the action taken by an aspect at a particular join point. 
          It is the code that is executed when a specific join point is reached. 
          There are different types of advice:
            - @Before: Runs before the method execution.
            - @After: Runs after the method execution.
            - @AfterReturning: Runs after the method execution if it completes normally.
            - @AfterThrowing: Runs after the method execution if it exits by throwing an exception.
            - @Around: Runs before and after the method execution.
        - Example: A logging aspect that logs method execution time could have @Before and @After advices.
    4. Pointcut: (which method to run : execution(* com.aop.services.PaymentServiceImpl.makePayment())
        - A pointcut is an expression that matches join points (method executions). 
          Pointcuts are expressions that define which join points should be advised by a given advice.
        - Example: A pointcut expression might specify all methods in a class or all methods with a specific annotation.

Maven Project, Adding AOP related Depencencies
    - Dependencies:
        spring core, spring context
        spring-aop, aspectjrt (Aspectj Runtime), aspectjweaver

Creating Payment Services
    @Component
    public class PaymentServiceImpl {
        public void makePayment() {
            System.out.println("Account1 Debited...");
            System.out.println("Account2 Credited...");
        }

        public void doPayment(int amount) {
            System.out.println(amount + "Account1 Debited...");
            System.out.println(amount + "Account2 Credited...");
        }
    }

Adding AOP in our Payment Services.
    @Aspect
    public class MyAspect {
        // @Before(expression)     // Syntax
        // @Before("execution(return_type method_name_with_packaga)")   // Syntax
        // @Before("execution(* com.aop.services.PaymentServiceImpl.*")   // *: any return types , .*: all methods from PaymentServiceImpl class
        @Before("execution(* com.aop.services.PaymentServiceImpl.makePayment())")   // *: any return types , .*: all methods from PaymentServiceImpl class
        public void printBefore() {
            System.out.println("Payment Initiating...");
        }

        @After("execution(* com.aop.services.PaymentServiceImpl.makePayment())")   // *: any return types , .*: all methods from PaymentServiceImpl class
        public void printAfter() {
            System.out.println("Payment Completed.");
        }

        @Before("execution(* com.aop.services.PaymentServiceImpl.doPayment(..))")   // any params
        public void printBeforeAnyParam() {
            System.out.println("Payment Initiating...");
        }
    }
