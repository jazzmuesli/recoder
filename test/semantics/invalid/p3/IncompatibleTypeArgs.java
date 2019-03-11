package invalid.p3;

import java.util.ArrayList;

public class IncompatibleTypeArgs {
	{
		ArrayList<String> al = new ArrayList<Object>();
	}
}
