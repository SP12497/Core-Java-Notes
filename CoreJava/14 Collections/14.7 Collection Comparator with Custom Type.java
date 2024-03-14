import java.util.*;

// Interface : Collection and List
// Class : ArrayList , Collections

class CMain {
	public static void main(String[] args) {

		List<Integer> c = new ArrayList<>();

		c.add(11);
		c.add(52);
		c.add(131);
		c.add(242);
		c.add(83);
		c.add(22);
		c.add(131);
		c.add(342);
		c.add(13);
		c.add(131);
		c.add(492);
		c.add(394);

		// Comparator<Integer> com = new MyComparator();
		// Collections.sort(c, com); //second parameter required Comparator Interface
		// Object

		// Sort by last digit using lambda expression
		Collections.sort(c, (o1, o2) -> {
			return o1 > o2 ? 1 : -1; // Ascending order
		});

		// System.out.println(c.size());

		for (int o : c) {
			System.out.println(o);
		}
	}
}

class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		// Sort by last digit
		if (o1 % 10 > o2 % 10) // sort by Last digit Ascending order
		{
			return 1;
		}

		return -1;
	}
}
