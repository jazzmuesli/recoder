package invalid.p4_2;

public class InvalidInnerClassInstantiation {
   class Inner {}
   static Object o = new InvalidInnerClassInstantiation.Inner();
}
