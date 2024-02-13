package com.ManyToMany;

import java.util.List;

import javax.persistence.*;

@Entity
public class Project
{
	@Id
	private int pid;
	private String pname;
	
	//@ManyToMany (mappedBy = "plist")	//project_emp table will not created
	@ManyToMany
	private List <Emp> elist;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List<Emp> getElist() {
		return elist;
	}

	public void setElist(List<Emp> elist) {
		this.elist = elist;
	}

	public Project(int pid, String pname, List<Emp> elist) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.elist = elist;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Project [pid=" + pid + ", pname=" + pname + ", elist=" + elist + "]";
	}
	
	

}
