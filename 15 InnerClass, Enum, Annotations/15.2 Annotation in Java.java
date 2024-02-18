/*
 Annotation in Java (@) :  @
	1. @Override
	2. @SuppressWarnings :
		is used to suppress warnings issued by the compiler.
	3. @Deprecated :
		@Deprecated annoation marks that this method is deprecated so compiler prints warning. It informs user that it may be removed in the future versions. So, it is better not to use such methods.
	4. @FunctionalInterface  
	
	https://www.javatpoint.com/java-annotation
	
*/

import java.util.ArrayList;

@FunctionalInterface //allows only one method inside the interface
interface IA
{
	public void print();
	//public void show();	//Second methode creation will throw errors.
}


class CA
{
	public void showData() {
		System.out.println("CA showData");
	}
}

class CB extends CA
{
	//@Override
	//public void showdata(){} //  not allows non parent method or spelling mistake
	
	@Override
	@SuppressWarnings("unchecked") // is used to suppress warnings issued by the compiler.
	public void showData()
	{
		ArrayList obj = new ArrayList();	//without generics that why it shows errors, we suppress it by @SuppressWarning
		System.out.println("CB showData");
	}
}

public class Main
{
	public static void main(String[] args) 
	{
		
	}
}
