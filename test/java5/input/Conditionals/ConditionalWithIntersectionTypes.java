import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Queue;
import java.util.PriorityQueue;

public class ConditionalWithIntersectionTypes {

	public void testConditionals() {
		List<String> stringList = new ArrayList<String>();
		Set<String> stringSet = new TreeSet<String>();
		Queue<String> stringQueue = new PriorityQueue<String>();
		boolean list = false;
		boolean set = true;
		
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
		
		stringQueue.add("this");
		stringQueue.add("is");
		stringQueue.add("a");
		stringQueue.add("string");
		stringQueue.add("queue");
		
		Iterable<String> coll = list ? stringList : set ? stringSet : stringQueue;
	}
}