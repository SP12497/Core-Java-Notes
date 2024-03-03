
/*
  Encapsulation in Java is a mechanism of wrapping the data (variables)
 	and code acting on the data (methods) together as a single unit. 
  In encapsulation, the variables of a class will be hidden from other classes,
	and can be accessed only through the methods of their current class. 
   	Therefore, it is also known as data hiding.

To achieve encapsulation in Java −
	- Declare the variables of a class as private.
	- Provide public setter and getter methods to modify 
	  and view the variables values.
*/

class RunEncap
{
	private int rollno;
	private String name;
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

public class Encapsulation
{

	public static void main(String[] args)
	{
		RunEncap stud = new RunEncap();
		
		stud.setRollno(1);
		stud.setName("Sagar");
		
		System.out.println(stud.getRollno());
		System.out.println(stud.getName());
	}
}
