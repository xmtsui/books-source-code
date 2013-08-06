package numbercruncher.program5_4;

import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.SecantRootFinder;
import numbercruncher.mathutils.AlignRight;
import numbercruncher.rootutils.RootFunctions;

/**
 * PROGRAM 5-4: Secant Algorithm
 *
 * Demonstrate the Secant Algorithm on a function.
 */
public class SecantAlgorithm
{
    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        try {
            SecantRootFinder finder =
                new SecantRootFinder(
                        RootFunctions.function("x^2 - 4"),
                                               0.3625f, 1.3625001f);

            AlignRight ar = new AlignRight();

            ar.print("n", 2);
            ar.print("x[n-1]", 10); ar.print("f(x[n-1])", 12);
            ar.print("x[n]", 10); ar.print("f(x[n])", 13);
            ar.print("x[n+1]", 10); ar.print("f(x[n+1])", 14);
            ar.underline();

            // Loop until convergence or failure.
            boolean converged;
            do {
                converged = finder.step();

                ar.print(finder.getIterationCount(), 2);
                ar.print(finder.getXnm1(), 10);
                ar.print(finder.getFnm1(), 12);
                ar.print(finder.getXn(), 10);
                ar.print(finder.getFn(), 13);
                ar.print(finder.getXnp1(), 10);
                ar.print(finder.getFnp1(), 14);
                ar.println();
            } while (!converged);

            System.out.println("\nSuccess! Root = " +
                               finder.getXnp1());
        }
        catch(Exception ex) {
            System.out.println("***** Error: " + ex);
        }
    }
}
/*
Output:
 n    x[n-1]   f(x[n-1])      x[n]      f(x[n])    x[n+1]     f(x[n+1])
-----------------------------------------------------------------------
 1    0.3625  -3.8685937 1.3625001   -2.1435935 2.6051629     2.7868733
 2 1.3625001  -2.1435935 2.6051629    2.7868733 1.9027661   -0.37948108
 3 2.6051629   2.7868733 1.9027661  -0.37948108  1.986947   -0.05204177
 4 1.9027661 -0.37948108  1.986947  -0.05204177 2.0003262  0.0013046265
 5  1.986947 -0.05204177 2.0003262 0.0013046265 1.9999989 -4.2915344E-6

Success! Root = 1.9999989
*/