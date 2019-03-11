import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.AbstractList;

public class Boxing {
	public static void main(String[] args) {
		Map<String, Integer> m = new TreeMap<String, Integer>();
		Integer i = new Integer(3);
		i++;
		i = 3;
		i = (true ? 1 : i);
		int k = 4;
		k = i;
		k = getInt(k);
		i = getInteger(i);
		switch (i) {
		case 1: {System.out.println("1"); break;}
		case 2: {System.out.println("2"); break;}
		default: {System.out.println("default"); break;}
		}
		
		assert i != null : "i is null";
		assert k == i : "k is not equal i";
		assert k == 3 : "k is not 3";
		
		int[] iArr = {k, i};
		Integer[] integerArr = {k, i};
		k = integerArr[1];
		i = iArr[i];
		
		for (String word : args) {
			Integer freq = m.get(word);
			m.put(word, (freq == null ? 1 : freq + 1));
		}
		System.out.println(m);
	}
   
   public static List<Integer> asList(final int[] a) {
	   return new AbstractList<Integer>() {
	        public Integer get(int i) { return a[i]; }
	        // Throws NullPointerException if val == null
	        public Integer set(int i, Integer val) {
	            Integer oldVal = a[i];
	            a[i] = val;
	            return oldVal;
	        }
	        public int size() { return a.length; }
	    };
	}
   
   public static int getInt(Integer i) {
	   return i;
   }
   
   public static Integer getInteger(int i) {
	   return i;
   }
}