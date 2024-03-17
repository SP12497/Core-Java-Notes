// Comparable Interface :
// Mark on the class , which we want to sort

import java.util.*;

class Main {
	public static void main(String[] args) {

		System.out.println("---Collections.sort()---");
		List<Stud> ls = new ArrayList<Stud>();

		ls.add(new Stud(1, 50));
		ls.add(new Stud(2, 40));
		ls.add(new Stud(3, 70));
		ls.add(new Stud(4, 30));
		ls.add(new Stud(5, 40));

		Collections.sort(ls);
		ls.forEach(System.out::println);

		System.out.println("---Arrays.sort()---");
		Stud[] studs = { new Stud(1, 50), new Stud(2, 40), new Stud(3, 70), new Stud(4, 30), new Stud(5, 40) };

		Arrays.sort(studs);
		for (Stud s : studs)
			System.out.println(s);

	}
}

class Stud implements Comparable<Stud> {
	int rollno;
	int marks;

	@Override
	public int compareTo(Stud s) {
		// +ve: swap , -ve/0: no swap
		// return this.marks>o.marks? 1 : this.marks < o.marks ? -1 : 0;
		// return this.marks - s.marks; // ASC: -ve: this.marks will come first // +ve:
		return -(this.marks - s.marks); // DESC: -ve: this.marks will come first // +ve: s.marks will come first
	}

	// ---------
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
