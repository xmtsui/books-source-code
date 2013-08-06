package numbercruncher.program5_4;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 5-4d: Secant Algorithm (Interactive Standalone Demo)
 *
 * Interactively demonstrate the Secant Algorithm on various functions.
 */
public class SecantDemo extends DemoFrame
{
    private static final String TITLE = "Secant Demo";

    /**
     * Constructor.
     */
    private SecantDemo()
    {
        super(TITLE, new SecantPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new SecantDemo();
        frame.setVisible(true);
    }
}
