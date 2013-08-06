package numbercruncher.program15_3;

import numbercruncher.primeutils.PrimalityTest;

/**
 * PROGRAM 15-3: Primality Testing
 *
 * Demonstrate the primality test.
 */
public class TestPrimality
{
    public static void main(String args[])
    {
        // Numbers to test.
        int ps[] = {7, 21, 8191, 15787, 149287, 524287, 1604401};

        // Loop to test each number.
        for (int i = 0; i < ps.length; ++i) {
            int p = ps[i];
            System.out.print(p + " is ");

            System.out.println((new PrimalityTest(p, 5)).test()
                                    ? "prime." : "composite.");
        }
    }
}
/*
Output:
7 is prime.
21 is composite.
8191 is prime.
15787 is prime.
149287 is prime.
524287 is prime.
1604401 is composite.
*/