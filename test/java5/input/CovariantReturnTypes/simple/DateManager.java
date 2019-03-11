package simple;

import java.util.*;

public class DateManager {
	Calendar c;
	GregorianCalendar gc;
	SuperClass supC;
	SubClass subC;
	
	public static void main(String[] args) {
		supC = new SuperClass();
		subC = new SubClass();
		
		c = supC.now();
		gc = subC.now();
		int i = gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR);
	}
}