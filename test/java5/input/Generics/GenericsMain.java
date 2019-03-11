package Generics;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;

public class GenericsMain {
	public static double sum(List<? extends Number> numList) {
		double result = 0;
		for(int i=0;i<numList.size();i++)
			result += numList.get(i).doubleValue();
		return result;
	}
	
	static <T> void fromArrayToCollection(T[] a, Collection<T> c) 
	{ 
		for (T o : a) 
		{ 
			c.add(o);
		}
	}
	
	public static void main(String[] args) {
		Generics.LinkedList<Integer> l = new Generics.LinkedList<Integer>();
		HashMap<String, Integer> dictionary = new HashMap<String, String>();
		List<List<String>> nestedList = new ArrayList<ArrayList<String>>();
		
		dictionary.put("eins", new Integer(1));
		dictionary.put("zwei", new Integer(2));
		Integer ii = dictionary.get("eins");
		Set<String> ss = dictionary.keySet();
		System.out.println(ss.size());
		
		l.add(new Integer(33));
		l.add(new Integer(42));
		System.out.println(l.deepClone().get(0));
		Integer i = l.get(0);
		
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(new Integer(1));
		ls.add(new Integer(2));
		ls.add(new Integer(3));
		
		double sum = sum(ls);
		
		List<String> stringList = new ArrayList<String>();
		String[] stringArray = {"string", "array", "to", "string", "list"};
		fromArrayToCollection(stringArray, stringList);
		System.out.println(stringList.get(0).substring(0));
		nestedList.add(stringList);
		System.out.println(nestedList.get(0).set(0, "test"));
		stringList = new java.util.LinkedList<String>();
		
		NonGeneric ng = new NonGeneric();
		System.out.println(ng.getList().get(0).substring(0));
		
		Iterator<String> iter = ng.getList().iterator();
		String res = "";
		while (iter.hasNext()) {
			res += iter.next();
		}
	}
}