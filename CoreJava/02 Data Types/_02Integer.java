
public class _02Integer {
	public static void main(String[] args) {
		implicitTypecasting();
		explicitTypecasting();

		// parsing:
		String numStr = "123";
		// int num= (int) numStr; // Cannot cast from String to int
		int num = Integer.parseInt(numStr);

		// class Integer
		Integer i = 10;
		Double d = i.doubleValue();
		Float f = i.floatValue();
		// Long l = i; // Type mismatch: cannot convert from Integer to Long
		Long l = i.longValue();
		double dd = i;

		int j;
		// System.out.println(j);	// error: The local variable j may not have been initialized
		
		Integer n = null;
		// Integer m = undefined; // Java dont have undefined keyword.
		if(n == null) {
			System.out.println("n is null");
		}
		
		int total = Integer.sum(11, 22);
		System.out.println("Integer.sum :" + total);
		total = Integer.max(44, 22);
		System.out.println("Integer.max :" + total);
	}

	static void implicitTypecasting() {
		int i = 123;
		double d = i;
		float f = i;
		long l = i;
		// Implicit typecasting occurs automatically when a value of a smaller data type
		// is assigned to a variable of a larger data type.
	}

	static void explicitTypecasting() {
		double d = 123;
		int i = (int) d;
		float f = (float) d;
		long l = (long) d;
		// boolean b = (boolean) d; // error
		/*
		 * - when assigning a value of a larger data type to a variable of a smaller
		 * data type. - Data loss may occur when converting from a larger data type to a
		 * smaller one. For example, when casting a double to an int, the fractional
		 * part is truncated.
		 */
	}

}