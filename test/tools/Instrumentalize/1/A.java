class A {
	A foo() {
		return this;
	}
	void bar() {
		this.foo().foo();
	}
}