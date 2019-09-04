import java.util.ArrayList; 
import java.util.Collections; 

public class SortArr { 
	public static void main(String[] args) { 
		ArrayList<Person> lst = new ArrayList<Person>(); 

		lst.add(new Person("yui", 17,40.0)); 
		lst.add(new Person("haruko", 44,50.0)); 
		lst.add(new Person("natsu", 44,35.4)); 
		lst.add(new Person("aki", 17,50.3)); 
		lst.add(new Person("masamune", 48,75.1)); 

		System.out.println(lst); 
		Collections.sort(lst, new PersonComparator()); 
		System.out.println(lst); 
	} 
} 

class Person { 
	private String name; 
	private int age; 
	private double weight;

	public Person(String n, int a, double w) { 
		name = n; 
		age = a; 
		weight = w;
	} 

	public int getAge() { 
		return age; 
	} 

	public String getName() { 
		return name; 
	} 
	
	public double getWeight() {
		return weight;
	}
	
	public String toString() { 
		return "<" + getName() + " " + getAge() + " " +getWeight() + ">"; 
	} 
} 

//
class PersonComparator implements java.util.Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		int compNum = 0;
		boolean isDesc = true;
		if (o1.getAge() == o2.getAge()){
			if(o1.getWeight() == o2.getWeight()) compNum = 0;
			else if(o1.getWeight() > o2.getWeight()) compNum = 1;
			else if(o1.getWeight() < o2.getWeight()) compNum = -1;
		}else if(o1.getAge() < o2.getAge()) compNum = -1;
		else if(o1.getAge() > o2.getAge()) compNum = 1;
		return compNum * (isDesc?-1:1);
	}
}