package numbercruncher.mathutils;

/**
 * Perform multiplication and exponentiation modulo arithmetic.
 */
public class ModuloArithmetic
{
    /**
     * Multiply two integer values a and b modulo m.
     * @param a the value of a
     * @param b the value of b
     * @param m the modulus m
     * @return the value of ab (mod m)
     */
    public static int multiply(int a, int b, int m)
    {
        int product = 0;

        // Loop to compute product = (a*b)%m.
        while (a > 0) {

            // Does the rightmost bit of a == 1?
            if ((a & 1) == 1) {
                product += b;
                product %= m;
            }

            // Double b modulo m, and
            // shift a 1 bit to the right.
            b <<= 1;  b %= m;
            a >>= 1;
        }

        return product;
    }

    /**
     * Raise a to the b power modulo m.
     * @param a the value of a
     * @param b the value of b
     * @param m the modulus m
     * @return the value of a^b (mod m)
     */
    public static int raise(int base, int exponent, int m)
    {
        int power = 1;

        // Loop to compute power = (base^exponent)%m.
        while (exponent > 0) {

            // Does the rightmost bit of the exponent == 1?
            if ((exponent & 1) == 1) {
                power = multiply(power, base, m);
            }

            // Square the base modulo m and
            // shift the exponent 1 bit to the right.
            base = multiply(base, base, m);
            exponent >>= 1;
        }

        return power;
    }

    /**
     * Main for testing.
     * @param args the commandline arguments (ignored)
     */
    public static void main(String args[])
    {
        int a = 3;
        int b = 13;
        int m = 5;

        // Test modulo multiplication.
        int modProduct = multiply(a, b, m);
        System.out.println(a + "*" + b + " = " + a*b);
        System.out.println(a + "*" + b + " = " + modProduct +
                           " (mod " + m + ")");

        System.out.println();

        // Test modulo exponentiation.
        int modPower = raise(a, b, m);
        System.out.println(a + "^" + b + " = " +
                           IntPower.raise(a, b));
        System.out.println(a + "^" + b + " = " + modPower +
                           " (mod " + m + ")");
    }
}
/*
Output:
3*13 = 39
3*13 = 4 (mod 5)

3^13 = 1594323.0
3^13 = 3 (mod 5)
*/