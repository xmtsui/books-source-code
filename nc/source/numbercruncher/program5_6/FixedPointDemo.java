package numbercruncher.program5_6;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 5-6d: Fixed-Point Iteration (Interactive Standalone Demo)
 *
 * Interactively demonstrate Fixed-Point Iteration Algorithm
 * on various functions.
 */
public class FixedPointDemo extends DemoFrame
{
    private static final String TITLE = "Fixed-Point Iteration Demo";

    // Constructor
    private FixedPointDemo()
    {
        super(TITLE, new FixedPointPanel());
    }

    // Main
    public static void main(String args[])
    {
        Frame frame = new FixedPointDemo();
        frame.setVisible(true);
    }
}