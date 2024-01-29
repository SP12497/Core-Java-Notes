import java.nio.charset.MalformedInputException;
import java.util.Scanner;

interface taxinterface
{
	void findtax(float sal) ;
}





abstract public class Employee implements taxinterface
{
      
	int empno;
	String name;
	float sal;
	
	Employee(int empno,String name,float sal)
	{
		
		this.empno = empno;
		this.name = name;
		this.sal = sal;
			
	}
	
	
		
	void dispEmp()
	{
		System.out.println(empno+name+sal);
		
	}

   
	
	abstract public void salary();
	
	
	
	public String toString()
	{
		return empno+name+sal;
		
	}
	
	
	
	
}

class SalariedEmploee  extends Employee implements taxinterface
{
	
	int hra; 

	SalariedEmploee(int empno, String name, float sal,int hra) 
	{
		super(empno, name, sal);
		this.hra = hra;
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "SalariedEmploee [tax=" + hra + ", empno=" + empno + ", name=" + name + ", sal=" + sal + "]";
	}









	@Override
	 public void salary() {
		sal = sal-hra;
		System.out.println(sal);
		
	}









	@Override
	public void findtax(float sal) {
		
		float tax = sal*0.2f;
		System.out.println(tax);
		
	}


	
	
	
	
}


class Hourlyemployee extends Employee implements taxinterface

{
	int wages;
	int hours;
	
	Hourlyemployee(int empno, String name, float sal,int wages,int hours)
	{
		super(empno, name, sal);
		this.wages= wages;
		this.hours=hours;
		
	}

	@Override
	public String toString() {
		return "Hourlyemployee [wages=" + wages + ", hours=" + hours + ", empno=" + empno + ", name=" + name + ", sal="
				+ sal + "]";
	}
	
	
	public void salary()
	{
		int salary = wages*hours;
		
		sal = sal+salary;
		System.out.println(sal);
				
	}

	@Override
	public void findtax(float sal) {
		
		float tax = sal*0.1f;
		System.out.println(tax);
		
	}
	
	
	
	
	
	
	
}











class Emptest
{

	
	static Employee CreateObject(String cname)
	{
		
		if(cname.equals("SalariedEmploee"))
		{
			return new SalariedEmploee(1001,"shan",50000,3000);
			
		}else if(cname.equals("Hourlyemployee"))
		{
			return new Hourlyemployee(1002,"raj",1000,300,200);
		}
			
		else
			return null;
		
	}
	
	
	
public static void main(String[] args) 
{

//	Employee e1 = new Employee(1001,"shan",4000);
//	Employee e2 = new Employee(1002,"raj",5000);
////	e1.dispEmp();
////	e2.dispEmp();
//	
//	System.out.println(e1);
//	e1.salary();
//	System.out.println(e2);
//	e2.salary();
	
	
	Scanner s = new Scanner(System.in);
	String cname;
	cname = s.next();
//	
//	
//	
//	
//	
	Employee e;
	e = CreateObject(cname);
	System.out.println(e);
	e.salary();
	e.findtax(e.sal);
	
	
//	SalariedEmploee sa = new SalariedEmploee(1001,"shan",50000,3000);
//	System.out.println(sa);
//	sa.salary();
//	sa.findtax(sa.sal);
//	
//	Hourlyemployee ho =new Hourlyemployee(1002,"raj",1000,300,200);
//	
//	System.out.println(ho);
//	sa.salary();
//	sa.findtax(ho.sal);
	
	
	
	
	
	
}

}
	










