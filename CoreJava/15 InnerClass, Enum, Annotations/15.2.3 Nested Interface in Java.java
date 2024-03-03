//1. Interface in a class
 
//Interfaces (or classes) can have only public and default access specifiers when declared outside any other class.

// Java program to demonstrate working of 
// interface inside a class. 

import java.util.*; 

class Test 
{ 
    interface Yes 
    { 
        void show(); 
    } 
} 
  
class Testing implements Test.Yes 
{ 
    public void show() 
    { 
        System.out.println("show method of interface"); 
    } 
} 
  
class A {
    public static void main(String[] args)
    { 
        Test.Yes ref; 
        Testing tObj = new Testing(); 

        ref = tObj;
        ref.show();

        // -------------
        Test.Yes ref2 = new Testing(); 
    }
}
//output
	//show method of interface 
	
//----------------------------

//2. Interface in another Interface
	//access specifier is public even if we have not written public.
	
// Java program to demonstrate working of  
// interface inside another interface. 

import java.util.*; 

interface Test 
{ 
   interface Yes 
   { 
      void show();     //only public
   } 
} 
  
class Testing implements Test.Yes 
{ 
   public void show() 
   { 
      System.out.println("show method of interface"); 
   }  
}  
  
class A 
{ 
   public static void main(String[] args) 
   { 
     Test.Yes obj; 
     Testing t = new Testing(); 
     obj = t; 
     obj.show(); 
   }  
} 

//show method of interface 


//----------------------
