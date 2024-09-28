/*
ABSTRACT :
	https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html#:~:text=An%20abstract%20class%20is%20a,but%20they%20can%20be%20subclassed.&text=When%20an%20abstract%20class%20is,methods%20in%20its%20parent%20class.

	- An abstract class is a class that is declared abstract—it may 
		or may not include abstract methods. 
	- Abstract classes cannot be instantiated, but they can be subclassed.

	abstract class AmolPhone
	abstract class BabanPhone extends AmolPhone
	class ChetanPhone extends BabanPhone
	class CharliePhone extends BabanPhone
*/

abstract class AmolPhone {
	public void call() {
		System.out.println("Amol Calling...");
	}

	// public abstract void chat() {// Error: We can not define abstract method. we can only declare;
	// 	System.out.println("Amol chatting...");
	// }

	public void play() {
		System.out.println("Amol Playing...");
	}

	public abstract void text(); // Here, 2 unimplemented methods. So mark class as abstract

	abstract void watch();
}

abstract class BabanPhone extends AmolPhone // Here one more abstract method which is unimplemented or not defined , so
											// mark this class is also abstract.
{
	public void text() {
		System.out.println("Baban Texting");
	}

	// public abstract void watch(); is unimplemnted here. so mark class as abstract.
}

class ChetanPhone extends BabanPhone // Till here all abstract methods are defined.
{
	public void watch() {
		System.out.println("Watching... by Chetan");
	}

	public void classname() {
		System.out.println("In ChetanPhone class ");
	}
}

class CharliePhone extends BabanPhone // Till here all abstract methods are defined.
{
	public void watch() {
		System.out.println("Watching... by Charlie");
	}
}

// main
public class _8_17_AbstractClassMethods {
	public static void main(String[] args) {
		// AmolPhone obj = new AmolPhone(); // we cannot create object of abstract class with abstract class constructor.

		System.out.println("__________________OBJ1______________________");
		AmolPhone obj1 = new ChetanPhone(); // we can access only those methods which are completely defines in
											// AmolPhone class.
		obj1.call();
		obj1.play();
		obj1.text();
		obj1.watch();
		// obj1.classname(); // we can access only those methods which are defines in AmolPhone class.

		System.out.println("_____________________OBJ2______________________");
		ChetanPhone obj2 = new ChetanPhone();
		obj2.classname();
		obj2.call();
		obj2.play();
		obj2.text();
		obj2.watch();

		System.out.println("_____________________OBJ3______________________");

		CharliePhone obj3 = new CharliePhone();
		obj3.watch();

		// Dynamic Method Dispatch / Run time PolyMorphism :
		System.out.println("__________________show(OBJ2)______________________");
		// show(obj2);
		show(obj2); // ChetanPhone
		System.out.println("__________________show(OBJ3)______________________");
		show(obj3); // CharliePhone

		System.out.println("___________Anonymous Inner Type (Object)________");
		BabanPhone bb = new BabanPhone() {		// Annonymous Class
			@Override
			void watch() {
				System.out.println("anonymous watch");

			}
		};
		bb.watch();
	}
	/*
	 * public static void show(ChetanPhone obj) { obj.watch(); }
	 */

	public static void show(AmolPhone obj) {
		obj.watch();
	} // create parameter of parent abstract class, and call Baban or Chetan by
		// passing object of it.
}
