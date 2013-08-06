package numbercruncher.program5_6;

import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.FixedPointRootFinder;
import numbercruncher.mathutils.AlignRight;
import numbercruncher.rootutils.RootFunctions;

/**
 * PROGRAM 5-6: Fixed-Point Iteration
 *
 * Demonstrate Fixed-Point Iteration on a function.
 */
public class FixedPointIteration
{
    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        doFunction("(x + 4/x)/2", 10.325f);
        doFunction("exp(1/x)", -3f);
    }

    /**
     * Apply fixed-point iteration to a function.
     * @param key the function key
     * @param x0 the starting value
     */
    private static void doFunction(String key, float x0)
    {
        try {
            FixedPointRootFinder finder;
            AlignRight ar = new AlignRight();

            System.out.println("\ng(x) = " + key + "\n");
            finder = new FixedPointRootFinder(
                                RootFunctions.function(key));
            finder.reset(x0);

            ar.print("n", 2); ar.print("x[n]", 15);
            ar.print("g(x[n])", 15);
            ar.underline();

            // Loop until convergence or failure.
            boolean converged;
            do {
                converged = finder.step();

                ar.print(finder.getIterationCount(), 2);
                ar.print(finder.getXn(), 15);
                ar.print(finder.getGn(), 15);
                ar.println();
            } while (!converged);

            System.out.println("\nSuccess! Fixed point = " +
                               finder.getXn());
        }
        catch(Exception ex) {
            System.out.println("***** Error: " + ex);
        }
    }
}
/*
Output:
g(x) = (x + 4/x)/2

 n           x[n]        g(x[n])
--------------------------------
 1         10.325      5.3562045
 2      5.3562045       3.051501
 3       3.051501      2.1811657
 4      2.1811657      2.0075238
 5      2.0075238       2.000014
 6       2.000014            2.0
 7            2.0            2.0

Success! Fixed point = 2.0

g(x) = exp(1/x)

 n           x[n]        g(x[n])
--------------------------------
 1           -3.0      0.7165313
 2      0.7165313      4.0374465
 3      4.0374465      1.2810516
 4      1.2810516      2.1828005
 5      2.1828005      1.5811099
 6      1.5811099      1.8822485
 7      1.8822485      1.7011074
 8      1.7011074      1.8001182
 9      1.8001182      1.7428454
10      1.7428454      1.7749537
11      1.7749537      1.7566261
12      1.7566261      1.7669822
13      1.7669822      1.7610966
14      1.7610966      1.7644305
15      1.7644305      1.7625386
16      1.7625386      1.7636112
17      1.7636112      1.7630026
18      1.7630026      1.7633477
19      1.7633477       1.763152
20       1.763152       1.763263
21       1.763263         1.7632
22         1.7632      1.7632357
23      1.7632357      1.7632155
24      1.7632155       1.763227
25       1.763227      1.7632204

Success! Fixed point = 1.763227
*/