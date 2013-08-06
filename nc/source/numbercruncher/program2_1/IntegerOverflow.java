package numbercruncher.program2_1;

/**
 * PROGRAM 2-1: Integer Overflow
 *
 * Show the effects of integer overflow and of division by zero.
 */
public class IntegerOverflow
{
    public static void main(String args[])
    {
        int big = 2147483645;

        for (int i = 1; i <= 4; ++i) {
            System.out.println(big + " + " + i + " = " + (big + i));
        }
        System.out.println();

        for (int i = 1; i <= 4; ++i) {
            System.out.println(big + " * " + i + " = " + (big*i));
        }
        System.out.println();

        int dze = big/0;
    }
}
/*
Output:
2147483645 + 1 = 2147483646
2147483645 + 2 = 2147483647
2147483645 + 3 = -2147483648
2147483645 + 4 = -2147483647

2147483645 * 1 = 2147483645
2147483645 * 2 = -6
2147483645 * 3 = 2147483639
2147483645 * 4 = -12

java.lang.ArithmeticException: / by zero
	at numbercruncher.program2_1.IntegerOverflow.main(IntegerOverflow.java:24)
Exception in thread "main"
*/