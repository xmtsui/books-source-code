package numbercruncher.program5_5;

import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.NewtonsRootFinder;
import numbercruncher.mathutils.AlignRight;
import numbercruncher.rootutils.RootFunctions;

/**
 * PROGRAM 5-5: Newton's Algorithm
 *
 * Demonstrate Newton's Algorithm on a function.
 */
public class NewtonsAlgorithm
{
    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        try {
            NewtonsRootFinder finder =
                new NewtonsRootFinder(
                        RootFunctions.function("x^2 - 4"));
            finder.reset(5.130833f);

            AlignRight ar = new AlignRight();

            ar.print("n", 2);
            ar.print("x[n]", 11); ar.print("f(x[n])", 14);
            ar.print("f'(x[n])", 11); ar.print("x[n+1]", 11);
            ar.print("f(x[n+1])", 14);
            ar.underline();

            // Loop until convergence or failure.
            boolean converged;
            do {
                converged = finder.step();

                ar.print(finder.getIterationCount(), 2);
                ar.print(finder.getXn(), 11);
                ar.print(finder.getFn(), 14);
                ar.print(finder.getFpn(), 11);
                ar.print(finder.getXnp1(), 11);
                ar.print(finder.getFnp1(), 14);
                ar.println();
            } while (!converged);

            System.out.println("\nSuccess! Root = " + finder.getXnp1());
        }
        catch(Exception ex) {
            System.out.println("***** Error: " + ex);
        }
    }
}
/*
Output:
 n       x[n]       f(x[n])   f'(x[n])     x[n+1]     f(x[n+1])
---------------------------------------------------------------
 1   5.130833     22.325449  10.261666   2.955217      4.733307
 2   2.955217      4.733307   5.910434  2.1543777     0.6413431
 3  2.1543777     0.6413431  4.3087554  2.0055313   0.022155762
 4  2.0055313   0.022155762  4.0110626  2.0000076  3.0517578E-5
 5  2.0000076  3.0517578E-5  4.0000153        2.0           0.0

Success! Root = 2.0
*/