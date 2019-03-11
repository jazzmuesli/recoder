package Generics;

public class LinkedList<E extends Number> {

	private E value;
	private LinkedList<E> next;

	public LinkedList() {
	}

	public boolean isEmpty() {
		return value == null;
	}

	public void add(E value) {

		if(isEmpty()) {
			this.value = value;

			next = new LinkedList<E>();
		}
		else
			next.add(value);
	}

	public E get(int index) {
		if(index < 0 || isEmpty())
			throw new IndexOutOfBoundsException();

		if(index == 0)
			return value;

		return next.get(index - 1);
	}

	public int size() {
		if(isEmpty())
			return 0;

		return 1 + next.size();
	}
	
	public LinkedList<E> deepClone() {
		return this;
	}
}