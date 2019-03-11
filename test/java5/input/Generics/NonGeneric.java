package Generics;

import java.util.ArrayList;

public class NonGeneric {
	private ArrayList<String> list;
	
	public NonGeneric() {
		list = new ArrayList<String>();
		list.add("array");
		list.add("list");
		list.add("of");
		list.add("strings");
	}
	
	public ArrayList<String> getList() {
		return list;
	}
}