/*
Inheritance	: - Sharing the Information.
	- In Java, it is possible to inherit attributes and methods from one class to another. 
	- Inheritance can be defined as the process where one class acquires the properties (methods and variables) of another.
	- We group the "inheritance concept" into two categories:
		- superclass (parent/base) - the class being inherited from.
		- subclass (child/derived) - the class that inherits from another class.

	- To inherit from a class, use the "extends" keyword.
	- Inheritance represents the IS-A relationship which is also known as a parent-child relationship.

	Inheritance : Is-A Relationship.
	Composition : Has-A Relationship.
	
	Types of Inheritance:
		- Single Inheritance	(B:A)			// class B extends A {}
		- Multi-Level			(C:B:A)
		- Hierarchical			(B:A  -  C:A)
		- Multiple Inheritance: 	(C:A,B)				Not support in java, bcoz of Diamond Problem.
		- Hybrid				(C:A,B - A:P - B:P)	- Not Support : Hierarchical + Multiple
*/

public class _8_3_Inheritance_IsA {
	public static void main(String[] args) {
		Add obj = new Add();
		System.out.println(obj.sum());
		// System.out.println(obj.sub()); //error

		Add obj2 = new AddSub();
		System.out.println(obj2.sum()); // parent //call
		// System.out.println(obj2.sub()); //child //error: reference is parent type.

		AddSub obj1 = new AddSub();
		System.out.println(obj1.sum()); // parent
		System.out.println(obj1.sub()); // child
	}
}

class Add {
	int num1; // default scope
	int num2;
	int result;

	public Add() {
		num1 = 20;
		num2 = 10;
	}

	public int sum() {
		return (num1 + num2);
	}
}

class AddSub extends Add {
	public int sub() {
		return (num1 - num2);
	}
}