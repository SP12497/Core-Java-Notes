class C1 {
    public void m1(double num) {
        System.out.println("Inside C1.m1(): " + num);
    }
}

class C2 extends C1 {
    public void m1(int num) {
        System.out.println("Inside C2.m1(): " + num);
    }
}


public class _8_8_1_Method_overloading_and_riding_combine {

    public static void main(String[] args) {
        C1 c1 = new C1();
        c1.m1(10);			//Inside C1.m1(): 10.0
        
        C2 c2 = new C2();
        c2.m1(10);			//Inside C2.m1(): 10
        c2.m1(10.11);       //Inside C1.m1(): 10.11
    }
}