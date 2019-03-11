package invalid.p1;

public class InvalidGenericsUse<T> {
	class B<U> {
		InvalidGenericsUse.B<String> a;
	}
}
