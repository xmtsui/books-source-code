package numbercruncher.program14_2;

import numbercruncher.mathutils.RandomExponential;
import numbercruncher.randomutils.Buckets;

/**
 * PROGRAM 14-2: Exponentially-Distributed Random Numbers
 *
 * Demonstrate algorithms for generating exponentially-distributed
 * random numbers.
 */
public class GenerateRandomExponential
{
    private static final int NUMBER_COUNT = 100000;    // 100K

    /** counters of random values that fall within each interval */
    private Buckets buckets;

    private RandomExponential exponential;

    private void run(float mean)
    {
        long startTime;     // starting time of each algorithm

        // Initialize the random number generator.
        exponential = new RandomExponential();
        exponential.setParameters(mean);

        // Logarithm algorithm.
        startTime = System.currentTimeMillis();
        buckets = new Buckets(32);
        buckets.setLimits(0, 2);
        log();
        print("Logarithm", startTime);

        // von Neumann algorithm.
        startTime = System.currentTimeMillis();
        buckets = new Buckets(13);
        buckets.setLimits(0, 12);
        vonNeumann();
        print("von Neumann", startTime);
    }

    /**
     * Print the results of an algorithm with its elapsed time.
     * @param label the algorithm label
     * @param startTime the starting time
     */
    private void print(String label, long startTime)
    {
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("\n" + label + " (" + elapsedTime +
                           " ms):\n");
        buckets.print();
    }

    /**
     * Invoke the logarithm algorithm.
     */
    private void log()
    {
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            buckets.put(exponential.nextLog());
        }
    }

    /**
     * Invoke the von Neumann algorithm.
     */
    private void vonNeumann()
    {
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            buckets.put(exponential.nextVonNeumann());
        }
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        float mean = 0.5f;

        GenerateRandomExponential test =
                                new GenerateRandomExponential();
        test.run(mean);
    }
}
/*
Output:
Logarithm (330 ms):

 0  11697: **************************************************
 1  10536: *********************************************
 2   9197: ***************************************
 3   8012: **********************************
 4   6986: ******************************
 5   6406: ***************************
 6   5447: ***********************
 7   4910: *********************
 8   4400: *******************
 9   3894: *****************
10   3332: **************
11   2943: *************
12   2591: ***********
13   2373: **********
14   1973: ********
15   1825: ********
16   1522: *******
17   1400: ******
18   1180: *****
19   1158: *****
20    968: ****
21    845: ****
22    689: ***
23    696: ***
24    595: ***
25    530: **
26    475: **
27    411: **
28    363: **
29    306: *
30    254: *
31    236: *

von Neumann (550 ms):

 0  31690: **************************************************
 1  22331: ***********************************
 2  15291: ************************
 3  10374: ****************
 4   6934: ***********
 5   4549: *******
 6   3087: *****
 7   2009: ***
 8   1320: **
 9    868: *
10    527: *
11    365: *
12    238:
*/