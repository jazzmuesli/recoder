package generic;

import java.util.*;

public class ListManager {
	SuperClass supC;
	SubClass subC;
	SubSubClass subSubC;
	
	public static void main(String[] args) {
		supC = new SuperClass();
		subC = new SubClass();
		subSubC = new SubSubClass();
		
		supC.addElement("e1");
		subC.addElement("e1");
		subSubC.addElement("e1");
		supC.addElement("e2");
		subC.addElement("e2");
		subSubC.addElement("e2");
		
		Collection<String> l = supC.getElements();
		AbstractList<String> al = subC.getElements();
		ArrayList<String> arrl = subSubC.getElements();
		
		SubSubClass subSubC2 = subSubC.clone().clone();
		ArrayList<String> arrl2 = new ArrayList<String>();
		arrl2.add("Array");
		arrl2.add("List");
		arrl2 = ((ArrayList<String>)arrl2.clone()).clone();
	}
}