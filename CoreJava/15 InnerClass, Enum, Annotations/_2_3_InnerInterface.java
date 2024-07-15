//1. Interface in a class

//Interfaces (or classes) can have only public and default access specifiers when declared outside any other class.

// Java program to demonstrate working of 
// interface inside a class. 


class CA { 
    interface IA { 
        void show(); 
    } 
}

class CB implements CA.IA {
    public void show() {
        System.out.println("show method of interface");
    }
}

class Main {
    public static void main(String[] args) {
        CA.IA ia;
        CB cb = new CB();

        ia = cb;
        ia.show();

        // -------------
        CA.IA ia2 = new CB();
    }
}
// output
// show method of interface

// ----------------------------

// 2. Interface in another Interface
// access specifier is public even if we have not written public.

// Java program to demonstrate working of
// interface inside another interface.


interface IP {
    interface IC {
        void show(); // only public
    }
}

class CI implements IP.IC {
    public void show() {
        System.out.println("show method of interface");
    }
}

public class _2_3_InnerInterface {
    public static void main(String[] args) {
        IP.IC obj;
        CI t = new CI();
        obj = t;
        obj.show();
    }
}

// show method of interface

// ----------------------
