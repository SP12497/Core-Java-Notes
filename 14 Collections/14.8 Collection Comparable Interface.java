// Comparable Interface :
	// Mark on the class , which we want to sort

import java.util.*;
class CMain
{
	public static void main(String[] args) 
		{
		List <Stud> ls = new ArrayList<Stud>();
		
		ls.add(new Stud(1,50));
		ls.add(new Stud(2,40));
		ls.add(new Stud(3,70));
		ls.add(new Stud(4,30));
		
		Collections.sort(ls);
		
		ls.forEach(System.out::println);	
	}
}

class Stud implements Comparable<Stud>
{
	int rollno;
	int marks;
	
	@Override
	public int compareTo(Stud o) {
		return this.marks>o.marks? 1 : this.marks < o.marks ? -1 : 0;
	}







	public Stud(int rollno, int marks) {
		super();
		this.rollno = rollno;
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Stud [rollno=" + rollno + ", marks=" + marks + "]";
	}
	
	
}