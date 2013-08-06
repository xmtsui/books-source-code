package numbercruncher.program3_1;

import numbercruncher.mathutils.IEEE754;
import numbercruncher.mathutils.IEEE754Constants;

/**
 * PROGRAM 3-1: IEEE 754 Standard Floating-Point Formats
 *
 * Demonstrate the IEEE 754 standard floating-point formats
 * with various float and double values.
 */
public class FPFormats implements IEEE754Constants
{
    private void display() throws IEEE754.Exception
    {
        // Floats
        float floats[] = {
            -0.0f, 0.0f, -1.0f, 1.0f, 0.75f, -0.375f,
            Float.MIN_VALUE, Float.MAX_VALUE,
            Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
            Float.NaN,
        };
        for (int i = 0; i < floats.length; ++i) {
            new IEEE754(floats[i]).print();
        }

        // Doubles
        double doubles[] = {
            -0.375,
            Double.MIN_VALUE, Double.MAX_VALUE,
            Double.POSITIVE_INFINITY,
            Double.NaN,
        };
        for (int i = 0; i < doubles.length; ++i) {
            new IEEE754(doubles[i]).print();
        }

        System.out.println("------------------------------");

        IEEE754 numbers[] = {

            // Floats
            new IEEE754(
                0,
                IEEE754.toFloatBiasedExponent(-FLOAT_EXPONENT_BIAS),
                new IEEE754.FloatFraction("00101")),
            new IEEE754(
                0,
                IEEE754.toFloatBiasedExponent(-126),
                new IEEE754.FloatFraction("0")),
            new IEEE754(
                0,
                IEEE754.toFloatBiasedExponent(-FLOAT_EXPONENT_BIAS),
                new IEEE754.FloatFraction("11111111111111111111111")),

            // Doubles
            new IEEE754(
                0,
                IEEE754.toDoubleBiasedExponent(-DOUBLE_EXPONENT_BIAS),
                new IEEE754.DoubleFraction("00101")),
            new IEEE754(
                0,
                IEEE754.toDoubleBiasedExponent(-1022),
                new IEEE754.DoubleFraction("0")),
            new IEEE754(
                0,
                IEEE754.toDoubleBiasedExponent(-DOUBLE_EXPONENT_BIAS),
                new IEEE754.DoubleFraction("11111111111111111111111111" +
                                           "11111111111111111111111111")),
        };

        for (int i = 0; i < numbers.length; ++i) {
            numbers[i].print();
        }
    }

    /**
     * Main.
     * @param args the string array of program arguments
     */
    public static void main(String args[])
    {
        FPFormats formats = new FPFormats();

        try {
            formats.display();
        }
        catch(IEEE754.Exception ex) {
            System.out.println("***** Error: " + ex.getMessage());
        }
    }
}
/*
Output:
------------------------------
float value = -0.0
sign=1, exponent=00000000 (biased=0, zero)
significand=0.00000000000000000000000
------------------------------
float value = 0.0
sign=0, exponent=00000000 (biased=0, zero)
significand=0.00000000000000000000000
------------------------------
float value = -1.0
sign=1, exponent=01111111 (biased=127, normalized, unbiased=0)
significand=1.00000000000000000000000
------------------------------
float value = 1.0
sign=0, exponent=01111111 (biased=127, normalized, unbiased=0)
significand=1.00000000000000000000000
------------------------------
float value = 0.75
sign=0, exponent=01111110 (biased=126, normalized, unbiased=-1)
significand=1.10000000000000000000000
------------------------------
float value = -0.375
sign=1, exponent=01111101 (biased=125, normalized, unbiased=-2)
significand=1.10000000000000000000000
------------------------------
float value = 1.4E-45
sign=0, exponent=00000000 (biased=0, denormalized, use -126)
significand=0.00000000000000000000001
------------------------------
float value = 3.4028235E38
sign=0, exponent=11111110 (biased=254, normalized, unbiased=127)
significand=1.11111111111111111111111
------------------------------
float value = -Infinity
sign=1, exponent=11111111 (biased=255, reserved)
significand=0.00000000000000000000000
------------------------------
float value = Infinity
sign=0, exponent=11111111 (biased=255, reserved)
significand=0.00000000000000000000000
------------------------------
float value = NaN
sign=0, exponent=11111111 (biased=255, reserved)
significand=0.10000000000000000000000
------------------------------
double value = -0.375
sign=1, exponent=01111111101 (biased=1021, normalized, unbiased=-2)
significand=1.1000000000000000000000000000000000000000000000000000
------------------------------
double value = 4.9E-324
sign=0, exponent=00000000000 (biased=0, denormalized, use -1022)
significand=0.0000000000000000000000000000000000000000000000000001
------------------------------
double value = 1.7976931348623157E308
sign=0, exponent=11111111110 (biased=2046, normalized, unbiased=1023)
significand=1.1111111111111111111111111111111111111111111111111111
------------------------------
double value = Infinity
sign=0, exponent=11111111111 (biased=2047, reserved)
significand=0.0000000000000000000000000000000000000000000000000000
------------------------------
double value = NaN
sign=0, exponent=11111111111 (biased=2047, reserved)
significand=0.1000000000000000000000000000000000000000000000000000
------------------------------
------------------------------
float value = 1.83671E-39
sign=0, exponent=00000000 (biased=0, denormalized, use -126)
significand=0.00101000000000000000000
------------------------------
float value = 1.17549435E-38
sign=0, exponent=00000001 (biased=1, normalized, unbiased=-126)
significand=1.00000000000000000000000
------------------------------
float value = 1.1754942E-38
sign=0, exponent=00000000 (biased=0, denormalized, use -126)
significand=0.11111111111111111111111
------------------------------
double value = 3.4766779039175E-309
sign=0, exponent=00000000000 (biased=0, denormalized, use -1022)
significand=0.0010100000000000000000000000000000000000000000000000
------------------------------
double value = 2.2250738585072014E-308
sign=0, exponent=00000000001 (biased=1, normalized, unbiased=-1022)
significand=1.0000000000000000000000000000000000000000000000000000
------------------------------
double value = 2.225073858507201E-308
sign=0, exponent=00000000000 (biased=0, denormalized, use -1022)
significand=0.1111111111111111111111111111111111111111111111111111
*/