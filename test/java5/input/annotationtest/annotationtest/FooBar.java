package annotationtest;

import java.lang.annotation.*;
/** * Indicates that the annotated method is a test method. * This annotation should be used only on parameterless static methods. */@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)public @interface TestAnnotation { }

public class FooBar {    @Test public static void m1() { }    public static void m2() { }    @Test public static void m3() {        throw new RuntimeException("Boom");    }    public static void m4() { }    @Test public static void m5() { }    public static void m6() { }    @Test public static void m7() {        throw new RuntimeException("Crash");    }    public static void m8() { }}