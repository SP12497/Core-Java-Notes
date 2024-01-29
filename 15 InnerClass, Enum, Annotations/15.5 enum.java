/*
	- An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
	- An enumeration is a user-defined data type that consists of integral constants
	
	CPP : 
		enum week { Sunday, Monday = 5, Tuesday, Wednesday };
		
	Java :
		enum week { Sunday, Monday(5), Tuesday, Wednesday  };


  // It is actual implementation of Enum
class Mobile
{
	static final Mobile Apple = new Mobile();
	static final Mobile Samsung = new Mobile();
	
	int price;
	
	public int getPrice()
	{
		return price;
	}
}
*/

class CA{}
interface IA{}

//enum Mobile  extends Enum 	//enum by default extends Enum Class so we can not multiple Inherit with it.
//enum Mobile extends CA  //Error , multiple Inheritance not allowed
//enum Mobile implements IA 	// Allows
enum Mobile
{
	Apple, Samsung(200),Realme;   // These are the objects	//Mobile constructor is called for all objects // by default static final
	
	int price;
	
	Mobile()	// Constructor in Enum
	{
		price=100;
		System.out.println("parameterless constructor called");
	}
	Mobile(int x)	// Constructor in Enum
	{
		price=x;
		System.out.println("parameterised constructor called");
	}

	
	public int getPrice()
	{
		return price;
	}	
}

public class CMain
{
	public static void main(String[] args) 
	{
		
		System.out.println(Mobile.Samsung.getPrice());	//200

		
		Mobile m = Mobile.Apple;	//obj1 = obj2
		Mobile.Apple.price= 10000;
		
		System.out.println(Mobile.Samsung.getPrice());	//200
		System.out.println(Mobile.Realme.getPrice());	//100
		
		switch (m) 
		{
			case Apple:
				System.out.println("In apple : " + m.getPrice());	//10000
				break;
			case Samsung:
				System.out.println("In Samsung : " + m.getPrice());
				break;
			case Realme:
				System.out.println("In Realme : " + m.getPrice());

			//case Tiger:	// Switch only allows enum Objects.
		}
	}
}