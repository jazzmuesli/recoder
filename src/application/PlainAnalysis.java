package application;
import java.util.Properties;

import recoder.CrossReferenceServiceConfiguration;
import recoder.io.DefaultClassFileRepository;
import recoder.io.DefaultSourceFileRepository;
import recoder.service.DefaultCrossReferenceSourceInfo;
import recoder.service.DefaultNameInfo;

/**
 * Yet another example, produces output:
 * 11007 variables with 43219 references
3397 methods with 20882 references
807 constructors with 2873 references
657 types with 31902 references
42 packages with 10702 references

Memory used: 46950968 (total: 338165760)
 *
 */
public class PlainAnalysis {

    public static void main(String[] args) {
	CrossReferenceServiceConfiguration sc = 
	    new CrossReferenceServiceConfiguration();
	System.out.println("Importing Initial Project Files...");
	// PR: add bootClassPath otherwise 
	String bootClassPath = System.getProperty("sun.boot.class.path");
	if (bootClassPath == null) {
		throw new IllegalArgumentException("boot classpath not defined: "+ bootClassPath + ", where is rt.jar with java.lang.Object?");
	}
	sc.getProjectSettings().getSearchPathList().add(bootClassPath);
	RecoderProgram.setup(sc, PlainAnalysis.class, args);
	System.out.println("\nSystem Settings...");
	Properties props = sc.getProjectSettings().getProperties();
	String key;
	key = "input.path";
	System.out.println(key + "=" + props.getProperty(key));
	key = "error.threshold";
	System.out.println(key + "=" + props.getProperty(key));
	key = "jdk1.4";
	System.out.println(key + "=" + props.getProperty(key));
	System.out.println();
	// System.out.println("\nAnalyzing System...");
	sc.getChangeHistory().updateModel();
	System.out.println("\nFiles...");
	System.out.println(((DefaultSourceFileRepository)sc.getSourceFileRepository()).information());
	System.out.println(((DefaultClassFileRepository)sc.getClassFileRepository()).information());
	
	System.out.println("\nNames...");
	System.out.println(((DefaultNameInfo)sc.getNameInfo()).information());
	System.out.println("\nReferences...");
	System.out.println(((DefaultCrossReferenceSourceInfo)sc.getCrossReferenceSourceInfo()).information());
	System.out.println();
	
	long totalMem = Runtime.getRuntime().totalMemory();
	long usedMem = recoder.util.Debug.getUsedMemory();	
	System.out.println("Memory used: " + usedMem + 
			   " (total: " + totalMem + ")");
    }

}
