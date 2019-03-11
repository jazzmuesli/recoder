package invalid.p2;

import java.util.ArrayList;

public class WrongNumberOfTypeParameters {
	{
		ArrayList<String> al = new ArrayList<String, String>();
	}
}
