package numbercruncher.program15_1;

import numbercruncher.mathutils.AlignRight;
import numbercruncher.primeutils.LucasTest;
import numbercruncher.primeutils.LucasCaller;
import numbercruncher.primeutils.LucasStatus;

/**
 * PROGRAM 15-1: Lucas Test for Primality
 *
 * Demonstrate the Lucas test for primality.
 */
public class TestLucas implements LucasCaller
{
    private int        prevA = 0;
    private AlignRight ar    = new AlignRight();

    /**
     * Test an integer p for primality.
     * @param p the value of p
     */
    void test(int p)
    {
        System.out.println("\nTESTING " + p + "\n");
        ar.print("a", 5);
        ar.print("q", 10);
        ar.print("exponent", 12);
        ar.print("mod value", 12);
        ar.print("status", 10);
        ar.underline();

        prevA = 0;

        boolean result = (new LucasTest(p, this)).test();

        System.out.println();
        System.out.println(p + " is " +
                           (result ? "prime." : "composite."));
    }

    /**
     * Report on the test status.
     * @param status the test status
     */
    public void reportStatus(LucasStatus status)
    {
        // Skip a line for a new value of a.
        if ((prevA != 0) && (status.getA() != prevA)) {
            System.out.println();
        }
        prevA = status.getA();

        ar.print(status.getA(), 5);
        ar.print(status.getQ(), 10);
        ar.print(status.getExponent(), 12);
        ar.print(status.getValue(), 12);
        ar.print((status.didPass() ? "pass" : "fail"), 10);
        ar.println();
    }

    /**
     * Main.
     * @param args the commandline arguments (ignored)
     */
    public static void main(String args[])
    {
        TestLucas lucas = new TestLucas();

        // Test various integers.  All but 21 are prime.
        lucas.test(7);
        lucas.test(15787);
        lucas.test(149287);
        lucas.test(21);
    }
}
/*
Output:
TESTING 7

    a         q    exponent   mod value    status
-------------------------------------------------
    2         1           6           1      pass
    2         2           3           1      fail

    3         1           6           1      pass
    3         2           3           6      pass
    3         3           2           2      pass

7 is prime.

TESTING 15787

    a         q    exponent   mod value    status
-------------------------------------------------
    2         1       15786           1      pass
    2         2        7893       15786      pass
    2         3        5262       11258      pass
    2       877          18        9552      pass

15787 is prime.

TESTING 149287

    a         q    exponent   mod value    status
-------------------------------------------------
    2         1      149286           1      pass
    2         2       74643           1      fail

    3         1      149286           1      pass
    3         2       74643      149286      pass
    3         3       49762       22426      pass
    3       139        1074      131616      pass
    3       179         834      123639      pass

149287 is prime.

TESTING 21

    a         q    exponent   mod value    status
-------------------------------------------------
    2         1          20           4      fail

    3         1          20           9      fail

    4         1          20          16      fail

    5         1          20           4      fail

    6         1          20          15      fail

    7         1          20           7      fail

    8         1          20           1      pass
    8         2          10           1      fail

    9         1          20          18      fail

   10         1          20          16      fail

   11         1          20          16      fail

   12         1          20          18      fail

   13         1          20           1      pass
   13         2          10           1      fail

   14         1          20           7      fail

   15         1          20          15      fail

   16         1          20           4      fail

   17         1          20          16      fail

   18         1          20           9      fail

   19         1          20           4      fail

   20         1          20           1      pass
   20         2          10           1      fail

   21         1          20           0      fail

21 is composite.
*/