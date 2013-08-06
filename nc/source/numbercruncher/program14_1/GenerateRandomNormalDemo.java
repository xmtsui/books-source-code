package numbercruncher.program14_1;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 14-1d: Normally-Distributed Random Numbers (Standalone Demo)
 *
 * Demonstrate algorithms for generating normally-distributed
 * random numbers.
 */
public class GenerateRandomNormalDemo extends DemoFrame
{
    private static final String TITLE = "Normally-Distributed Random Numbers";

    /**
     * Constructor.
     */
    private GenerateRandomNormalDemo()
    {
        super(TITLE, new RandomNormalPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new GenerateRandomNormalDemo();
        frame.setVisible(true);
    }
}