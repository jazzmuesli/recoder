import java.util.*;
import java.lang.String;

public class EnhancedFor {
	public static void main(String[] args) {
		String[] stringArray = {"this", "is", "a", "string", "array"};
		List<String> stringList = new ArrayList<String>();
		Set<String> stringSet = new TreeSet<String>();
		List stringList2 = new Vector();
		boolean list = true;
		
		stringList.add("this");
		stringList.add("is");
		stringList.add("a");
		stringList.add("string");
		stringList.add("list");
		
		stringSet.add("this");
		stringSet.add("is");
		stringSet.add("a");
		stringSet.add("string");
		stringSet.add("set");
		
		stringList2.add("this");
		stringList2.add("is");
		stringList2.add("type-unsafe");
		stringList2.add("string");
		stringList2.add("list");
		
		for (String s : stringArray) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		for (String s : stringList) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		for (String s : stringList2) {
			System.out.println(s + " ");
		}
		System.out.println();
		
		for (String s : list ? stringList : stringSet) {
			System.out.println(s + " ");
		}
		System.out.println();
		
		for (String s : !list ? stringArray : stringArray) {
			System.out.println(s + " ");
		}
		System.out.println();
	}
	
}