package a;

import static java.lang.System.err;
import static a.Helper.err;
//import static a.Helper.doesnotexist; // compiler error, but recoder ignores this
import static java.lang.System.out;
import static a.Helper.*;


public class StaticImports {
    public void foo() {
        System.out.println(err == null); // ambiguity (error 1)
        err(); // this should work
        out.println(""); // should work => System.out
        
        PublicMemberClass pumc = new PublicMemberClass();  // ok
        ProtectedMemberClass prmc = new ProtectedMemberClass(); // ok
        PackageMemberClass pamc = new PackageMemberClass();  // ok
        PrivateMemberClass privmc = new PrivateMemberClass(); // fail (error 2+3+4)

        NonStaticInnerClass nmsic = new NonStaticInnerClass(); // fail (error 5+6+7)
        
        publicInt++;
        protectedInt++;
        privateInt++; // fail (error 8)
        packageInt++;
        
        publicFoo();
        protectedFoo();
        privateFoo(); // fail (error 9)
        packageFoo();
    }
}