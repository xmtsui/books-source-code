package numbercruncher.program13_3;

import java.math.BigDecimal;

import numbercruncher.mathutils.BigFunctions;
import numbercruncher.piutils.PiFormula;

/**
 * PROGRAM 13-3: The Borwein Pi Algorithm
 *
 * Compute digits of pi by the Borwein algorithm.
 */
public class PiBorwein extends PiFormula
                       implements PiBorweinParent, PiBorweinConstants
{
    /** number of digits to compute */  private int digits;

    /** the Borwein algorithm */
    private PiBorweinAlgorithm algorithm;

    /**
     * Compute the digits of pi using the Borwein algorithm.
     * @param digits the number of digits of pi to compute
     */
    private void compute(int digits)
    {
        this.digits = digits;

        startTime = System.currentTimeMillis();
        System.out.println(timestamp(startTime) + " START TIME\n");

        algorithm = new PiBorweinAlgorithm(digits, this);
        algorithm.compute();
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        PiBorwein pi = new PiBorwein();

        if (args.length < 1) {
            System.out.println("Specify the number of decimal digits " +
                               "(at least 1) on the command line.");
            return;
        }

        try {
            int digits = Integer.parseInt(args[0]);

            if (digits < 1) {
                throw new Exception("The number of decimal digits " +
                                    "must be at least 1.");
            }

            pi.compute(digits);
        }
        catch(Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    //-----------------------------------//
    // Implementation of PiBorweinParent //
    //-----------------------------------//

    /**
     * Scale notification.
     * @param scale the scale being used
     */
    public void notifyScale(int scale)
    {
        System.out.println("digits = " + digits);
        System.out.println("scale  = " + scale);
    }

    /**
     * Phase notification.
     * @param phase the current phase
     */
    public void notifyPhase(int phase)
    {
        switch (phase) {

            case INITIALIZING: {
                System.out.print("\n" + timestamp(startTime) +
                                 " Initialization:");
                break;
            }

            case INVERTING: {
                System.out.println("\n" + timestamp(markTime) +
                                   " Inverting");
                break;
            }

            case DONE: {
                System.out.println(timestamp(markTime) +
                                   " pi computed");

                String totalTime = timestamp(startTime);
                printPi(algorithm.getPi());

                System.out.println("\n" + algorithm.getIterations() +
                                   " iterations");
                System.out.println(totalTime + " TOTAL COMPUTE TIME");

                break;
            }

            default: {
                System.out.print("\n" + timestamp(markTime) +
                                 " Iteration " + phase + ":");
                break;
            }
        }

        markTime = System.currentTimeMillis();
    }

    /**
     * Task notification.
     * @param task the current computation task
     */
    public void notifyTask(String task)
    {
        System.out.print(" " + task);
    }
}
/*
Output:
01:29:30.720 (00:00:00) START TIME

digits = 700
scale  = 705

01:29:30.720 (00:00:00) Initialization: constants sqrt2 y a
01:29:31.320 (00:00:01) Iteration 1: y4 yRoot4 y aTerm power2 y2 a
01:29:32.370 (00:00:01) Iteration 2: y4 yRoot4 y aTerm power2 y2 a
01:29:33.580 (00:00:01) Iteration 3: y4 yRoot4 y aTerm power2 y2 a
01:29:35.170 (00:00:02) Iteration 4: y4 yRoot4 y aTerm power2 y2 a
01:29:36.870 (00:00:02) Iteration 5: y4 yRoot4 y aTerm power2 y2 a
01:29:38.520 (00:00:02) Iteration 6: y4 yRoot4 y
01:29:39.230 (00:00:01) Inverting
01:29:39.620 (00:00:00) pi computed

pi = 3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510
       58209 74944 59230 78164 06286 20899 86280 34825 34211 70679
       82148 08651 32823 06647 09384 46095 50582 23172 53594 08128
       48111 74502 84102 70193 85211 05559 64462 29489 54930 38196
       44288 10975 66593 34461 28475 64823 37867 83165 27120 19091
       45648 56692 34603 48610 45432 66482 13393 60726 02491 41273
       72458 70066 06315 58817 48815 20920 96282 92540 91715 36436
       78925 90360 01133 05305 48820 46652 13841 46951 94151 16094
       33057 27036 57595 91953 09218 61173 81932 61179 31051 18548
       07446 23799 62749 56735 18857 52724 89122 79381 83011 94912

       98336 73362 44065 66430 86021 39494 63952 24737 19070 21798
       60943 70277 05392 17176 29317 67523 84674 81846 76694 05132
       00056 81271 45263 56082 77857 71342 75778 96091 73637 17872
       14684 40901 22495 34301 46549 58537 10507 92279 68925 89235

6 iterations
01:29:39.620 (00:00:09) TOTAL COMPUTE TIME
*/