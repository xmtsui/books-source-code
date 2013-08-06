package numbercruncher.program3_5;

import numbercruncher.mathutils.IEEE754;
import numbercruncher.mathutils.Epsilon;

/**
 * PROGRAM 3-5: Print Machine Epsilon
 *
 * Decompose and print the machine epsilon
 * for the float and double types.
 */
public class PrintEpsilon
{
    public static void main(String args[])
    {
        (new IEEE754(Epsilon.floatValue())).print();
        (new IEEE754(Epsilon.doubleValue())).print();
    }
}
/*
Output:
------------------------------
float value = 5.9604645E-8
sign=0, exponent=01100111 (biased=103, normalized, unbiased=-24)
significand=1.00000000000000000000000
------------------------------
double value = 1.1102230246251565E-16
sign=0, exponent=01111001010 (biased=970, normalized, unbiased=-53)
significand=1.0000000000000000000000000000000000000000000000000000
*/