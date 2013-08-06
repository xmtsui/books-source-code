package numbercruncher.program5_3;

import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.ImprovedRegulaFalsiRootFinder;
import numbercruncher.mathutils.AlignRight;
import numbercruncher.rootutils.RootFunctions;

/**
 * PROGRAM 5-3: Improved Regula Falsi Algorithm
 *
 * Demonstrate the Improved Regula Falsi Algorithm on a function.
 */
public class ImprovedRegulaFalsiAlgorithm
{
    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        try {
            ImprovedRegulaFalsiRootFinder finder =
                new ImprovedRegulaFalsiRootFinder(
                        RootFunctions.function("x^2 - 4"),
                                               -0.25f, 3.25f);

            AlignRight ar = new AlignRight();

            ar.print("n", 2);
            ar.print("xNeg", 10); ar.print("f(xNeg)", 13);
            ar.print("xFalse", 10); ar.print("f(xFalse)", 13);
            ar.print("xPos", 10); ar.print("f(xPos)", 13);
            ar.underline();

            // Loop until convergence or failure.
            boolean converged;
            do {
                converged = finder.step();

                ar.print(finder.getIterationCount(), 2);
                ar.print(finder.getXNeg(), 10);
                ar.print(finder.getFNeg(), 13);
                ar.print(finder.getXFalse(), 10);
                ar.print(finder.getFFalse(), 13);
                ar.print(finder.getXPos(), 10);
                ar.print(finder.getFPos(), 13);
                ar.println();
            } while (!converged);

            System.out.println("\nSuccess! Root = " +
                               finder.getXFalse());
        }
        catch(Exception ex) {
            System.out.println("***** Error: " + ex);
        }
    }
}
/*
Output:
 n      xNeg      f(xNeg)    xFalse    f(xFalse)      xPos      f(xPos)
-----------------------------------------------------------------------
 1     -0.25      -3.9375    1.0625   -2.8710938      3.25       6.5625
 2    1.0625   -2.8710938 2.0833335   0.34027863      3.25      3.28125
 3    1.0625   -2.8710938 1.9751655 -0.098721266 2.0833335   0.34027863
 4 1.9751655 -0.098721266   1.99949 -0.002039671 2.0833335   0.34027863
 5   1.99949 -0.002039671 2.0004833 0.0019330978 2.0833335   0.17013931
 6   1.99949 -0.002039671       2.0          0.0 2.0004833 0.0019330978

Success! Root = 2.0
*/