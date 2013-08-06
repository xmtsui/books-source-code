package numbercruncher.program5_2;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 5-2d: Regula Falsi Algorithm (Interactive Standalone Demo)
 *
 * Interactively demonstrate the Regula Falsi Algorithm on various
 * functions.  Either single-step or let the program automatically
 * step once each half second.
 */
public class RegulaFalsiDemo extends DemoFrame
{
    private static final String TITLE = "Regula Falsi Demo";

    /**
     * Constructor.
     */
    private RegulaFalsiDemo()
    {
        super(TITLE, new RegulaFalsiPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new RegulaFalsiDemo();
        frame.setVisible(true);
    }
}
