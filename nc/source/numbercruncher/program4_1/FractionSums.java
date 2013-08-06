package numbercruncher.program4_1;

import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-1: Fraction Sums
 *
 * For each n, compute the sum 1/d + 2/d + 3/d + ... + n/d = d/d
 * where:
 *     n = 10, 100, 1000, ..., 100,000,000
 *     d = 1 + 2 + 3 + ... + n = (n/2)(n + 1)
 *
 * See how close each sum is to 1.
 */
public class FractionSums
{
    public static void main(String args[])
    {
        AlignRight ar = new AlignRight();

        ar.print("n", 9); ar.print("Denom", 14);
        ar.print("1/Denom", 14); ar.print("n/Denom", 13);
        ar.print("Sum", 11); ar.print("% Error", 13);
        ar.underline();

        for (int n = 10; n <= 100000000; n *= 10) {
            float sum   = 0;
            float denom = (0.5f*n)*(n + 1);

            // Sum fractions.
            for (int i = 1; i <= n; ++i) sum += i/denom;

            ar.print(n, 9); ar.print(denom, 14); ar.print(1/denom, 14);
            ar.print(n/denom, 13); ar.print(sum, 11);
            ar.print(100*Math.abs(sum - 1), 13);
            ar.println();
        }
    }
}
/*
Output:
        n         Denom       1/Denom      n/Denom        Sum      % Error
--------------------------------------------------------------------------
       10          55.0   0.018181818   0.18181819 0.99999994 5.9604645E-6
      100        5050.0   1.980198E-4   0.01980198        1.0          0.0
     1000      500500.0   1.998002E-6  0.001998002 0.99999994 5.9604645E-6
    10000      5.0005E7     1.9998E-8    1.9998E-4  0.9999999 1.1920929E-5
   100000   5.0000502E9 1.9999799E-10   1.99998E-5 0.99999875 1.2516975E-4
  1000000 5.00000489E11  1.999998E-12  1.999998E-6  0.9998997  0.010031462
 10000000  5.0000004E13 1.9999998E-14 1.9999999E-7   1.002663   0.26630163
100000000  5.0000001E15 1.9999999E-16       2.0E-8        0.5         50.0
*/