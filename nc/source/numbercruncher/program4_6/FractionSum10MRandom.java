package numbercruncher.program4_6;

import java.util.Random;

import numbercruncher.mathutils.KahanSummation;
import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-6: Fraction Sum 10M with Randomized Order
 *
 * Compute the sum 1/d + 2/d + 3/d + ... + n/d = d/d
 * where:
 *     n = 10,000,000
 *     d = 1 + 2 + 3 + ... + n = (n/2)(n + 1)
 *
 * Randomize the order of the fractions, and then compute their
 * sum by simple straight summation and by Kahan's Summation Algorithm.
 */
public class FractionSum10MRandom
{
    private static final int GROUPS = 20;
    private static final int MAX    = 10000000;     // 10M

    public static void main(String args[])
    {
        int    counting[] = new int[MAX + 1];  // array of counting #s
        Random random     = new Random(0);     // random # generator

        AlignRight ar = new AlignRight();

        // Initialize the array of counting numbers.
        for (int i = 1; i <= MAX; ++i) counting[i] = i;

        // Randomize the array.
        for (int i = 0; i < (MAX/2); ++i) {
            int j = random.nextInt(MAX) + 1;
            int k = random.nextInt(MAX) + 1;

            // Exchange random elements.
            int temp    = counting[j];
            counting[j] = counting[k];
            counting[k] = temp;
        }

        System.out.println("STRAIGHT SUMMATION:\n");
        ar.print("i", 9); ar.print("Running sum", 16);
        ar.print("% ExpDiff>24", 16);
        ar.underline();

        float sum   = 0;
        float denom = (0.5f*MAX)*(MAX + 1);

        int gSize   = MAX/GROUPS;   // group size
        int gEnd    = gSize;        // index of group end
        int exceeds = 0;            // # of exponent diff > 24

        // Sum the fractions using simple straight summation.
        for (int i = 1; i <= MAX; ++i) {
            float fraction = counting[i]/denom;
            sum += fraction;

            int expSum      = Float.floatToIntBits(sum)      >> 23;
            int expFraction = Float.floatToIntBits(fraction) >> 23;
            int diff        = Math.abs(expSum - expFraction);

            if ((sum > 0) && (diff > 24)) ++exceeds;

            // Subtotal and printout at the end of each group.
            if (i == gEnd) {
                ar.print(i, 9); ar.print(sum, 16);
                ar.print((100*exceeds)/gSize, 16);
                ar.println();

                exceeds = 0;
                gEnd += gSize;
            }
        }

        System.out.println("\nStraight summation % error = " +
                           100*Math.abs(sum - 1));

        System.out.println("\nKAHAN SUMMATION ALGORITHM:\n");
        ar.print("i", 9); ar.print("Running sum", 16);
        ar.print("% ExpDiff>24", 16);
        ar.underline();

        KahanSummation kSum = new KahanSummation();

        exceeds = 0;
        gEnd    = gSize;

        // Sum the corrected fractions using
        // the Kahan Summation Algorithm.
        for (int i = 1; i <= MAX; ++i) {
            float fraction = counting[i]/denom;

            int expSum = Float.floatToIntBits(kSum.value()) >> 23;
            kSum.add(fraction);

            int expFraction = Float.floatToIntBits(
                                    kSum.correctedAddend()) >> 23;
            int diff        = Math.abs(expSum - expFraction);

            if ((i > 1) && (diff > 24))
                ++exceeds;

            // Printout at the start of each group.
            if (i == gEnd) {
                ar.print(i, 9); ar.print(kSum.value(), 16);
                ar.print((100*exceeds)/gSize, 16);
                ar.println();

                exceeds = 0;
                gEnd += gSize;
            }
        }

        System.out.println("\nKahan summation % error = " +
                           100*Math.abs(kSum.value() - 1));
    }
}
/*
Output:
STRAIGHT SUMMATION:

        i     Running sum    % ExpDiff>24
-----------------------------------------
   500000      0.03253527               0
  1000000      0.06698631               0
  1500000      0.10321888               1
  2000000      0.14138556               1
  2500000      0.18116862               2
  3000000      0.22312914               2
  3500000      0.26563966               3
  4000000      0.31117648               4
  4500000      0.35919634               4
  5000000      0.40717205               4
  5500000      0.45824587               4
  6000000       0.5116293               5
  6500000       0.5648118               9
  7000000       0.6180561               9
  7500000      0.67235166               9
  8000000      0.73646766               9
  8500000       0.8006135               9
  9000000       0.8647307               9
  9500000       0.9288819               9
 10000000       0.9929972               9

Straight summation % error = 0.7002771

KAHAN SUMMATION ALGORITHM:

        i     Running sum    % ExpDiff>24
-----------------------------------------
   500000     0.032521274               0
  1000000      0.06689266               0
  1500000      0.10307682               1
  2000000      0.14115047               1
  2500000      0.18101728               2
  3000000      0.22275041               2
  3500000      0.26629776               3
  4000000      0.31170923               4
  4500000      0.35897893               4
  5000000      0.40804166               4
  5500000      0.45892397               4
  6000000      0.51166385               5
  6500000        0.566273               9
  7000000       0.6227702               9
  7500000      0.68106335               9
  8000000      0.74115884               9
  8500000       0.8031232               9
  9000000      0.86690027               9
  9500000       0.9325508               9
 10000000             1.0               9

Kahan summation % error = 0.0
*/