package numbercruncher.program1_4;

import java.util.Random;

/**
 * PROGRAM 1-4: Not Associative Percentage
 *
 * Figure out what percentage of floating-point additions
 * and multiplications fail their associative laws.
 */
public class NotAssocPercentage
{
    private static final int TRIALS = 1000000;  // one million

    public static void main(String args[])
    {
        Random random = new Random();

        int addCount  = 0;
        int multCount = 0;

        // Loop to perform trials.
        for (int i = 0; i < TRIALS; ++i) {

            // Generate three random floating-point values.
            float a = random.nextFloat();
            float b = random.nextFloat();
            float c = random.nextFloat();

            // Add both ways.
            float s1 = (a+b)+c;
            float s2 = a+(b+c);

            // Multiply both ways.
            float p1 = (a*b)*c;
            float p2 = a*(b*c);

            // Compare sums and products and count the failures.
            if (s1 != s2) ++addCount;
            if (p1 != p2) ++multCount;
        }

        System.out.println((100*addCount)/TRIALS + "% failures of the " +
                           "associative law of addition.");
        System.out.println((100*multCount)/TRIALS + "% failures of the " +
                           "associative law of multiplication.");
    }
}
/*
Output:
17% failures of the associative law of addition.
34% failures of the associative law of multiplication.
*/