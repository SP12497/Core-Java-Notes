package Numbers;
//Static Block:
// first all static block will execute sequencialyy then main static block will execute.


import static java.lang.System.out;

public class Main {
    int a = 5;
    static {
        // static (class level) block
        out.println("CA static 1");
    }

    {
        // instance (object level) block
        out.println("CA instance 1");
    }


    public static void main(String[] args) {
        out.println("CA main");
        CB cb;
        Main m = new Main();
        cb = new CB();
    }    
    
    static {
        out.println("CA static 2");
    }
    {out.println("CA instance 2");}
}

class CB {

    {
        out.println("CB instance 1");
    }

    public static int getBalance() {
        return 123;
    }

   static {
        // static block
        out.println("CB static 1");
    }

    CB() {
        out.println("CB default constructor");
    }
   {out.println("CB instance 2");}

}



/*
CA static 1
CA static 2
CA main
CB static 1
CB instance 1
CB instance 2
CB default constructor
 */