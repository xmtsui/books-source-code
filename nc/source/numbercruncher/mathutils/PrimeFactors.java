package numbercruncher.mathutils;

import java.util.Vector;
import java.util.Enumeration;

/**
 * Compute the Sieve of Eratosthenes and prime factors.
 */
public class PrimeFactors
{
    /**
     * Compute the Sieve of Eratosthenes.
     * @param n the size of the sieve
     * @return the sieve as a boolean array (each element is true
     *         if the corresponding number is prime, false if the
     *         number is composite)
     */
    public static boolean[] primeSieve(int n)
    {
        int     halfN   = (n+1) >> 1;
        boolean sieve[] = new boolean[n+1];

        // Initialize every integer from 2 onwards to prime.
        for (int i = 2; i <= n; ++i) sieve[i] = true;

        int prime = 2;  // first prime number

        // Loop to create the sieve.
        while (prime < halfN) {

            // Mark as composites multiples of the prime.
            for (int composite = prime << 1;
                 composite <= n;
                 composite += prime) sieve[composite] = false;

            // Skip over composites to the next prime.
            while ((++prime < halfN) && (!sieve[prime]));
        }

        return sieve;
    }

    /**
     * Compute the prime factors of an integer value.
     * @param n the value to factor
     * @return an array of distinct prime factors
     */
    public static int[] factorsOf(int n)
    {
        boolean isPrime[] = primeSieve(n);      // primes <= n
        Vector  v         = new Vector();

        // Loop to try prime divisors.
        for (int factor = 2; n > 1; ++factor) {
            if (isPrime[factor] && (n%factor == 0)) {

                // Prime divisor found.
                v.add(new Integer(factor));

                // Factor out multiples of the divisor.
                do {
                    n /= factor;
                } while (n%factor == 0);
            }
        }

        // Create an array of the distinct prime factors.
        int factors[] = new int[v.size()];
        Enumeration e = v.elements();
        for (int i = 0; e.hasMoreElements(); ++i) {
            factors[i] = ((Integer) e.nextElement()).intValue();
        }

        return factors;
    }

    /**
     * Main for testing.
     * @param args the commandline arguments (ignored)
     */
    public static void main(String args[])
    {
        AlignRight ar = new AlignRight();

        // Test Sieve of Eratosthenes.
        System.out.println("The Sieve of Eratosthenes:\n");
        boolean isPrime[] = primeSieve(100);
        for (int i = 1; i <= 100; ++i) {
            if (isPrime[i]) ar.print(i, 4);
            else            ar.print(".", 4);
            if (i%10 == 0)  ar.println();
        }

        System.out.println();

        // Test prime factors.
        int k[] = {84, 1409, 3141135, };
        for (int i = 0; i < k.length; ++i) {
            int factors[] = factorsOf(k[i]);
            System.out.print("The prime factors of " +
                             k[i] + " are");
            for (int j = 0; j < factors.length; ++j) {
                System.out.print(" " + factors[j]);
            }
            System.out.println();
        }
    }
}
/*
Output:
The Sieve of Eratosthenes:

   .   2   3   .   5   .   7   .   .   .
  11   .  13   .   .   .  17   .  19   .
   .   .  23   .   .   .   .   .  29   .
  31   .   .   .   .   .  37   .   .   .
  41   .  43   .   .   .  47   .   .   .
   .   .  53   .   .   .   .   .  59   .
  61   .   .   .   .   .  67   .   .   .
  71   .  73   .   .   .   .   .  79   .
   .   .  83   .   .   .   .   .  89   .
   .   .   .   .   .   .  97   .   .   .

The prime factors of 84 are 2 3 7
The prime factors of 1409 are 1409
The prime factors of 3141135 are 3 5 29 83
*/