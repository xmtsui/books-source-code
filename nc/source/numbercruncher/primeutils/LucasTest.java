package numbercruncher.primeutils;

import numbercruncher.mathutils.ModuloArithmetic;
import numbercruncher.mathutils.PrimeFactors;

/**
 * An implemention of the the Lucas test for primality.
 */
public class LucasTest
{
    private static LucasStatus status = new LucasStatus();

    /** number to test for primality */     int p;
    /** prime factors of p-1 */             int q[];
    /** caller of the test */               LucasCaller caller;

    /**
     * Constructor.
     * @param p the number to test for primality
     * @param caller the caller of the test
     */
    public LucasTest(int p, LucasCaller caller)
    {
        this.p      = p;
        this.caller = caller;

        q = PrimeFactors.factorsOf(p-1);
    }

    /**
     * Perform the Lucas test.
     * @return true if p is prime, false if p is composite
     */
    public boolean test()
    {
        // Try integers a from 2 through p.
        for (int a = 2; a <= p; ++a) {
            if (passPart1(a) && passPart2(a)) return true;  // prime
        }

        return false;   // composite
    }

    /**
     * Test if integer a passes the first part of the test.
     * @param a the value of a
     * @return true if [a^(p-1)]%p == 1, else false
     */
    private boolean passPart1(int a)
    {
        int exponent = p-1;
        int value    = ModuloArithmetic.raise(a, exponent, p);

        // Report status back to the caller.
        if (caller != null) {
            status.a        = a;
            status.q        = 1;
            status.exponent = exponent;
            status.value    = value;
            status.pass     = (value == 1);

            caller.reportStatus(status);
        }

        return (value == 1);    // pass if it's 1
    }

    /**
     * Test if integer a passes the second part of the test.
     * @param a the value of a
     * @return true if [a^(p-1)/q]%p != 1 for all prime factors q,
     *              else false
     */
    private boolean passPart2(int a)
    {
        int pm1 = p-1;

        // Loop to try each prime factor.
        for (int i = 0; i < q.length; ++i) {
            int exponent = pm1/q[i];
            int value    = ModuloArithmetic.raise(a, exponent, p);

            // Report status back to the caller.
            if (caller != null) {
                status.a        = a;
                status.q        = q[i];
                status.exponent = exponent;
                status.value    = value;
                status.pass     = (value != 1);

                caller.reportStatus(status);
            }

            if (value == 1) return false;   // fail
        }

        return true;    // pass
    }
}