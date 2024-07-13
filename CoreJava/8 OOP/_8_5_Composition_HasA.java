//Employee Has A Address class	
// Used in Class A having reference of another class B

//Has a relation : 	- Instance variable.
//					- Reference to another class 

public class _8_5_Composition_HasA {
	public static void main(String[] args) {
		Address addr = new Address("Nandurbar", "Maharashtra", 425412);
		Employee e = new Employee(1001, "Sagar", 500.10f, addr);
		e.disp();
	}
}

class Employee {
	int id;
	String name;
	float sal;

	Address add; // Employee Has A Address class

	public Employee(int id, String name, float sal, Address add) {
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.add = add;
	}

	void disp() {
		System.out.println(id + " " + name + " " + sal);
		System.out.println(add.city + " " + add.state + " " + add.pin);
	}
}

class Address {
	String city;
	String state;
	int pin;

	Address(String city, String state, int pin) {
		this.city = city;
		this.state = state;
		this.pin = pin;
	}
}