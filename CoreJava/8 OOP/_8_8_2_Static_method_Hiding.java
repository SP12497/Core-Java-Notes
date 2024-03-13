public class _8_8_2_Static_method_Hiding
{
	public static void main(String[] args) {
	    System.out.println(RBI.balance);
	    System.out.println(SBI.balance);
		System.out.println(SBI.getBalance(6)); // RBI Method Hiding
		System.out.println(RBI.getBalance(5));
		System.out.println(RBI.getBalance(""));
		
		RBI rbi = new RBI();
        System.out.println(rbi.balance);
	   // System.out.println(rbi.interest); // Data Hiding
		/*
         */
	}
}

class RBI {
    static int balance = 100;
    private int interest = 10;  // Data Hiding
    
    public static int getBalance(int a) {
        return balance;
    }
    public static int getBalance(String a) {
        return 777;
    }
} 

class SBI extends RBI {
    public static int getBalance(int a) { // Method Hiding : RBI: getBalance
        // super is not available in static method // available in Instance methods
        // RBI.getBalance(1);
        return 44;
    }
}
