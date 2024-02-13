package com.tut;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //automatically create , save , fetch table
public class Student
{
	@Id		//set PK to id
	private int RollNo;
	private String name;
	private Certificate certi;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int rollNo, String name, Certificate certi) {
		super();
		RollNo = rollNo;
		this.name = name;
		this.certi = certi;
	}
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Certificate getCerti() {
		return certi;
	}
	public void setCerti(Certificate certi) {
		this.certi = certi;
	}
	@Override
	public String toString() {
		return "Student [RollNo=" + RollNo + ", name=" + name + ", certi=" + certi + "]";
	}
}
