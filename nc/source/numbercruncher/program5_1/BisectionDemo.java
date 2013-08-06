package numbercruncher.program5_1;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 5-1d: Bisection Algorithm (Interactive Standalone Demo)
 *
 * Interactively demonstrate the Bisection Algorithm on various functions.
 * Either single-step or let the program automatically step once each
 * half second.
 */
public class BisectionDemo extends DemoFrame
{
    private static final String TITLE = "Bisection Demo";

    /**
     * Constructor.
     */
    private BisectionDemo()
    {
        super(TITLE, new BisectionPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new BisectionDemo();
        frame.setVisible(true);
    }
}
