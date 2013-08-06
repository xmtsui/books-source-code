package numbercruncher.program13_3;

import java.math.BigDecimal;
import numbercruncher.mathutils.BigFunctions;

/**
 * Implement the Borwein algorithm for pi.
 */
class PiBorweinAlgorithm implements PiBorweinConstants
{
    /** number of pi digits */  private int digits;
    /** computation scale */    private int scale;
    /** iteration counter */    private int iterations;

    /** value of pi */          private BigDecimal      pi;
    /** parent object */        private PiBorweinParent parent;

    /**
     * Constructor.
     * @param digits the number of digits to compute
     * @param parent the parent object
     */
    PiBorweinAlgorithm(int digits, PiBorweinParent parent)
    {
        this.digits = digits;
        this.parent = parent;
        this.scale  = Math.max(((int) (1.005f*digits)), (digits + 5));
    }

    /**
     * Get the number of iterations.
     * @return the number of iterations
     */
    int getIterations() { return iterations; }

    /**
     * Get the value of pi as a string.
     * @return the string
     */
    String getPi() { return pi.toString(); }

    /**
     * Compute the digits of pi.
     * Notify the parent of each phase and task.
     */
    void compute()
    {
        parent.notifyScale(scale);

        // Initialization phase.
        parent.notifyPhase(INITIALIZING);
        parent.notifyTask("constants");

        BigDecimal big1   = BigDecimal.valueOf(1);
        BigDecimal big2   = BigDecimal.valueOf(2);
        BigDecimal big4   = BigDecimal.valueOf(4);
        BigDecimal big6   = BigDecimal.valueOf(6);
        BigDecimal power2 = big2;

        parent.notifyTask("sqrt2");
        BigDecimal sqrt2 = BigFunctions.sqrt(big2, scale);

        parent.notifyTask("y");
        BigDecimal y = sqrt2.subtract(BigDecimal.valueOf(1));

        parent.notifyTask("a");
        BigDecimal a =
            big6.subtract(
                big4.multiply(sqrt2)
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN));

        BigDecimal y2, y4, yRoot4, yNumerator, yDenominator;
        BigDecimal aTerm4, aTerm;

        // Loop once per iteration.
        for (;;) {
            parent.notifyPhase(++iterations);

            parent.notifyTask("y4");
            y4 = y.multiply(y)
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
            y4 = y4.multiply(y4)
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);

            parent.notifyTask("yRoot4");
            yRoot4 = BigFunctions.sqrt(big1.subtract(y4), scale);
            yRoot4 = BigFunctions.sqrt(yRoot4, scale);

            parent.notifyTask("y");
            yNumerator   = big1.subtract(yRoot4);
            yDenominator = big1.add(yRoot4);
            y = yNumerator.divide(yDenominator, scale,
                                  BigDecimal.ROUND_HALF_EVEN);

            if (y.signum() == 0) break;

            parent.notifyTask("aTerm");
            aTerm4 = big1.add(y);
            aTerm4 = aTerm4.multiply(aTerm4)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
            aTerm4 = aTerm4.multiply(aTerm4)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
            a = a.multiply(aTerm4)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);

            parent.notifyTask("power2");
            power2 = power2.multiply(big4)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);

            parent.notifyTask("y2");
            y2 = y.multiply(y)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);

            parent.notifyTask("a");
            aTerm = big1.add(y).add(y2);
            aTerm = power2.multiply(y).multiply(aTerm)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
            a = a.subtract(aTerm)
                        .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        }

        // Inversion phase.
        parent.notifyPhase(INVERTING);
        pi = big1.divide(a, digits, BigDecimal.ROUND_DOWN);

        parent.notifyPhase(DONE);
    }
}