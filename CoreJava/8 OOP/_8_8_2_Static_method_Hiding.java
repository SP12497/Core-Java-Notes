public class _8_8_2_Static_method_Hiding{
	public static void main(String[] args) {
	    System.out.println("RBI.balance: " +RBI.balance);
	    System.out.println("SBI.balance: " + SBI.balance);
		System.out.println("SBI.getBalance(6): " + SBI.getBalance(6)); // RBI Method Hiding
		System.out.println("RBI.getBalance(5): " + RBI.getBalance(5));
		System.out.println("SBI.getBalance(''): " + RBI.getBalance(""));
		
		RBI rbi = new RBI();
        System.out.println("rbi.balance: " + rbi.balance);
	   // System.out.println(rbi.interest); // Data Hiding
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
        // 'super' keyword is not available in static method // available in Instance methods
        System.out.print(" RBI.getBalance(1): " + RBI.getBalance(1));
        // RBI.getBalance(1);
        return 44;
    }
}


/*
Output:
    RBI.balance: 100
    SBI.balance: 100

    RBI.getBalance(1): 100    
    SBI.getBalance(6): 44

    RBI.getBalance(5): 100
    SBI.getBalance(''): 777
    rbi.balance: 100
*/
