/*
Encapsulation:
	- Grouping the Information.
  Encapsulation in Java is a mechanism of wrapping/binding the data (variables)
 	and code acting on the data (methods) together as a single unit. 

	Data Hiding: <- Abstraction
		In Abstraction, the variables of a class will be hidden from other classes,	and can be accessed only through 
		the methods of their current class. Therefore, it is also known as DATA HIDING.
	Method Hiding:
		- OverRiding of static method is not possible is java. But we can overload static method.
		- If we ReDefining the static method of super class into sub class then , this is called as Method Hidding.

To achieve Data Hiding in Java −
	- Declare the variables of a class as private.
	- Provide public setter and getter methods to modify 
	  and view the variables values.

What is OOP:
	Object-oriented programming is about creating objects that contain both data and functions.
4 Pilors of OOP :
	  Encapsulation : - Grouping the Information.	=> Abstration + Data Hiding
	  Abstraction 	: - Hiding the Information.
	  Inheritance	: - Sharing the Information.
	  Polymorphism	: - Redefining the Information.
*/

class RunEncap {
	// private
	private int rollno; // fields, member variable, attribute, property // it contains/stores data
	private String name;

	// public
	public int getRollno() { // method, function, code block // its a code acting on the data
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

public class EncapsulationMain {

	public static void main(String[] args) {
		RunEncap stud = new RunEncap();

		stud.setRollno(1);
		stud.setName("Sagar");

		System.out.println(stud.getRollno());
		System.out.println(stud.getName());
	}
}
