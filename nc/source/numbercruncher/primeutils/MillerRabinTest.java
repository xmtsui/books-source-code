package numbercruncher.primeutils;

import java.util.Random;
import numbercruncher.mathutils.ModuloArithmetic;

/**
 * An implemention of the the Miller-Rabin test for primality.
 */
public class MillerRabinTest
{
    private static MillerRabinStatus status = new MillerRabinStatus();

    /** number to test for primality */     private int p;
    /** number of times to run the test */  private int iterations;

    /** caller of the test */   private MillerRabinCaller caller;

    /**
     * Constructor.
     * @param p the number to test for primality
     * @param iterations the number of times to run the test
     * @param caller the caller of the test
     */
    public MillerRabinTest(int p, int iterations,
                           MillerRabinCaller caller)
    {
        this.p          = p;
        this.iterations = iterations;
        this.caller     = caller;
    }

    /**
     * Perform the Miller-Rabin test.
     * @return true if p is probably prime, false if p is composite
     */
    public boolean test()
    {
        Random random = new Random(0);
        int k = p - 1;
        int s = 0;

        // Shift k to the right s bits to make it odd.
        while ((k & 1) == 0) {
            k >>= 1;
            ++s;
        }

        status.k = k;
        status.s = s;

        // Run the test with different random base values.
        for (int i = 0; i < iterations; ++i) {

            // Generate a random integer base b.
            int    b;
            while ((b = random.nextInt(p)) <= 1);  // want 1 < b < p

            status.b = b;

            // Composite?
            if (!test(k, s, b)) return false;  // definitely composite
        }

        return true;    // most likely prime
    }

    /**
     * Perform the Miller-Rabin test.
     * @param k the value of p-1 shifted right until it is odd
     * @param s the number of right shifts
     * @return true if p is probably prime, false if p is composite
     */
    private boolean test(int k, int s, int b)
    {
        int pm1 = p-1;
        int sm1 = s-1;

        status.i    = 0;
        status.code = MillerRabinStatus.DONT_KNOW_YET;

        int r = ModuloArithmetic.raise(b, k, p);    // b^k (mod p)
        status.r = r;

        if (r == 1) {
            status.code = MillerRabinStatus.PROBABLY_PRIME;
            reportStatus();

            return true;        // probably prime
        }

        // Loop at most s-1 times.
        int i = 0;
        while (r != pm1) {
            reportStatus();
            status.i = ++i;

            if (i > sm1) {
                status.code = MillerRabinStatus.DEFINITELY_COMPOSITE;
                return false;   // definitely composite
            }

            r = ModuloArithmetic.raise(r, 2, p);    // r^2 (mod p)
            status.r = r;

            if (r == 1) {
                status.code = MillerRabinStatus.DEFINITELY_COMPOSITE;
                reportStatus();

                return false;   // definitely composite
            }
        }

        status.code = MillerRabinStatus.PROBABLY_PRIME;
        reportStatus();

        return true;            // probably prime
    }

    /**
     * Report the test status back to the caller.
     * @param status the test status
     */
    private void reportStatus()
    {
        if (caller != null) caller.reportStatus(status);
    }
}
