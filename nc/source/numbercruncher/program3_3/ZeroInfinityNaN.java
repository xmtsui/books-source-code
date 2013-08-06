package numbercruncher.program3_3;

import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 3-3: Zero, Infinity, and Not-a-Number
 *
 * Investigate the results of floating-point arithmetic
 * involving zero, infinity, and NaN.
 */
public class ZeroInfinityNaN
{
    public static void main(String args[])
    {
        AlignRight ar = new AlignRight();

        float operands[] = {
            -0f, +0f, -1f, 1f,
            Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NaN,
        };

        ar.print("x", 10); ar.print("y", 10); ar.print("|", 2);
        ar.print("x+y", 10); ar.print("x-y", 10);
        ar.print("x*y", 10); ar.print("x/y", 10); ar.print("x%y", 10);
        ar.underline();

        for (int i = 0; i < operands.length; ++i) {
            for (int j = 0; j < operands.length; ++j) {
                float x = operands[i];
                float y = operands[j];

                ar.print(x, 10); ar.print(y, 10); ar.print("|", 2);
                ar.print(x+y, 10); ar.print(x-y, 10);
                ar.print(x*y, 10); ar.print(x/y, 10); ar.print(x%y, 11);
                ar.println();
            }
        }
    }
}
/*
Output:
         x         y |       x+y       x-y       x*y       x/y       x%y
------------------------------------------------------------------------
      -0.0      -0.0 |      -0.0       0.0       0.0       NaN        NaN
      -0.0       0.0 |       0.0      -0.0      -0.0       NaN        NaN
      -0.0      -1.0 |      -1.0       1.0       0.0       0.0       -0.0
      -0.0       1.0 |       1.0      -1.0      -0.0      -0.0       -0.0
      -0.0 -Infinity | -Infinity  Infinity       NaN       0.0       -0.0
      -0.0  Infinity |  Infinity -Infinity       NaN      -0.0       -0.0
      -0.0       NaN |       NaN       NaN       NaN       NaN        NaN
       0.0      -0.0 |       0.0       0.0      -0.0       NaN        NaN
       0.0       0.0 |       0.0       0.0       0.0       NaN        NaN
       0.0      -1.0 |      -1.0       1.0      -0.0      -0.0        0.0
       0.0       1.0 |       1.0      -1.0       0.0       0.0        0.0
       0.0 -Infinity | -Infinity  Infinity       NaN      -0.0        0.0
       0.0  Infinity |  Infinity -Infinity       NaN       0.0        0.0
       0.0       NaN |       NaN       NaN       NaN       NaN        NaN
      -1.0      -0.0 |      -1.0      -1.0       0.0  Infinity        NaN
      -1.0       0.0 |      -1.0      -1.0      -0.0 -Infinity        NaN
      -1.0      -1.0 |      -2.0       0.0       1.0       1.0       -0.0
      -1.0       1.0 |       0.0      -2.0      -1.0      -1.0       -0.0
      -1.0 -Infinity | -Infinity  Infinity  Infinity       0.0       -1.0
      -1.0  Infinity |  Infinity -Infinity -Infinity      -0.0       -1.0
      -1.0       NaN |       NaN       NaN       NaN       NaN        NaN
       1.0      -0.0 |       1.0       1.0      -0.0 -Infinity        NaN
       1.0       0.0 |       1.0       1.0       0.0  Infinity        NaN
       1.0      -1.0 |       0.0       2.0      -1.0      -1.0        0.0
       1.0       1.0 |       2.0       0.0       1.0       1.0        0.0
       1.0 -Infinity | -Infinity  Infinity -Infinity      -0.0        1.0
       1.0  Infinity |  Infinity -Infinity  Infinity       0.0        1.0
       1.0       NaN |       NaN       NaN       NaN       NaN        NaN
 -Infinity      -0.0 | -Infinity -Infinity       NaN  Infinity        NaN
 -Infinity       0.0 | -Infinity -Infinity       NaN -Infinity        NaN
 -Infinity      -1.0 | -Infinity -Infinity  Infinity  Infinity        NaN
 -Infinity       1.0 | -Infinity -Infinity -Infinity -Infinity        NaN
 -Infinity -Infinity | -Infinity       NaN  Infinity       NaN        NaN
 -Infinity  Infinity |       NaN -Infinity -Infinity       NaN        NaN
 -Infinity       NaN |       NaN       NaN       NaN       NaN        NaN
  Infinity      -0.0 |  Infinity  Infinity       NaN -Infinity        NaN
  Infinity       0.0 |  Infinity  Infinity       NaN  Infinity        NaN
  Infinity      -1.0 |  Infinity  Infinity -Infinity -Infinity        NaN
  Infinity       1.0 |  Infinity  Infinity  Infinity  Infinity        NaN
  Infinity -Infinity |       NaN  Infinity -Infinity       NaN        NaN
  Infinity  Infinity |  Infinity       NaN  Infinity       NaN        NaN
  Infinity       NaN |       NaN       NaN       NaN       NaN        NaN
       NaN      -0.0 |       NaN       NaN       NaN       NaN        NaN
       NaN       0.0 |       NaN       NaN       NaN       NaN        NaN
       NaN      -1.0 |       NaN       NaN       NaN       NaN        NaN
       NaN       1.0 |       NaN       NaN       NaN       NaN        NaN
       NaN -Infinity |       NaN       NaN       NaN       NaN        NaN
       NaN  Infinity |       NaN       NaN       NaN       NaN        NaN
       NaN       NaN |       NaN       NaN       NaN       NaN        NaN
*/