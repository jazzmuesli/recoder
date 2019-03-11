public class Nested {
	int foo(Object o) {
		return -1;
	}
	String bar() {
		return null;
	}
	void foobar() {
		if (true)
			foo(bar());
	}
	int f = foo(bar());
}
