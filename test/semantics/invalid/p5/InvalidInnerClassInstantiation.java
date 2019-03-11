package invalid.p5;

public class InvalidNonInnerClassInstantiation {
   static class Inner {}
}

class C { Object o = new InnerClassInstantiation().new Inner(); }
