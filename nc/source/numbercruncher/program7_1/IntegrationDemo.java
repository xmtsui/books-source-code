package numbercruncher.program7_1;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 7-1d: Integration (Interactive Standalone Demo)
 *
 * Interactively demonstrate numerical integration algorithms.
 */
public class IntegrationDemo extends DemoFrame
{
    private static final String TITLE = "Numerical Integration";

    /**
     * Constructor.
     */
    private IntegrationDemo()
    {
        super(TITLE, new IntegrationPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new IntegrationDemo();
        frame.setVisible(true);
    }
}