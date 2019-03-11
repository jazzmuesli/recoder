package generic;

import java.util.*;

public class SuperClass {
	
	protected List<String> elements;
	
	public SuperClass() {
		elements = new AbstractCollection<String>();
	}
	
	public SuperClass(List<String> l) {
		elements = l;
	}
	
	public Object[] getArray() {
		return new Object[10];
	}
	
	public Collection<String> getElements() {
		return elements;
	}
	
	public void addElement(String s) {
		elements.add(s);
	}
	
	public <T> T getElement(int i, List<T> l) {
		return l.get(i);
	}

	public <T> Collection<T> genericMethod(T[] array, Collection<T> c) {
		for (T t : array) {
			c.add(t);
		}
		return c;
	}
	
	public SuperClass clone() {
		List<String> l = new AbstractCollection();
		for (String str : elements) {
			l.add(str);
		}
		return new SuperClass(l);
	}
	
	public void anything() {
		
	}
}