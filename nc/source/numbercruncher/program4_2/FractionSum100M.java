package numbercruncher.program4_2;

import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-2: Fraction Sum 100M
 *
 * Compute the sum 1/d + 2/d + 3/d + ... + n/d = d/d
 * where:
 *     n = 100,000,000
 *     d = 1 + 2 + 3 + ... + n = (n/2)(n + 1)
 *
 * See why the sum ends up being 0.5
 */
public class FractionSum100M
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
        int gEnd    = gSize;              // index of group end
        int minDiff = Integer.MAX_VALUE;  // min exponent difference
        int exceeds = 0;                  // # of exponent diff > 24

        // Loop to sum the fractions.
        for (int i = 1; i <= MAX; ++i) {
            float fraction = i/denom;

            // Compute the exponent difference.
            int expSum      = Float.floatToIntBits(sum)      >> 23;
            int expFraction = Float.floatToIntBits(fraction) >> 23;
            int diff        = Math.abs(expSum - expFraction);

            if (i > 1) {
                minDiff = Math.min(minDiff, diff);
                if (diff > 24) ++exceeds;
            }

            sum += fraction;

            // Printout at the end of each group.
            if (i == gEnd) {
                ar.print(i, 9);
                ar.print(fraction, 15);
                ar.print(sum, 15);
                ar.print(minDiff, 15);
                ar.print((100*exceeds)/gSize, 15);
                ar.println();

                minDiff = Integer.MAX_VALUE;
                exceeds = 0;
                gEnd += gSize;
            }
        }
    }
}
/*
Output:
        i       Fraction    Running sum   Min exp diff   % ExpDiff>24
---------------------------------------------------------------------
  5000000         1.0E-9    0.002493547              0              0
 10000000         2.0E-9    0.009991142             21              0
 15000000         3.0E-9    0.022081949             22              0
 20000000         4.0E-9      0.0407084             23              0
 25000000         5.0E-9     0.05933485             23              0
 30000000         6.0E-9     0.09342261             23              0
 35000000   6.9999997E-9          0.125             24             15
 40000000         8.0E-9     0.16593489             24             45
 45000000         9.0E-9      0.2404407             24              0
 50000000         1.0E-8           0.25             24             87
 55000000         1.1E-8           0.25             25            100
 60000000         1.2E-8           0.25             25            100
 65000000   1.2999999E-8           0.25             25            100
 70000000   1.3999999E-8           0.25             25            100
 75000000         1.5E-8     0.26472795             24             90
 80000000         1.6E-8     0.41373956             24              0
 85000000         1.7E-8            0.5             24             42
 90000000         1.8E-8            0.5             25            100
 95000000         1.9E-8            0.5             25            100
100000000         2.0E-8            0.5             25            100
*/