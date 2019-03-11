package recoder.backport;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.EventObject;
import java.util.List;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ParserException;
import recoder.io.PropertyNames;
import recoder.io.SourceFileRepository;
import recoder.java.CompilationUnit;
import recoder.java.declaration.TypeDeclaration;
import recoder.kit.transformation.java5to4.EnhancedFor2For;
import recoder.kit.transformation.java5to4.FloatingPoints;
import recoder.kit.transformation.java5to4.MakeConditionalCompatible;
import recoder.kit.transformation.java5to4.RemoveAnnotations;
import recoder.kit.transformation.java5to4.RemoveCoVariantReturnTypes;
import recoder.kit.transformation.java5to4.RemoveStaticImports;
import recoder.kit.transformation.java5to4.ReplaceEnums;
import recoder.kit.transformation.java5to4.ResolveBoxing;
import recoder.kit.transformation.java5to4.ResolveGenerics;
import recoder.kit.transformation.java5to4.ResolveVarArgs;
import recoder.kit.transformation.java5to4.methodRepl.ApplyRetrotranslatorLibs;
import recoder.kit.transformation.java5to4.methodRepl.ReplaceOthers;
import recoder.service.ErrorHandler;
import recoder.service.SemanticsChecker;

public class Backport {

	/**
	 * @param args
	 */
	public static void main(String ... args) {
		if (args.length != 2 && args.length != 3) {
			System.out.println("Usage: backport.Backport input-path output-path [path-to-libraries]");
			System.out.println("WARNING: If target files exist, they will be overwritten without further notice!");
			System.out.println("Current runtime classes will be used if no rt.jar is specified in input path");
			System.out.println("The optional parameter path-to-libraries specifies where to find\n " +
					"\tretrotranslator-runtime-1.2.9.jar and backport-util-concurrent-3.1.jar.\n\tDefaults to lib/");
			System.out.println("See http://recoder.sourceforge.net for more details");
			return;
		}
		String pathToLibs = args.length == 3 ? args[2] : "lib";

		CrossReferenceServiceConfiguration crsc = new CrossReferenceServiceConfiguration();
		SourceFileRepository dsfr = crsc.getSourceFileRepository();
		
		String outPath = args[1];
		if(!(new File(outPath).isDirectory())) {
			if (!new File(outPath).mkdirs()) {
				System.out.println("ERROR: specified output-path is not a directory" +
						" and could not be created either");
				return;
			}
		}

		String inputPath = args[0];
		inputPath += File.pathSeparator + 
			new File(pathToLibs + "/retrotranslator-runtime-1.2.9.jar").getAbsolutePath() + File.pathSeparator + 
			new File(pathToLibs + "/backport-util-concurrent-3.1.jar").getAbsolutePath(); 
		
		crsc.getProjectSettings().setProperty(PropertyNames.INPUT_PATH, inputPath);
		crsc.getProjectSettings().setProperty(PropertyNames.OUTPUT_PATH, outPath);
		crsc.getProjectSettings().setProperty(PropertyNames.JAVA_5, "true");
        crsc.getProjectSettings().setProperty(PropertyNames.TABSIZE, "4");
     	if (!crsc.getProjectSettings().ensureSystemClassesAreInPath()) {
     		System.out.println("\tWarning: Cannot find system classe (rt.jar)");
     		System.out.println("\tThis will likely cause an error, unless you are");
     		System.out.println("\ttrying to transform the JDK itself. Please make sure");
     		System.out.println("\tthat java.home is set, or specify an rt.jar in the");
     		System.out.println("\tinput classpath.");
     	}
        crsc.getProjectSettings().ensureExtensionClassesAreInPath();
     	
     	System.out.println("Now parsing source files. This may take a while...");
     	
        try {
            dsfr.getAllCompilationUnitsFromPath(new FilenameFilter()  {
    			@Override
    			public boolean accept(File dir, String name) {
    				if (dir == null)
    					return false; // from archive, ignore in our case.
    				return name.endsWith(".java");
    			}
    		});
            System.out.println("Parsing done. Now updating model. This may take a while... [1/14]");
            crsc.getChangeHistory().updateModel();
        } catch (ParserException pe) {
            System.out.println(pe.getMessage());
            System.out.println("Parse error. Aborting.");
            return;
        }
        List<CompilationUnit> cul = dsfr.getCompilationUnits();
        for (CompilationUnit cu : cul) {
        	// just to make sure...
        	cu.validateAll();
        }
        // Make sure that there are no semantic errors. 
        new SemanticsChecker(crsc).checkAllCompilationUnits();
        if (crsc.getProjectSettings().getErrorHandler().getErrorCount() > 0) {
        	System.err.flush();
        	System.out.println("There are compile errors. Transformations cannot be performed.");
        	return;
        }
        System.out.println("----------------------------");
        System.out.println("Beginning transformations...");
		
		System.out.println("Conditionals [2/14]");
    	MakeConditionalCompatible mcc = new MakeConditionalCompatible(crsc, cul);
    	mcc.execute();
    	
    	System.out.println("Enhanced For [3/14]");
    	EnhancedFor2For eff = new EnhancedFor2For(crsc, cul);
    	eff.execute();
    	
    	System.out.println("Generics [4/14]");
    	ResolveGenerics rg = new ResolveGenerics(crsc, cul);
    	rg.execute();
    	
    	System.out.println("Covariant Return Types [5/14]");
    	RemoveCoVariantReturnTypes rc = new RemoveCoVariantReturnTypes(crsc, cul);
    	rc.execute();
    	
    	System.out.println("Annotations [6/14]");
    	RemoveAnnotations ra = new RemoveAnnotations(crsc, cul);
    	ra.execute();
    	
    	System.out.println("Static Imports [7/14]");
    	RemoveStaticImports rsi = new RemoveStaticImports(crsc, cul);
    	rsi.execute();
    	
    	System.out.println("Varargs [8/14]");
    	ResolveVarArgs rva = new ResolveVarArgs(crsc, cul);
    	rva.execute();
    	
    	System.out.println("Boxing [9/14]");
    	ResolveBoxing rb = new ResolveBoxing(crsc, cul);
    	rb.execute();
    	
    	System.out.println("Boxing 2 (hot fix for a rare bug) [10/14]");
    	ResolveBoxing rb2 = new ResolveBoxing(crsc, cul);
    	rb2.execute();
    	
    	System.out.println("Enumerations [11/14]");
    	ReplaceEnums re = new ReplaceEnums(crsc);
    	re.execute();
    	
    	System.out.println("Hexadecimal floating points [12/14]");
    	new FloatingPoints(crsc, cul).execute();
    	
    	if (crsc.getNameInfo().getClassType("java.util.Collections") instanceof TypeDeclaration) {
    		System.out.println("Skipping remaining transformations (API replacements). " +
    				"Transformed sources seem to be part of the JDK.");
    	} else {
    		System.out.println("Replacing StringBuilder with StringBuffer... [13/14]");
    		ReplaceOthers ro = new ReplaceOthers(crsc);
    		ro.execute();

    		crsc.getProjectSettings().setErrorHandler(new ErrorHandler() {
    			private int errCnt = 0;
				public int getErrorThreshold() {
					throw new UnsupportedOperationException();
				}
				public void reportError(Exception e) throws RuntimeException {
			        System.err.println(e.getMessage());
			        errCnt++;
				}
				public void setErrorThreshold(int maxCount) {
					throw new UnsupportedOperationException();
				}
				public void modelUpdated(EventObject event) {
					if (errCnt > 0) {
						System.err.flush();
						System.out.println("Errors occured while replacing API calls, check messages above.");
						System.out.println("This should be solvable by manually changing the transformed source code files.");
					}
				}
				public void modelUpdating(EventObject event) {
					// ignore
				}
				public int getErrorCount() {
					return errCnt;
				}
			});
    		System.out.println("Replacing library references... [14/14]");
    		ApplyRetrotranslatorLibs arl = new ApplyRetrotranslatorLibs(crsc, pathToLibs);
    		arl.execute();
    	}
    	System.out.println("Done transforming. Now writing back files.");
    	
        try {
            dsfr.printAll(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        	System.out.println("IOException while writing back files...");
        	return;
        }
        System.out.println("Done.");
	}

}
