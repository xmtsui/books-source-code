package numbercruncher.program4_11;

import numbercruncher.mathutils.IntPower;
import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 4-11: e to x with Split Exponent
 *
 * Compute e^x by splitting the exponent x into its whole and
 * fraction components before using the Taylor series.
 */
public class EtoXSplit
{
    private static final double x = -19.5;

    private static AlignRight ar = new AlignRight();

    public static void main(String args[])
    {
        double result;

        // Split off the whole part of |x|.
        double xAbs   = Math.abs(x);
        int    xWhole = (int) xAbs;

        // x is only a fraction.
        if (xWhole == 0) {
            result = taylor(xAbs);
        }

        // x has a whole part.
        else {
            // Split off the fraction part of x,
            // compute e^(1 + fraction/whole) ...
            double xFraction = xAbs - xWhole;
            double temp = taylor(1 + xFraction/xWhole);

            // ... and raise it to the whole power.
            result = IntPower.raise(temp, xWhole);
        }

        // Invert the result if x < 0.
        if (x < 0) result = 1/result;

        double correct = Math.exp(x);
        System.out.println("\ne^" + x + " = " + result);
        System.out.println("% error = " +
                           100*Math.abs(result - correct)/correct);
    }

    /**
     * Compute e^x, x > 0 using the Taylor series.
     * @param x the exponent
     * @return the value to which the series converged
     */
    private static double taylor(double x)
    {
        ar.print("k", 2);
        ar.print("Numerator", 20);
        ar.println();
        ar.print("Denominator", 22);
        ar.print("Fraction", 24);
        ar.print("Running sum", 20);
        ar.underline();

        int    k           = 0;
        double numerator   = 1;
        double denominator = 1;
        double sum         = 1;
        double prevSum     = 0;

        // Loop until the terms have no effect on the sum.
        do {
            numerator   *= x;    // x^k
            denominator *= ++k;  // k!

            double fraction = numerator/denominator;
            prevSum = sum;
            sum += fraction;

            ar.print(k, 2);
            ar.print(numerator, 20);
            ar.println();
            ar.print(denominator, 22);
            ar.print(fraction, 24);
            ar.print(sum, 20);
            ar.println();
        } while (prevSum != sum);

        return sum;
    }
}
/*
Output:
 k           Numerator
           Denominator                Fraction         Running sum
------------------------------------------------------------------
 1  1.0263157894736843
                   1.0      1.0263157894736843   2.026315789473684
 2  1.0533240997229918
                   2.0      0.5266620498614959    2.55297783933518
 3  1.0810431549788602
                   6.0     0.18017385916314335   2.733151698498323
 4  1.1094916590572512
                  24.0     0.04622881912738547  2.7793805176257087
 5  1.1386888079798105
                 120.0    0.009489073399831755  2.7888695910255406
 6  1.1686543029266478
                 720.0   0.0016231309762870108  2.7904927220018276
 7  1.1994083635299808
                5040.0   2.3797784990674224E-4  2.7907306998517343
 8   1.230971741517612
               40320.0    3.053005311303601E-5  2.7907612299048474
 9   1.263365734715444
              362880.0   3.4814972848198965E-6   2.790764711402132
10  1.2966122014184822
             3628800.0   3.5731156344204206E-7  2.7907650687136956
11  1.3307335751400213
             3.99168E7    3.333768175655416E-8  2.7907651020513775
12  1.3657528797489693
            4.790016E8   2.8512490976000275E-9  2.7907651049026265
13  1.4016937450055214
           6.2270208E9  2.2509861296842326E-10   2.790765105127725
14  1.4385804225056669
         8.71782912E10  1.6501590048437045E-11  2.7907651051442266
15  1.4764378020452897
        1.307674368E12  1.1290561612088505E-12  2.7907651051453555
16  1.5152914284149028
       2.0922789888E13    7.24230103406993E-14   2.790765105145428
17  1.5551675186363478
      3.55687428096E14   4.372287001992683E-15  2.7907651051454323
18   1.596092979653094
     6.402373705728E15  2.4929706590309163E-16  2.7907651051454327
19  1.6380954264860703
   1.21645100408832E17  1.3466185000305503E-17  2.7907651051454327

e^-19.5 = 3.398267819495072E-9
% error = 2.434124255327038E-14
*/