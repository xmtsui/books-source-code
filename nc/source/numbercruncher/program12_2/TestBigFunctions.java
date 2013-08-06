package numbercruncher.program12_2;

import java.math.BigDecimal;
import numbercruncher.mathutils.BigFunctions;

/**
 * PROGRAM 12-2: Test BigFunctions
 *
 * Test the BigFunctions by comparing results with
 * class java.lang.Math.
 */
public class TestBigFunctions
{
    private static final int SCALE = 40;

    /**
     * Run the test.
     */
    private void run()
    {
        System.out.println("2^(-25) = " + Math.pow(2, -25));
        System.out.println("        = " +
            BigFunctions.intPower(BigDecimal.valueOf(2), -25, SCALE));

        System.out.println();
        System.out.println("sqrt 2 = " + Math.sqrt(2));
        System.out.println("       = " +
            BigFunctions.sqrt(BigDecimal.valueOf(2), SCALE));

        System.out.println();
        System.out.println("2^(1/3) = " + Math.exp(Math.log(2)/3));
        System.out.println("        = " +
            BigFunctions.intRoot(BigDecimal.valueOf(2), 3, SCALE));

        System.out.println();
        System.out.println("e^(-19.5) = " + Math.exp(-19.5));
        System.out.println("          = " +
            BigFunctions.exp(new BigDecimal("-19.5"), SCALE));

        System.out.println();
        System.out.println("ln 2 = " + Math.log(2));
        System.out.println("     = " +
            BigFunctions.ln(BigDecimal.valueOf(2), SCALE));

        System.out.println();
        System.out.println("arctan sqrt(3)/3 = " + Math.PI/6);
        System.out.println("                 = " +
            BigFunctions.arctan(
                    BigFunctions.sqrt(BigDecimal.valueOf(3), SCALE)
                        .divide(BigDecimal.valueOf(3), SCALE,
                                BigDecimal.ROUND_HALF_EVEN),
                    SCALE));
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        TestBigFunctions test = new TestBigFunctions();

        try {
            test.run();
        }
        catch(Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
/*
Output:
2^(-25) = 2.9802322387695312E-8
        = 0.0000000298023223876953125000000000000000

sqrt 2 = 1.4142135623730951
       = 1.4142135623730950488016887242096980785696

2^(1/3) = 1.2599210498948732
        = 1.25992104989487316476721060727822835057024

e^(-19.5) = 3.398267819495071E-9
          = 0.0000000033982678194950712251407378768109

ln 2 = 0.6931471805599453
     = 0.6931471805599453094172321214581765680755

arctan sqrt(3)/3 = 0.5235987755982988
                 = 0.52359877559829887307710723054658381403285
*/