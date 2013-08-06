package numbercruncher.program14_3;

import numbercruncher.mathutils.AlignRight;

/**
 * PROGRAM 14-3: Buffon's Needle
 *
 * Demonstrate how we can calculate the value of pi using the
 * Monte Carlo technique and Buffon's needle algorithm.
 */
public class Buffon
{
    private static final int MAX_NEEDLES = 10000000;    // 10 million
    private static final int GROUP_SIZE  = MAX_NEEDLES/20;

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        Needles    needles = new Needles(0, 0, 0, 10.5f);
        AlignRight ar      = new AlignRight();

        ar.print("Needles", 15);  ar.print("Crossings", 15);
        ar.print("Pi", 15);       ar.print("Error", 15);
        ar.underline();

        do {
            // Drop a group of needles.
            for (int i = 0; i < GROUP_SIZE; ++i) needles.dropNext();

            ar.print(needles.getCount(), 15);
            ar.print(needles.getCrossings(), 15);
            ar.print(needles.getPi(), 15);
            ar.print(needles.getError(), 15);
            ar.println();
        } while (needles.getCount() < MAX_NEEDLES);
    }
}
/*
Output:
        Needles      Crossings             Pi          Error
------------------------------------------------------------
         500000         318282      3.1418679   2.7513504E-4
        1000000         636433      3.1425147   9.2196465E-4
        1500000         954980       3.141427  -1.6570091E-4
        2000000        1273603       3.140696   -8.966923E-4
        2500000        1591847      3.1410053   -5.874634E-4
        3000000        1910364      3.1407628   -8.299351E-4
        3500000        2229064      3.1403315  -0.0012612343
        4000000        2547078       3.140854   -7.388592E-4
        4500000        2865942      3.1403286  -0.0012640953
        5000000        3184564      3.1401472  -0.0014455318
        5500000        3502476      3.1406353    -9.57489E-4
        6000000        3820892      3.1406279     -9.6488E-4
        6500000        4139230      3.1406808  -9.1195107E-4
        7000000        4457471      3.1407945   -7.982254E-4
        7500000        4775901      3.1407685    -8.24213E-4
        8000000        5093656      3.1411622  -4.3058395E-4
        8500000        5411821      3.1412716  -3.2114983E-4
        9000000        5730361      3.1411633  -4.2939186E-4
        9500000        6048652      3.1411958  -3.9696693E-4
       10000000        6366532      3.1414278  -1.6498566E-4
*/