package numbercruncher.program13_1;

import java.math.BigDecimal;
import numbercruncher.mathutils.BigFunctions;

/**
 * PROGRAM 13-3: Ramanujan's Formulas for pi
 *
 * Compute estimates of pi using Ramanujan's formulas.
 */
public class PiRamanujan
{
    private void compute()
    {
        int digits;     // number of digits
        int scale;

        BigDecimal term, a, b, c, d, e, lnArg, pi;
        BigDecimal sqrt2, sqrt5, sqrt6, sqrt10, sqrt13, sqrt29;
        BigDecimal sqrt130, sqrt142, sqrt190, sqrt310, sqrt522;

        // --- 15 digits ---

        digits = 15;
        scale  = digits + 2;

        sqrt2   = BigFunctions.sqrt(BigDecimal.valueOf(  2), scale);
        sqrt5   = BigFunctions.sqrt(BigDecimal.valueOf(  5), scale);
        sqrt13  = BigFunctions.sqrt(BigDecimal.valueOf( 13), scale);
        sqrt130 = BigFunctions.sqrt(BigDecimal.valueOf(130), scale);

        term = BigDecimal.valueOf(12)
                    .divide(sqrt130, scale,
                            BigDecimal.ROUND_HALF_EVEN);
        a = BigDecimal.valueOf(2).add(sqrt5);
        b = BigDecimal.valueOf(3).add(sqrt13);
        lnArg = a.multiply(b)
                    .divide(sqrt2, BigDecimal.ROUND_HALF_EVEN)
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        pi = term.multiply(BigFunctions.ln(lnArg, scale))
                    .setScale(digits, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(digits + " digits: " + pi);

        // --- 16 digits ---

        digits = 16;
        scale  = digits + 2;

        sqrt2   = BigFunctions.sqrt(BigDecimal.valueOf(  2), scale);
        sqrt142 = BigFunctions.sqrt(BigDecimal.valueOf(142), scale);

        term = BigDecimal.valueOf(24)
                    .divide(sqrt142, scale,
                            BigDecimal.ROUND_HALF_EVEN);
        a = BigDecimal.valueOf(10)
                    .add(BigDecimal.valueOf(11).multiply(sqrt2))
                    .divide(BigDecimal.valueOf(4), scale,
                            BigDecimal.ROUND_HALF_EVEN);
        b = BigDecimal.valueOf(10)
                    .add(BigDecimal.valueOf(7).multiply(sqrt2))
                    .divide(BigDecimal.valueOf(4), scale,
                            BigDecimal.ROUND_HALF_EVEN);
        lnArg = BigFunctions.sqrt(a, scale)
                    .add(BigFunctions.sqrt(b, scale));
        pi = term.multiply(BigFunctions.ln(lnArg, scale))
                    .setScale(digits, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(digits + " digits: " + pi);

        // --- 18 digits ---

        digits = 18;
        scale  = digits + 2;

        sqrt2   = BigFunctions.sqrt(BigDecimal.valueOf(  2), scale);
        sqrt10  = BigFunctions.sqrt(BigDecimal.valueOf( 10), scale);
        sqrt190 = BigFunctions.sqrt(BigDecimal.valueOf(190), scale);

        term = BigDecimal.valueOf(12)
                    .divide(sqrt190, scale,
                            BigDecimal.ROUND_HALF_EVEN);
        a = BigDecimal.valueOf(2).multiply(sqrt2).add(sqrt10);
        b = BigDecimal.valueOf(3).add(sqrt10);
        lnArg = a.multiply(b)
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        pi = term.multiply(BigFunctions.ln(lnArg, scale))
                    .setScale(digits, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(digits + " digits: " + pi);

        // --- 22 digits ---

        digits = 22;
        scale  = digits + 2;

        sqrt2 =   BigFunctions.sqrt(BigDecimal.valueOf(  2), scale);
        sqrt5   = BigFunctions.sqrt(BigDecimal.valueOf(  5), scale);
        sqrt10  = BigFunctions.sqrt(BigDecimal.valueOf( 10), scale);
        sqrt310 = BigFunctions.sqrt(BigDecimal.valueOf(310), scale);

        term = BigDecimal.valueOf(12)
                    .divide(sqrt310, scale,
                            BigDecimal.ROUND_HALF_EVEN);
        a = BigDecimal.valueOf(3).add(sqrt5);
        b = BigDecimal.valueOf(2).add(sqrt2);
        c = BigDecimal.valueOf(5)
                    .add(BigDecimal.valueOf(2).multiply(sqrt10));
        d = BigFunctions.sqrt(BigDecimal.valueOf(61)
                                    .add(BigDecimal.valueOf(20)
                                            .multiply(sqrt10)),
                              scale);
        e = c.add(d).multiply(a).multiply(b)
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        lnArg = e.divide(BigDecimal.valueOf(4), scale,
                         BigDecimal.ROUND_HALF_EVEN);
        pi = term.multiply(BigFunctions.ln(lnArg, scale))
                    .setScale(digits, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(digits + " digits: " + pi);

        // --- 31 digits ---

        digits = 31;
        scale  = digits + 2;

        sqrt2   = BigFunctions.sqrt(BigDecimal.valueOf(  2), scale);
        sqrt6   = BigFunctions.sqrt(BigDecimal.valueOf(  6), scale);
        sqrt29  = BigFunctions.sqrt(BigDecimal.valueOf( 29), scale);
        sqrt522 = BigFunctions.sqrt(BigDecimal.valueOf(522), scale);

        term = BigDecimal.valueOf(4)
                    .divide(sqrt522, scale,
                            BigDecimal.ROUND_HALF_EVEN);
        a = BigDecimal.valueOf(5).add(sqrt29)
                    .divide(sqrt2, BigDecimal.ROUND_HALF_EVEN);
        b = BigDecimal.valueOf(5).multiply(sqrt29)
                    .add(BigDecimal.valueOf(11).multiply(sqrt6))
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        c = BigDecimal.valueOf(9)
                    .add(BigDecimal.valueOf(3).multiply(sqrt6))
                    .divide(BigDecimal.valueOf(4), scale,
                            BigDecimal.ROUND_HALF_EVEN);
        d = BigDecimal.valueOf(5)
                    .add(BigDecimal.valueOf(3).multiply(sqrt6))
                    .divide(BigDecimal.valueOf(4), scale,
                            BigDecimal.ROUND_HALF_EVEN);
        e = BigFunctions.sqrt(c, scale)
                    .add(BigFunctions.sqrt(d, scale));
        lnArg = BigFunctions.intPower(a, 3, scale)
                    .multiply(b)
                    .multiply(BigFunctions.intPower(e, 6, scale))
                    .setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        pi = term.multiply(BigFunctions.ln(lnArg, scale))
                    .setScale(digits, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(digits + " digits: " + pi);
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        PiRamanujan formulas = new PiRamanujan();

        try {
            formulas.compute();
         }
        catch(Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
/*
Output:
15 digits: 3.141592653589793
16 digits: 3.1415926535897931
18 digits: 3.141592653589793238
22 digits: 3.1415926535897932384626
31 digits: 3.1415926535897932384626433832794
*/