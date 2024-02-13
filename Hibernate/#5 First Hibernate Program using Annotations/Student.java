package com.tut;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //automatically create , save , fetch table
public class Student
{
	@Id		//set PK to id
	private int id;
	private String name;
	private String city;
	
	
	
	
	public Student(int id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}
	
	
	
	public Student() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return  id  +"  :  " +name + "  :  "+ city ;
	}
	
	
	
	
	
	

}
