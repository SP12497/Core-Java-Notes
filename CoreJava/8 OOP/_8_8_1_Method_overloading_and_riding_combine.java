class C1 {
    public void m1(double num) {
        System.out.println("Inside void C1.m1(double): " + num);
    }

    private void m1(int num) {
        System.out.println("Inside int C2.m1(int): " + num);
    }

    protected void m1(char c) {
        System.out.println("Inside int C2.m1(char): " + c);
    }

    public void paramCheck(double num, int a) {
        System.out.println("Inside void C1.paramCheck(double, int): " + num);
    }
    
}

class C2 extends C1 {
    // public int m1(double num) {          // error: The return type is incompatible with void C1.m1(double)
    //     System.out.println("Inside void C1.m1(double): " + num);
    //     return num;
    // }

    // private void m1(double num) {   // private/protected: error: attempting to assign weaker access privileges; was public
    //     System.out.println("Inside void C1.m1(double): " + num);
    // }

    public int m1(int num) {
        System.out.println("Inside int C2.m1(int): " + num);
        return num;
    }

    public void m1(char c) {        // we can increase the access specifier, but can't decrease. (protected -> public)
        System.out.println("Inside int C2.m1(char): " + c);
    }

    public void paramCheck(int num, double a) {     // works fine
        System.out.println("Inside void C2.paramCheck(int, double): " + num);
    }
}


public class _8_8_1_Method_overloading_and_riding_combine {

    public static void main(String[] args) {
        C1 c1 = new C1();
        c1.m1(10);			//Inside C1.m1(double): 10.0
        
        C2 c2 = new C2();
        c2.m1(10);			//Inside C2.m1(int): 10
        c2.m1(10.11);       //Inside C1.m1(double): 10.11

        c2.paramCheck(1, 1.1);  // Inside void C2.paramCheck(int, double): 1
        c2.paramCheck(1.1, 1);  // Inside void C1.paramCheck(double, int): 1.1
    }
}