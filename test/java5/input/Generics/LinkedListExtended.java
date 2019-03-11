package Generics;

public class LinkedListExtended<E extends Number & Comparable<E>> extends LinkedList<E> {
	private E value;
	private LinkedListExtended<E> next;
	
	public LinkedListExtended() {
		super();
	}
	
	public void add(E value) {

		if(isEmpty()) {
			this.value = value;

			next = new LinkedListExtended<E>();
		}
		else
			next.add(value);
	}
	
	public E max() {
		if(isEmpty())
			return null;

		E nextmax = next.max();

		if(nextmax == null)
			return value;

		return value.compareTo(nextmax) < 0 ? nextmax : value;
	}
}