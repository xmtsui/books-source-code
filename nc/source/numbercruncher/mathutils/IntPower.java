package numbercruncher.mathutils;

/**
 * Raise a double value to an integer power.
 */
public final class IntPower
{
    /**
     * Compute and return x^power.
     * @param x
     * @param power the integer power
     * @return x^power
     */
    public static double raise(double x, int exponent)
    {
        if (exponent < 0) return 1/raise(x, -exponent);

        double power = 1;

        // Loop to compute x^exponent.
        while (exponent > 0) {

            // Is the rightmost exponent bit a 1?
            if ((exponent & 1) == 1) power *= x;

            // Square x and shift the exponent 1 bit to the right.
            x *= x;
            exponent >>= 1;
        }

        return power;
    }
}