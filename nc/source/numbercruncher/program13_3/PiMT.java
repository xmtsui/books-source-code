package numbercruncher.program13_3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;

import numbercruncher.mathutils.BigFunctions;
import numbercruncher.piutils.PiFormula;

/**
 * PROGRAM 13-3: The Borwein Pi Algorithm
 *
 * Compute digits of pi by the Borwein algorithm.
 */
public class PiMT extends PiFormula
                  implements PiBorweinParent, PiBorweinConstants
{
    private static final SimpleDateFormat TIME_FORMAT =
                                new SimpleDateFormat("HH:mm:ss.SSS");

    /** number of digits to compute */  private int digits;
    /** the Borwein algorithm */        private PiMTAlgorithm algorithm;

    /**
     * Compute the digits of pi using the Borwein algorithm.
     * @param digits the number of digits of pi to compute
     */
    private void compute(int digits)
    {
        this.digits = digits;

        startTime = System.currentTimeMillis();
        System.out.println(timestamp(startTime) + " START TIME\n");

        algorithm = new PiMTAlgorithm(digits, this);
        algorithm.compute();
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        PiMT pi = new PiMT();

        try {
            int digits = Integer.parseInt(args[0]);
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
        System.out.println();
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
        String tString = TIME_FORMAT.format(new Date());
        System.out.println(tString + " " + task);
    }
}