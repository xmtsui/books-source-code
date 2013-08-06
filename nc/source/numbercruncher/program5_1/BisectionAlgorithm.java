package numbercruncher.program5_1;

import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.BisectionRootFinder;
import numbercruncher.mathutils.AlignRight;
import numbercruncher.rootutils.RootFunctions;

/**
 * PROGRAM 5-1: Bisection Algorithm
 *
 * Demonstrate the Bisection Algorithm on a function.
 */
public class BisectionAlgorithm
{
    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        try {
            BisectionRootFinder finder =
                new BisectionRootFinder(
                        RootFunctions.function("x^2 - 4"),
                                               -0.25f, 3.25f);

            AlignRight ar = new AlignRight();

            ar.print("n", 2);
            ar.print("xNeg", 10); ar.print("f(xNeg)", 14);
            ar.print("xMid", 10); ar.print("f(xMid)", 14);
            ar.print("xPos", 10); ar.print("f(xPos)", 13);
            ar.underline();

            // Loop until convergence or failure.
            boolean converged;
            do {
                converged = finder.step();

                ar.print(finder.getIterationCount(), 2);
                ar.print(finder.getXNeg(), 10);
                ar.print(finder.getFNeg(), 14);
                ar.print(finder.getXMid(), 10);
                ar.print(finder.getFMid(), 14);
                ar.print(finder.getXPos(), 10);
                ar.print(finder.getFPos(), 13);
                ar.println();
            } while (!converged);

            System.out.println("\nSuccess! Root = " +
                               finder.getXMid());
        }
        catch(Exception ex) {
            System.out.println("***** Error: " + ex);
        }
    }
}
/*
Output:
 n      xNeg       f(xNeg)      xMid       f(xMid)      xPos      f(xPos)
-------------------------------------------------------------------------
 1     -0.25       -3.9375       1.5         -1.75      3.25       6.5625
 2       1.5         -1.75     2.375      1.640625      3.25       6.5625
 3       1.5         -1.75    1.9375   -0.24609375     2.375     1.640625
 4    1.9375   -0.24609375   2.15625    0.64941406     2.375     1.640625
 5    1.9375   -0.24609375  2.046875    0.18969727   2.15625   0.64941406
 6    1.9375   -0.24609375 1.9921875  -0.031188965  2.046875   0.18969727
 7 1.9921875  -0.031188965 2.0195312    0.07850647  2.046875   0.18969727
 8 1.9921875  -0.031188965 2.0058594   0.023471832 2.0195312   0.07850647
 9 1.9921875  -0.031188965 1.9990234 -0.0039052963 2.0058594  0.023471832
10 1.9990234 -0.0039052963 2.0024414   0.009771347 2.0058594  0.023471832
11 1.9990234 -0.0039052963 2.0007324  0.0029301643 2.0024414  0.009771347
12 1.9990234 -0.0039052963 1.9998779 -4.8828125E-4 2.0007324 0.0029301643
13 1.9998779 -4.8828125E-4 2.0003052  0.0012207031 2.0007324 0.0029301643
14 1.9998779 -4.8828125E-4 2.0000916  3.6621094E-4 2.0003052 0.0012207031
15 1.9998779 -4.8828125E-4 1.9999847 -6.1035156E-5 2.0000916 3.6621094E-4
16 1.9999847 -6.1035156E-5 2.0000381  1.5258789E-4 2.0000916 3.6621094E-4
17 1.9999847 -6.1035156E-5 2.0000114  4.5776367E-5 2.0000381 1.5258789E-4
18 1.9999847 -6.1035156E-5 1.9999981 -7.6293945E-6 2.0000114 4.5776367E-5
19 1.9999981 -7.6293945E-6 2.0000048  1.9073486E-5 2.0000114 4.5776367E-5
20 1.9999981 -7.6293945E-6 2.0000014   5.722046E-6 2.0000048 1.9073486E-5

Success! Root = 2.0000014
*/