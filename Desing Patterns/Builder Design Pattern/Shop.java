/*
Builder Design Pattern in Java :

	Builder design pattern belongs to Creational design pattern which is a type of Design Patterns in java.
	Builder design pattern is a creational design pattern like Factory Pattern and Abstract Factory Pattern.
	Builder pattern builds a complex object using simple objects and uses step by step approach.
	A Builder class builds the final object step by step. This builder is independent of other objects.

 
 */


public class Shop
{
	public static void main(String[] args) {
		Phone p = new Phone("Android", 2, "snapdragon", 5.5, 50000);
		System.out.println(p);
		
	//Builder pattern builds a complex object using simple objects and uses step by step approach. 
		
		//Here all parameters are not compulsory. 
		PhoneBuilder builder = new PhoneBuilder().setOs("Android").setRam(4).setBattery(6000);
		System.out.println(builder);
		

	}
}