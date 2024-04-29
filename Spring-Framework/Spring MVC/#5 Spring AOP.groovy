What is AOP?
    - Aspect Oriented Programming.
    - AOP is Programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns.
    - Process of applying services or external services as transaction management or logging to our application classes without modifying the code.
    - This services also called cross cutting concern.
    - Aspect-Oriented Programming (AOP) complements Object-Oriented Programming (OOP) by providing another way of thinking about program structure.

Understanding AOP with example
    - suppose, we have method called makePayment() , if in future some condition we have to add before or after the payment, 
      again we have to modify the makePayment() ie. core logic.
      What if, will get a service, using that, we can perform pre or post operation for makePayment(). So that, we dont have to touch makePayment().
      So, that why , AOP is introduced.
 
Terminology of AOP, Aspect, Advice, Joinpoint, Pointcut etc
    Aspect: (AOP class)
        - An aspect is a modularization of a concern that cuts across multiple classes.
    Join Point: (the method, for which we have to run AOP logic. eg. makePayment() )
        - Join point is any point in your program such as method execution exception handling,
          field access etc. Spring supports only method execution join point.
    Advice: (methods in AOP class)
        - Advice represent an action taken by an aspect at particular join point.
    Pointcut: (which method to run)
        - It is an expression of AOP that matches join point

Maven Project, Adding AOP related Depencencies
    - Dependencies:
        spring core, spring context
        spring-aop, aspectjrt (Aspectj Runtime), aspectjweaver

Creating Payment Services
    @Component
    public class PaymentServiceImpl {
        public void makePayment() {
            System.out.println("Account Debited...");
            System.out.println("Account Credited...");
        }

        public void doPayment(int amount) {
            System.out.println(amount + "Account Debited...");
            System.out.println(amount + "Account Credited...");
        }
    }

Adding AOP in our Payment Services.
    @Aspect
    public class MyAspect {
        // @Before(expression)     // Syntax
        // @Before("execution(retunt_type method_name)")   // Syntax
        // @Before("execution(* com.aop services.PaymentServiceImpl.*")   // *: any return types , .*: all methods from PaymentServiceImpl class
        @Before("execution(* com.aop services.PaymentServiceImpl.makePayment()")   // *: any return types , .*: all methods from PaymentServiceImpl class
        public void printBefore() {
            System.out.println("Payment Started");
        }

        @After("execution(* com.aop services.PaymentServiceImpl.makePayment()")   // *: any return types , .*: all methods from PaymentServiceImpl class
        public void printAfter() {
            System.out.println("Payment Started");
        }

        @Before("execution(* com.aop services.PaymentServiceImpl.doPayment(..)")   // any params
        public void printBefore() {
            System.out.println("Payment Started");
        }
    }