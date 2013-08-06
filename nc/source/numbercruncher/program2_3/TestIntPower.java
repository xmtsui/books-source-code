package numbercruncher.program2_3;

import numbercruncher.mathutils.IntPower;

/**
 * PROGRAM 2-3: Test Class IntPower
 *
 * Test the IntPower class.
 */
public class TestIntPower
{
    public static void main(String args[])
    {
        System.out.println(IntPower.raise(2, 5)   + " " +
                           Math.pow(2, 5));
        System.out.println(IntPower.raise(2, -5)  + " " +
                           Math.pow(2, -5));
        System.out.println(IntPower.raise(2, 0)   + " " +
                           Math.pow(2, 0));
        System.out.println(IntPower.raise(2.5, 5) + " " +
                           Math.pow(2.5, 5));
        System.out.println();
        System.out.println(IntPower.raise(-2, 5)   + " " +
                           Math.pow(-2, 5));
        System.out.println(IntPower.raise(-2, -5)  + " " +
                           Math.pow(-2, -5));
        System.out.println(IntPower.raise(-2, 0)   + " " +
                           Math.pow(-2, 0));
        System.out.println(IntPower.raise(-2.5, 5) + " " +
                           Math.pow(-2.5, 5));
    }
}
/*
Output:
32.0 32.0
0.03125 0.03125
1.0 1.0
97.65625 97.65625

-32.0 -32.0
-0.03125 -0.03125
1.0 1.0
-97.65625 -97.65625
*/