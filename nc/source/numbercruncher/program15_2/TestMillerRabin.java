package numbercruncher.program15_2;

import numbercruncher.mathutils.AlignRight;
import numbercruncher.primeutils.MillerRabinTest;
import numbercruncher.primeutils.MillerRabinCaller;
import numbercruncher.primeutils.MillerRabinStatus;

/**
 * PROGRAM 15-2: Miller-Rabin Test for Primality
 *
 * Demonstrate the Miller-Rabin test for primality.
 */
public class TestMillerRabin implements MillerRabinCaller
{
    private static final int ITERATIONS = 5;

    private static final String CODE_LABELS[] = {
        "???", "composite", "prime?"
    };

    private AlignRight ar = new AlignRight();

    /**
     * Test an integer p for primality.
     * @param p the value of p
     */
    public void test(int p)
    {
        System.out.println("\nTESTING " + p + "\n");
        ar.print("b", 10);
        ar.print("k", 10);
        ar.print("s", 5);
        ar.print("i", 5);
        ar.print("r", 10);
        ar.print("status", 12);
        ar.underline();

        MillerRabinTest mrt = new MillerRabinTest(p, ITERATIONS,
                                                  this);
        boolean result = mrt.test();

        System.out.println();
        System.out.print(p + " is ");
        System.out.println(result ? "probably prime."
                                  : "composite.");
    }

    /**
     * Report on the test status.
     * @param status the test status
     */
    public void reportStatus(MillerRabinStatus status)
    {
        ar.print(status.getB(), 10);
        ar.print(status.getK(), 10);
        ar.print(status.getS(), 5);
        ar.print(status.getIndex(), 5);
        ar.print(status.getValue(), 10);

        ar.print(CODE_LABELS[status.getCode()], 12);
        ar.println();
    }

    /**
     * Main.
     * @param args the commandline arguments (ignored)
     */
    public static void main(String args[])
    {
        TestMillerRabin millerRabin = new TestMillerRabin();

        millerRabin.test(21);
        millerRabin.test(8191);
        millerRabin.test(524287);
        millerRabin.test(1604401);
    }
}
/*
Output:
TESTING 21

         b         k    s    i         r      status
----------------------------------------------------
        12         5    2    0         3         ???
        12         5    2    1         9         ???

21 is composite.

TESTING 8191

         b         k    s    i         r      status
----------------------------------------------------
      1738      4095    1    0         1      prime?
      7195      4095    1    0         1      prime?
      7187      4095    1    0      8190      prime?
      1368      4095    1    0         1      prime?
      4550      4095    1    0      8190      prime?

8191 is probably prime.

TESTING 524287

         b         k    s    i         r      status
----------------------------------------------------
     26082    262143    1    0         1      prime?
    308713    262143    1    0         1      prime?
    125334    262143    1    0    524286      prime?
    311826    262143    1    0    524286      prime?
    454445    262143    1    0         1      prime?

524287 is probably prime.

TESTING 1604401

         b         k    s    i         r      status
----------------------------------------------------
    637182    100275    4    0    419491         ???
    637182    100275    4    1    393000         ???
    637182    100275    4    2   1337735         ???
    637182    100275    4    3    494434         ???

1604401 is composite.
*/