package annotationtest;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
public @interface TestTargetAnnotation {
	int value();
}

public class Foo {
	@TestTargetAnnotation(value = 0) public static void m1() {}
}