package numbercruncher.program4_4;

import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-4: Fraction Sum 100M Using Group Subtotals
 *
 * Compute the sum 1/d + 2/d + 3/d + ... + n/d = d/d
 * where:
 *     n = 100,000,000
 *     d = 1 + 2 + 3 + ... + n = (n/2)(n + 1)
 *
 * Compute the sum by adding the group subtotals.
 * See if the final sum is closer to 1.
 */
public class FractionSum100MGrouped
{
    private static final int GROUPS = 20;
    private static final int MAX    = 100000000;    // 100M

    public static void main(String args[])
    {
        AlignRight ar = new AlignRight();

        ar.print("i", 9);
        ar.print("Group sum", 15);
        ar.print("Running sum", 15);
        ar.print("% ExpDiff>24", 15);
        ar.underline();

        float sum      = 0;           // running sum
        float subtotal = 0;           // group subtotal
        int   gSize    = MAX/GROUPS;  // group size
        int   gEnd     = gSize;       // index of group end
        int   exceeds  = 0;           // # of exponent diff > 24

        float denom = (0.5f*MAX)*(MAX + 1);

        // Sum the fractions by groups.
        for (int i = 1; i <= MAX; ++i) {
            float fraction = i/denom;

            int expSubtotal = Float.floatToIntBits(subtotal) >> 23;
            int expFraction = Float.floatToIntBits(fraction) >> 23;
            int diff        = Math.abs(expSubtotal - expFraction);

            if ((subtotal > 0) && (diff > 24)) ++exceeds;
            subtotal += fraction;

            // Subtotal and printout at the end of each group.
            if (i == gEnd) {
                sum += subtotal;

                ar.print(i, 9);
                ar.print(subtotal, 15);
                ar.print(sum, 15);
                ar.print((100*exceeds)/gSize, 15);
                ar.println();

                subtotal = 0;
                exceeds  = 0;
                gEnd += gSize;
            }
        }

        System.out.println("\n% error = " + 100*Math.abs(sum - 1));
    }
}
/*
Output:
        i      Group sum    Running sum   % ExpDiff>24
------------------------------------------------------
  5000000    0.002493547    0.002493547              0
 10000000    0.007556428   0.0100499755              0
 15000000    0.012413543     0.02246352              0
 20000000    0.017349107    0.039812624              0
 25000000    0.023679595     0.06349222              0
 30000000     0.02718998      0.0906822              0
 35000000    0.034128636     0.12481084              0
 40000000    0.036770158       0.161581              0
 45000000     0.04185812     0.20343912              0
 50000000    0.049500026     0.25293913              0
 55000000     0.05435951     0.30729866              0
 60000000    0.055825584     0.36312425              0
 65000000     0.05929653     0.42242077              0
 70000000     0.07003953      0.4924603              0
 75000000     0.07346739      0.5659277              0
 80000000     0.07455075     0.64047843              0
 85000000     0.07784402     0.71832246              0
 90000000     0.08436947     0.80269194              0
 95000000    0.098553196      0.9012451              0
100000000     0.09986824      1.0011134              0

% error = 0.11134148
*/