Exception :
	- Exception is an error which occured during runtime.
	- when run time errors occurs, the flow of program get terminated abroubtly.
	

Exception Handling  :
	java.io.Serializable interface:
		java.lang.Throwable class : (is a class/ Parent)
		(Sub Classes/Child)
			- java.lang.Exception class : Can be handled
				- Checked (Compile Time Exception)
					- IOException
					- SQLException
				- UnChecked	(Runtime Exception)
					- Runtime Exception
						- ArithmeticException
						- NullpointerException	//calling method by null obj
						- ArrayIndexOutOfBoundsException 
						- ClassCastException
			- java.lang.Error class : Can't be handled  (CPU not working / Memory leakage in JVM)
				- StackOverflowError
				- VirtualMachineError
				- OutOfMemoryError
//------
Java Exceptions : 
		When executing Java code, different errors can occur: 
			coding errors made by the programmer, errors due to wrong input, 
			or other unforeseeable things.
		
		When an error occurs, Java will normally stop and generate an error message. 
			The technical term for this is: Java will throw an exception (throw an error).
		
		* Java try and catch :
			The try statement allows you to define a block of code to be tested for errors while it is being executed.

		 The catch statement allows you to define a block of code to be executed, if an error occurs in the try block
		
		* The throw keyword
			The throw statement allows you to create a custom error.
			The throw statement is used together with an exception type. 
			There are many exception types available in Java:
				ArithmeticException, FileNotFoundException, ArrayIndexOutOfBoundsException, SecurityException, etc:

/*	keywords :
		try		: used to specify a block where we should place exception code.
		catch	:
				- used to handle exception.
		throw	: 
				- used to throw the custom/user defined exceptions.
				- used to throw the exception (runtime) explicitly.
				- throw new ArithmeticException(); 
		throws	: 
				- used to supress the error 
				- Related to IO or SQL exception
		finally :
				- This block is always executed 
				- only one way to stop exception of finally block is by using 
					System.exit(0) before finally block.


Q9 — Will the finally block be executed if the try or catch block executes a return statement?
Answer
Yes, the finally block will still be executed even if a return statement was executed in a try or catch block. 
This is a very popular and tricky Java question. 
The only way we can stop finally block from being executed is to use the System.exit(0) method.
