package numbercruncher.program14_1;

import numbercruncher.mathutils.RandomNormal;
import numbercruncher.randomutils.Buckets;

/**
 * PROGRAM 14-1: Normally-Distributed Random Numbers
 *
 * Demonstrate algorithms for generating normally-distributed
 * random numbers.
 */
public class GenerateRandomNormal
{
    private static final int BUCKET_COUNT = 32;
    private static final int NUMBER_COUNT = 100000;    // 100K

    /** counters of random values that fall within each interval */
    private Buckets buckets = new Buckets(BUCKET_COUNT);

    /** generator of normally-distributed random values */
    private RandomNormal normal;

    /**
     * Test the algorithms with a given mean and standard deviation.
     * @param mean the mean
     * @param stddev the standard deviation
     */
    private void run(float mean, float stddev)
    {
        long startTime;     // starting time of each algorithm

        // Initialize the random number generator
        // and the interval buckets.
        normal = new RandomNormal();
        normal.setParameters(mean, stddev);
        buckets.setLimits(0, BUCKET_COUNT-1);

        // Central limit theorem algorithm.
        startTime = System.currentTimeMillis();
        buckets.clear();
        central();
        print("Central", startTime);

        // Polar algorithm.
        startTime = System.currentTimeMillis();
        buckets.clear();
        polar();
        print("Polar", startTime);

        // Ratio algorithm.
        startTime = System.currentTimeMillis();
        buckets.clear();
        ratio();
        print("Ratio", startTime);
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
     * Invoke the Central Limit Theorem algorithm.
     */
    private void central()
    {
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            buckets.put(normal.nextCentral());
        }
    }

    /**
     * Invoke the polar algorithm.
     */
    private void polar()
    {
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            buckets.put(normal.nextPolar());
        }
    }

    /**
     * Invoke the ratio algorithm.
     */
    private void ratio()
    {
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            buckets.put(normal.nextRatio());
        }
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        float mean   = BUCKET_COUNT/2f;
        float stddev = BUCKET_COUNT/6f;

        GenerateRandomNormal test = new GenerateRandomNormal();
        test.run(mean, stddev);
    }
}
/*
Output:
Central (710 ms):

 0     87: *
 1    164: *
 2    313: **
 3    413: ***
 4    691: *****
 5    994: *******
 6   1430: **********
 7   2005: **************
 8   2572: ******************
 9   3158: **********************
10   3968: ****************************
11   4804: **********************************
12   5508: ***************************************
13   6241: ********************************************
14   6784: ************************************************
15   7014: **************************************************
16   7068: **************************************************
17   7028: **************************************************
18   6791: ************************************************
19   6246: ********************************************
20   5535: ***************************************
21   4840: **********************************
22   3968: ****************************
23   3349: ************************
24   2656: *******************
25   1966: **************
26   1472: **********
27   1042: *******
28    702: *****
29    419: ***
30    283: **
31    172: *

Polar (490 ms):

 0    117: *
 1    190: *
 2    271: **
 3    431: ***
 4    665: *****
 5    929: ******
 6   1432: **********
 7   1989: **************
 8   2478: *****************
 9   3269: ***********************
10   3925: ***************************
11   4886: **********************************
12   5509: **************************************
13   6212: *******************************************
14   6628: **********************************************
15   7106: *************************************************
16   7261: **************************************************
17   7101: *************************************************
18   6743: **********************************************
19   6339: ********************************************
20   5573: **************************************
21   4816: *********************************
22   3994: ****************************
23   3301: ***********************
24   2514: *****************
25   1932: *************
26   1391: **********
27    960: *******
28    690: *****
29    443: ***
30    301: **
31    192: *

Ratio (550 ms):

 0     98: *
 1    183: *
 2    283: **
 3    454: ***
 4    685: *****
 5    995: *******
 6   1428: **********
 7   1933: *************
 8   2497: *****************
 9   3209: **********************
10   4053: ****************************
11   4762: *********************************
12   5502: **************************************
13   6204: *******************************************
14   6758: **********************************************
15   7013: ************************************************
16   7293: **************************************************
17   7121: *************************************************
18   6838: ***********************************************
19   6237: *******************************************
20   5453: *************************************
21   4802: *********************************
22   3950: ***************************
23   3389: ***********************
24   2533: *****************
25   2009: **************
26   1342: *********
27   1016: *******
28    673: *****
29    440: ***
30    277: **
31    216: *
*/