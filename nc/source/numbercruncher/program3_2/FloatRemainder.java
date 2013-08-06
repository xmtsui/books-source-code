package numbercruncher.program3_2;

/**
 * PROGRAM 3-2: Float Remainder
 *
 * Demonstrate the float remainder operation.
 */
public class FloatRemainder
{
    public static void main(String args[])
    {
        float values[] = {
             5f,   3f,
             5.5f, 1.1f,
            -5.5f, 2.1f,
             5.5f, -3.1f,
            -5.5f, -4.1f,
        };

        for (int i = 0; i < values.length/2; ++i) {
            float x = values[2*i];
            float y = values[2*i+1];

            System.out.println(x + " % " + y + " = " + x%y);
        }
    }
}
/*
Output:
5.0 % 3.0 = 2.0
5.5 % 1.1 = 1.0999999
-5.5 % 2.1 = -1.3000002
5.5 % -3.1 = 2.4
-5.5 % -4.1 = -1.4000001
*/