package numbercruncher.program4_7;

import numbercruncher.mathutils.AlignRight;

/**
 * Program 4-7: Sum Numbers with Mixed Signs
 *
 * Sum a sequence numbers with alternating signs.  The sum should be 0.
 */
public class SumMixedSigns
{
    private static AlignRight ar = new AlignRight();

    public static void main(String args[])
    {
        int     k          = 0;
        int     odd        = 1;      // odd number
        float   kFactorial = 1;      // k!
        boolean subtract   = false;  // true if subtract, false if add
        float   sum        = 0;      // running sum
        float   prevSum    = -1;     // previous value of running sum

        ar.print("k", 2);
        ar.print("Numerator", 11);
        ar.print("Factorial", 16);
        ar.print("Fraction", 16);
        ar.print("Running sum", 16);
        ar.underline();

        // Loop until the running sum stops changing.
        do {
            float numerator = odd*odd;
            numerator += odd*numerator;

            float fraction = numerator/kFactorial;
            prevSum = sum;

            // Add or subtract the next fraction.
            if (subtract) fraction = -fraction;
            sum += fraction;

            ar.print(k, 2);
            ar.print(numerator, 11);
            ar.print(kFactorial, 16);
            ar.print(fraction, 16);
            ar.print(sum, 16);
            ar.println();

            ++k;
            kFactorial *= k;
            odd += 2;
            subtract = !subtract;
        } while (sum != prevSum);
    }
}
/*
Output:
 k  Numerator       Factorial        Fraction     Running sum
-------------------------------------------------------------
 0        2.0             1.0             2.0             2.0
 1       36.0             1.0           -36.0           -34.0
 2      150.0             2.0            75.0            41.0
 3      392.0             6.0      -65.333336      -24.333336
 4      810.0            24.0           33.75        9.416664
 5     1452.0           120.0           -12.1      -2.6833363
 6     2366.0           720.0        3.286111      0.60277486
 7     3600.0          5040.0     -0.71428573     -0.11151087
 8     5202.0         40320.0      0.12901786     0.017506987
 9     7220.0        362880.0    -0.019896384   -0.0023893975
10     9702.0       3628800.0     0.002673611    2.8421357E-4
11    12696.0       3.99168E7   -3.1806156E-4   -3.3847988E-5
12    16250.0      4.790016E8     3.392473E-5     7.674316E-8
13    20412.0     6.2270208E9    -3.277972E-6   -3.2012288E-6
14    25230.0    8.7178289E10    2.8940693E-7   -2.9118219E-6
15    30752.0   1.30767428E12    -2.351656E-8   -2.9353384E-6
16    37026.0   2.09227885E13    1.7696494E-9   -2.9335688E-6
17    44100.0   3.55687415E14  -1.2398527E-10   -2.9336927E-6
18    52022.0    6.4023735E15    8.125424E-12   -2.9336845E-6
19    60840.0   1.21645096E17   -5.001435E-13    -2.933685E-6
20    70602.0   2.43290202E18   2.9019664E-14    -2.933685E-6
*/