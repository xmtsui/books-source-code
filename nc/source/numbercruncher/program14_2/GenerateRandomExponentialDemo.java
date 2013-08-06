package numbercruncher.program14_2;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 14-2d: Exponentially-Distributed Random Numbers
 *                (Standalone Demo)
 *
 * Demonstrate algorithms for generating exponentially-distributed
 * random numbers.
 */
public class GenerateRandomExponentialDemo extends DemoFrame
{
    private static final String TITLE = "Exponentially-Distributed Random Numbers";

    /**
     * Constructor.
     */
    private GenerateRandomExponentialDemo()
    {
        super(TITLE, new RandomExponentialPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new GenerateRandomExponentialDemo();
        frame.setVisible(true);
    }
}