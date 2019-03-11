import java.util.Vector;

class SysOutTest {
	void foo(Vector v, Vector w){
		int i;
		for(;;)
			System.out.println(i+":\t"+v.elementAt(i)+"\t"+w.elementAt(i));
	}
}
