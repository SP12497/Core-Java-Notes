// TODO: Executor service? Thread safety?

// We can achieve multi-threading using:
// - interface Runnable
// - class Thread implements Runnable
// class Thread implements Runnable{}

/*
What is Multithreading in Java?
Multithreading in Java is the process of executing two or more threads simultaneously to maximize CPU utilization. 
Multithreaded applications run multiple threads concurrently, which is also known as concurrency in Java.

Each thread runs in parallel, sharing the same memory area, which saves memory. 
Context switching between threads is also efficient.

For more details, visit: https://www.guru99.com/multithreading-java.html

class Thread has start method
interface Runnable has run abstract method
*/

class A extends Thread {
    public void show() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("A show()...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        show();
    }
}

class B extends Thread {
    public void show() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("B show()...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        show();
    }
}

public class _13_1_ThreadClass {
    public static void main(String[] args) {
        // Both A and B print simultaneously
        A obj1 = new A();
        obj1.start();
        // obj1.start(); // Error: thread already started.

        B obj2 = new B();
        obj2.start();
    }
}

// Interview Questions and Short Answers

/*
1. What is the difference between a process and a thread?
   - A process is a self-contained execution environment, while a thread is a smaller unit (smaller chunk) of a process that can run concurrently.

2. What is the purpose of the Runnable interface?
   - The Runnable interface is used to define a task that can be executed by a thread.

3. What are the benefits of multithreading?
   - Improved performance, better resource utilization, and enhanced application responsiveness.

4. What is thread safety?
   - Thread safety ensures that shared data is accessed and modified by multiple threads without causing data inconsistency.

5. What is the Executor framework in Java?
   - The Executor framework provides a higher-level replacement for managing threads, offering thread pools and task scheduling.

6. What happens if you call start() on a thread that has already been started?
   - It will throw an IllegalThreadStateException.

7. How do you handle exceptions in threads?
   - Use a try-catch block inside the run() method to catch exceptions thrown by the thread.
*/
