// https://www.javatpoint.com/java-annotation

/*
There are three types of annotations.
	1 Marker Annotation.
	2 Single-Value Annotation.
	3 Multi-Value Annotation.
	
 Java Annotation is a tag that represents the metadata 
  i.e. attached with class, interface, methods or fields 
  to indicate some additional information which can be used by java compiler and JVM.

*/

// Creating Custom/Own Annotation

//1 Marker Annotation.
@interface Phone{}

//2 Single-Value Annotation.
@interface Apple
{
	int value();
}

@interface MyAnnotation
{  
	int value() default 0;  
}  

//3 Multi-Value Annotation.
@interface SmartPhone
{
	//MultiValue Annotation
	String os() default "Android";
	int version() default 4;
}


//apply Multi-Value Annotation :

@SmartPhone(os="Windows", version=5)
class RealMe
{

	@Override
	public String toString() {
		return "RealMe [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				 +"]";
	}	
}

public class CMain
{
	public static void main(String[] args) 
	{
		RealMe obj = new RealMe();
		System.out.println(obj);		
	}
}