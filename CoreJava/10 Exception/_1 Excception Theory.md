```java
// Exceptions in Java

// Exception: An error that occurs during runtime, causing the program flow to terminate abruptly.


// Exception Handling in Java

// Throwable: The parent class for all exceptions.
class Throwable {
    // 1. Exception: Errors that can be handled.
    class Exception extends Throwable {
        // Checked Exceptions (Compile Time)
        class IOException extends Exception {}
        class SQLException extends Exception {}

        // Unchecked Exceptions (Runtime)
        class RuntimeException extends Exception {
            class ArithmeticException extends RuntimeException {}
            class NullPointerException extends RuntimeException {} // calling method on a null object
            class ArrayIndexOutOfBoundsException extends RuntimeException {}
            class ClassCastException extends RuntimeException {}
        }
    }

    // 2. Error: Serious issues that cannot be handled (e.g., CPU failure, memory leaks).
    class Error extends Throwable {
        class StackOverflowError extends Error {}
        class VirtualMachineError extends Error {}
        class OutOfMemoryError extends Error {}
    }
}

// Java Exceptions Overview:
//	When executing Java code, different types of errors can occur due to coding mistakes, incorrect input, or unforeseen issues. 
//	When an error occurs, Java stops execution and throws an exception.


// Exception Handling Keywords

// try: Defines a block of code to be tested for errors.
try {
    // Code that might throw an exception

	// throw: Used to explicitly throw an exception, often custom/user-defined.
	throw new ArithmeticException("Custom error message");
} catch (Exception e) {
    // catch: Defines a block of code to be executed if an error occurs in the try block.
    // Handle the exception
} finally {
    // finally: A block of code that always executes after try and catch blocks, unless the JVM exits.
    // Cleanup code
}



// throws: Declares that a method can throw exceptions, typically used for IO or SQL exceptions.
void method() throws IOException, SQLException {
    // Method implementation
}

// finally block
try {
    // Code that might throw an exception
} catch (Exception e) {
    // Handle the exception
} finally {
    // Executes regardless of whether an exception was thrown
}

// Common Exceptions

class ArithmeticException extends RuntimeException {}
class FileNotFoundException extends IOException {}
class ArrayIndexOutOfBoundsException extends RuntimeException {}
class SecurityException extends RuntimeException {}

// FAQ

// Q: Will the finally block execute if a return statement is in the try or catch block?
// A: Yes, the finally block will still execute even if a return statement is executed in the try or catch block. 
// The only exception is if System.exit(0) is called before the finally block.
```
