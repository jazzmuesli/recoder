package generic;

import java.util.*;

public class SubSubClass extends SubClass implements FirstInterface, SecondInterface {
	public SubSubClass() {
		elements = new ArrayList<String>();
	}
	
	public SubSubClass(List<String> l) {
		elements = l;
	}
	
	public ArrayList<String> getElements() {
		return elements;
	}
	
	public ArrayList<String> multipleInheritance() {
		return elements;
	}
	
	public SubSubClass clone() {
		return new SubSubClass((List<String>)((ArrayList)elements).clone());
	}
}