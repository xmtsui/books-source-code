package numbercruncher.primeutils;

import numbercruncher.primeutils.MillerRabinTest;
import numbercruncher.primeutils.LucasTest;

/**
 * Primality test that combines the Miller-Rabin and Lucas tests.
 */
public class PrimalityTest
{
    /** number to test for primality */   private int p;
    /** number of times to run the
        Miller-Rabin test */              private int iterations;

    /**
     * Constructor.
     * @param p the number to test for primality
     * @param iterations the number of times to run
     *                   the Miller-Rabin test
     */
    public PrimalityTest(int p, int iterations)
    {
        this.p          = p;
        this.iterations = iterations;
     }

    /**
     * Perform the primality test.
     * @return true if p is prime, false if p is composite
     */
    public boolean test()
    {
        return (new MillerRabinTest(p, iterations, null)).test()
                    && (new LucasTest(p, null)).test();
    }
}