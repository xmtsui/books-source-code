package numbercruncher.program4_3;

import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-3: Fraction Sum 100M in the Reversed Order
 *
 * Compute the sum n/d + (n-1)/d + (n-2)/d + ... + 2/d + 1/d = d/d
 * where:
 *     n = 100,000,000
 *     d = 1 + 2 + 3 + ... + n = (n/2)(n + 1)
 *
 * See if the sum is closer to 1.
 */
public class FractionSum100MReversed
{
    private static final int GROUPS = 20;
    private static final int MAX    = 100000000;    // 100M

    public static void main(String args[])
    {
        AlignRight ar = new AlignRight();

        ar.print("i", 9);
        ar.print("Fraction", 15);
        ar.print("Running sum", 15);
        ar.print("Min exp diff", 15);
        ar.print("% ExpDiff>24", 15);
        ar.underline();

        float sum   = 0;
        float denom = (0.5f*MAX)*(MAX + 1);

        int gSize   = MAX/GROUPS;         // group size
        int gStart  = (MAX + 1) - gSize;  // index of group start
        int minDiff = Integer.MAX_VALUE;  // min exponent difference
        int exceeds = 0;                  // # of exponent diff > 24

        // Loop to sum the fractions.
        for (int i = MAX; i >= 1; --i) {
            float fraction = i/denom;

            int expSum      = Float.floatToIntBits(sum)      >> 23;
            int expFraction = Float.floatToIntBits(fraction) >> 23;
            int diff        = Math.abs(expSum - expFraction);

            if (i < MAX) {
                minDiff = Math.min(minDiff, diff);
                if (diff > 24) ++exceeds;
            }

            sum += fraction;

            // Printout at the start of each group.
            if (i == gStart) {
                ar.print(i, 9);
                ar.print(fraction, 15);
                ar.print(sum, 15);
                ar.print(minDiff, 15);
                ar.print((100*exceeds)/gSize, 15);
                ar.println();

                minDiff = Integer.MAX_VALUE;
                exceeds = 0;
                gStart -= gSize;
            }
        }
    }
}
/*
Output:
        i       Fraction    Running sum   Min exp diff   % ExpDiff>24
---------------------------------------------------------------------
 95000001         1.9E-8     0.10206167              0              0
 90000001         1.8E-8     0.18421358             22              0
 85000001         1.7E-8     0.26743877             23              0
 80000001         1.6E-8     0.41645038             24              0
 75000001         1.5E-8            0.5             24             43
 70000001   1.3999999E-8            0.5             25            100
 65000001   1.2999999E-8            0.5             26            100
 60000001         1.2E-8            0.5             26            100
 55000001         1.1E-8            0.5             26            100
 50000001         1.0E-8            0.5             26            100
 45000001         9.0E-9            0.5             26            100
 40000001         8.0E-9            0.5             26            100
 35000001   6.9999997E-9            0.5             26            100
 30000001         6.0E-9            0.5             27            100
 25000001         5.0E-9            0.5             27            100
 20000001         4.0E-9            0.5             27            100
 15000001         3.0E-9            0.5             27            100
 10000001   2.0000002E-9            0.5             28            100
  5000001   1.0000002E-9            0.5             28            100
        1  1.9999999E-16            0.5             29            100
*/