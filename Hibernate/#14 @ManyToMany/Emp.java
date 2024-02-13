package com.ManyToMany;

import java.util.List;

import javax.persistence.*;

@Entity
public class Emp 
{
	@Id
	private int eid;
	private String ename;
	
	@ManyToMany
	@JoinTable		//change table and column name
		(
			name = "Emp_Proj_Mapping",
			joinColumns = {@JoinColumn(name="Emp_Id")},
			inverseJoinColumns = {@JoinColumn(name="Proj_Id")}
		)
	private List <Project> plist;

	

	public Emp(int eid, String ename, List<Project> plist) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.plist = plist;
	}



	public int getEid() {
		return eid;
	}



	public void setEid(int eid) {
		this.eid = eid;
	}



	public String getEname() {
		return ename;
	}



	public void setEname(String ename) {
		this.ename = ename;
	}



	public List<Project> getPlist() {
		return plist;
	}



	public void setPlist(List<Project> plist) {
		this.plist = plist;
	}



	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
