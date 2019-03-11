package invalid.p4;

public class InvalidInnerClassInstantiation {
   class Inner {}
}

class C { Object o = new InvalidInnerClassInstantiation.Inner(); }
