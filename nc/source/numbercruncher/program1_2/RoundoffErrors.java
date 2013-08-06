package numbercruncher.program1_2;

/**
 * PROGRAM 1-2: Roundoff Errors
 *
 * Demonstrate how a tiny roundoff error
 * can explode into a much larger one.
 */
public class RoundoffErrors
{
    public static void main(String args[])
    {
        float denominator = 20000000;
        float a           = 10000001/denominator;
        float b           = 1/2f;
        float diff1       = Math.abs(a - b);
        float pctError1   = 100*diff1/b;

        float inverse     = 1/diff1;
        float diff2       = Math.abs(inverse - denominator);
        float pctError2   = 100*diff2/denominator;

        System.out.println("        a = " + a);
        System.out.println("        b = " + b);
        System.out.println("    diff1 = " + diff1);
        System.out.println("pctError1 = " + pctError1 + "%");

        System.out.println();
        System.out.println("  inverse = " + inverse);
        System.out.println("    diff2 = " + diff2);
        System.out.println("pctError2 = " + pctError2 + "%");

        System.out.println();
        System.out.println("   factor = " + pctError2/pctError1);
    }
}
/*
Output:
        a = 0.50000006
        b = 0.5
    diff1 = 5.9604645E-8
pctError1 = 1.1920929E-5%

  inverse = 1.6777216E7
    diff2 = 3222784.0
pctError2 = 16.11392%

   factor = 1351733.6
*/