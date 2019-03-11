package generic;

import java.util.*;

public class SubClass extends SuperClass {
	
	public SubClass() {
		elements = new AbstractList<String>();
	}
	
	public SubClass(List<String> l) {
		elements = l;
	}
	
	public AbstractList<String> getElements() {
		return elements;
	}
	
	public String[] getArray() {
		return new String[10];
	}
	
	public <T> T getElement(int i, List<T> l) {
		return l.get(i);
	}
	
	public SubClass clone() {
		List<String> l = new AbstractList();
		for (String str : elements) {
			l.add(str);
		}
		return new SubClass(l);
	}
	
	public void anything() {
		
	}
}