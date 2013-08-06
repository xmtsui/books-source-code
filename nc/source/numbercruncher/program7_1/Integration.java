package numbercruncher.program7_1;

import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.Integrator;
import numbercruncher.mathutils.TrapezoidalIntegrator;
import numbercruncher.mathutils.SimpsonsIntegrator;
import numbercruncher.mathutils.Epsilon;
import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 7-1: Integration
 *
 * Demonstrate numerical integration algorithms.
 */
public class Integration
{
    private static final int   MAX_INTERVALS = Integer.MAX_VALUE/2;
    private static final float TOLERANCE     = 100*Epsilon.floatValue();

    private static final float FROM_LIMIT    = 0;
    private static final float TO_LIMIT      = 1;

    private static final int   TRAPEZOIDAL   = 0;
    private static final int   SIMPSONS      = 1;

    private static final String ALGORITHMS[] = {"Trapezoidal",
                                                "Simpson's"};

    /**
     * Main program.
     * @param args the array of runtime arguments
     */
    public static void main(String args[])
    {
        if (args.length < 1) {
            System.out.println("Specify either Trapezoidal or " +
                               "Simpson's on the command line.");
            return;
        }

        String arg       = args[0].toLowerCase();
        int    algorithm = arg.startsWith("tra") ? TRAPEZOIDAL
                                                 : SIMPSONS;

        System.out.println("ALGORITHM: " + ALGORITHMS[algorithm]);
        System.out.println();

        // The function to integrate.
        Function integrand = new Function() {
            public float at(float x)
            {
                return (float) Math.sqrt(1 - x*x);
            }
        };

        integrate(algorithm, integrand);
    }

    /**
     * Do the integration with either the trapezoidal algorithm
     * or Simpson's algorithm.
     * @param algorithm 0 for trapezoidal, 1 for Simpson's
     * @param integrand the function to integrate
     * @return an approximation to the area
     */
    private static void integrate(int algorithm, Function integrand)
    {
        int   intervals = 1;                // number of intervals
        float area      = 0;                // total area
        float errorPct  = Float.MAX_VALUE;  // % error

        float prevArea;
        float prevErrorPct;

        Integrator integrator =
            (algorithm == TRAPEZOIDAL)
                ? (Integrator) new TrapezoidalIntegrator(integrand)
                : (Integrator) new SimpsonsIntegrator(integrand);

        AlignRight ar = new AlignRight();

        ar.print("n", 5); ar.print("pi", 15); ar.print("% Error", 15);
        ar.underline();

        do {
            prevArea = area;
            area     = integrator.integrate(FROM_LIMIT, TO_LIMIT,
                                            intervals);

            float pi = 4*area;

            prevErrorPct = errorPct;
            errorPct = (float) Math.abs(100*(pi - Math.PI)/Math.PI);

            ar.print(intervals, 5);
            ar.print(pi, 15);
            ar.print(errorPct, 15);
            ar.println();

            intervals *= 2;
        } while ((errorPct > TOLERANCE)
                    && (errorPct  < prevErrorPct)
                    && (intervals < MAX_INTERVALS));
    }
}
/*
Output:
ALGORITHM: Trapezoidal

    n             pi        % Error
-----------------------------------
    1            2.0      36.338024
    2       2.732051      13.036119
    4       2.995709       4.643623
    8       3.089819       1.648008
   16      3.1232529      0.5837735
   32      3.1351023     0.20659526
   64      3.1392965    0.073087834
  128      3.1407807    0.025845688
  256      3.1413052    0.009149671
  512      3.1414926    0.003184639
 1024      3.1415575   0.0011204039
 2048      3.1415784    4.525632E-4
 4096      3.1415865   1.9453382E-4
 8192      3.1415906    6.551914E-5
16384      3.1415942   4.8317346E-5
32768      3.1415963   1.1661924E-4
*/
/*
ALGORITHM: Simpson's

    n             pi        % Error
-----------------------------------
    1      2.9760678      5.2688203
    2      3.0835953      1.8461139
    4       3.121189      0.6494647
    8      3.1343977     0.22902104
   16      3.1390526     0.08085148
   32       3.140695    0.028570175
   64      3.1412754    0.010098308
  128      3.1414802    0.003579272
  256      3.1415539   0.0012342404
  512      3.1415799    4.070286E-4
 1024      3.1415892  1.11053734E-4
 2048      3.1415887   1.2623193E-4
*/