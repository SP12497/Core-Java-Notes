package com.springcore.auto.wire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Employee {
	String name;
	
	@Autowired
	@Value("#{address}")
	private Address address;
	
	
	
	public Employee() {
		super();
		System.out.println("Employee default constructor");
		// TODO Auto-generated constructor stub
	}

//	@Autowired
	public Employee(String name, Address address) {
		super();
		System.out.println("Employee parameterized constructor");
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Emp [name=" + name + ", address=" + address + "]";
	}

	public String getName() {
		return name;
	}

//	@Autowired
	public void setName(String name) {
		System.out.println("Employee name setter");
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		System.out.println("Employee address setter");
		this.address = address;
	}

	
}
