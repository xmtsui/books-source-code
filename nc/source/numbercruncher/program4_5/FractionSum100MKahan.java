package numbercruncher.program4_5;

import numbercruncher.mathutils.KahanSummation;
import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-5: Fraction Sum 100M by the Kahan Summation Algorithm
 *
 * Use the Kahan Summation Algorithm to compute
 * the sum 1/d + 2/d + 3/d + ... + n/d = d/d
 * where:
 *     n = 100,000,000
 *     d = 1 + 2 + 3 + ... + n = (n/2)(n + 1)
 *
 * See if the sum is closer to 1.
 */
public class FractionSum100MKahan
{
    private static final int GROUPS = 20;
    private static final int MAX    = 100000000;    // 100M

    public static void main(String args[])
    {
        AlignRight ar = new AlignRight();

        ar.print("i", 9); ar.print("Running sum", 16);
        ar.print("% ExpDiff>24", 16);
        ar.underline();

        float          denom = (0.5f*MAX)*(MAX + 1);
        KahanSummation kSum  = new KahanSummation();

        int gSize    = MAX/GROUPS;  // group size
        int gEnd     = gSize;       // index of group end
        int exceeds  = 0;           // # of exponent diff > 24

        // Sum the corrected fractions.
        for (int i = 1; i <= MAX; ++i) {
            float fraction = i/denom;

            int expSum = Float.floatToIntBits(kSum.value()) >> 23;
            kSum.add(fraction);

            int expFraction = Float.floatToIntBits(
                                    kSum.correctedAddend()) >> 23;
            int diff        = Math.abs(expSum - expFraction);

            if ((i > 1) && (diff > 24)) ++exceeds;

            // Printout at the start of each group.
            if (i == gEnd) {
                ar.print(i, 9); ar.print(kSum.value(), 16);
                ar.print((100*exceeds)/gSize, 16);
                ar.println();

                exceeds = 0;
                gEnd += gSize;
            }
        }

        System.out.println("\n% error = " +
                           100*Math.abs(kSum.value() - 1));
    }
}
/*
Output:
        i     Running sum    % ExpDiff>24
-----------------------------------------
  5000000    0.0025000004               0
 10000000     0.010000001               0
 15000000          0.0225               0
 20000000            0.04               0
 25000000          0.0625               0
 30000000            0.09              26
 35000000          0.1225              12
 40000000            0.16              46
 45000000          0.2025              42
 50000000            0.25              36
 55000000          0.3025              64
 60000000      0.35999998              61
 65000000      0.42249998              58
 70000000      0.48999998              54
 75000000          0.5625              72
 80000000            0.64              73
 85000000      0.72249997              72
 90000000            0.81              70
 95000000          0.9025              68
100000000             1.0              67

% error = 0.0
*/