/**
 * Created on 19 mar 2010
 */
package recoder.miniexamples;

import java.io.File;
import java.util.EventObject;
import java.util.List;

import recoder.CrossReferenceServiceConfiguration;
import recoder.abstraction.ClassType;
import recoder.convenience.ForestWalker;
import recoder.io.PropertyNames;
import recoder.io.SourceFileRepository;
import recoder.java.CompilationUnit;
import recoder.java.ProgramElement;
import recoder.java.expression.operator.Equals;
import recoder.kit.UnitKit;
import recoder.service.ErrorHandler;
import recoder.service.SourceInfo;

/**
 * @author Tobias Gutzmann
 *
 */
public class ProblematicStringComparisons {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CrossReferenceServiceConfiguration crsc = new CrossReferenceServiceConfiguration();
		SourceFileRepository dsfr = crsc.getSourceFileRepository();
		String p = args[0];
		for (int i = 1; i < args.length; i++) {
			p += File.pathSeparator + args[i];
		}
		crsc.getProjectSettings().setProperty(PropertyNames.INPUT_PATH, p);
		crsc.getProjectSettings().ensureSystemClassesAreInPath();
		crsc.getProjectSettings().setErrorHandler(new ErrorHandler() {
			public void modelUpdating(EventObject event) {
				// ignore.
			}
			
			public void modelUpdated(EventObject event) {
				// ignore.
			}
			
			public void setErrorThreshold(int maxCount) {
				// ignore.
			}
			
			public void reportError(Exception e) throws RuntimeException {
				// ignore.
			}
			
			public int getErrorThreshold() {
				return Integer.MAX_VALUE;
			}

			public int getErrorCount() {
				return 0;
			}
		});
		ClassType string = crsc.getNameInfo().getJavaLangString();
		SourceInfo si = crsc.getSourceInfo();
		List<CompilationUnit> cus = dsfr.getAllCompilationUnitsFromPath();
		ForestWalker fw = new ForestWalker(cus);
		while (fw.next()) {
			ProgramElement pe = fw.getProgramElement();
			if (pe instanceof Equals) {
				Equals eq = (Equals)pe;
				if (si.getType(eq.getExpressionAt(0)) == string && si.getType(eq.getExpressionAt(1)) == string)
					printLocation(eq);
			}
		}
	}
	private static void printLocation(ProgramElement pe) {
		System.out.println(UnitKit.getCompilationUnit(pe).getPrimaryTypeDeclaration().getFullName() + " line " + pe.getStartPosition().getLine() + " col " + pe.getStartPosition().getColumn());
	}

}
