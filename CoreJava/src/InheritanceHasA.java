//Employee Has A Address class

//Has a relation : 	- Instance variable.
//					- Reference to another class 

public class InheritanceHasA
{
	public static void main(String[] args) 
	{
		Address a = new Address("Nandurbar","Maharashtra",425412);
		Employee e = new Employee(1001, "Sagar",50000f,a);
		
		e.disp();
		
	}

}


class Employee
{
	int id;
	String name;
	float sal;
	Address add;		//Employee Has A Address class
	
	public Employee(int id,String name,float sal, Address add)
	{
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.add = add;
	}
	
	void disp()
	{
		System.out.println(id+" "+name + " " + sal);
		System.out.println(add.city+ " " + add.state + " "+ add.pin);
	}
}
class Address
{
	String city;
	String state;
	int pin;
	
	Address(String city, String state, int pin)
	{
		this.city = city;
		this.state = state;
		this.pin = pin;
	}
}