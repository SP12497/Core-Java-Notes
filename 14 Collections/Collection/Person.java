package Collection;

public class Person extends Object
{

	  int id;
	  String name;
	  
	  
	   
	public Person(int id, String name) {
		
		this.id = id;
		this.name = name;
	}


  
	void disp()
	{
		System.out.println("	"+id + " "+name);		
	}
	
	public String toString() {
		return "	Person [id=" + id + ", name=" + name + "]";
	}
}