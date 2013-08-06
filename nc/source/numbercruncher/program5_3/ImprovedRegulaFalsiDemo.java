package numbercruncher.program5_3;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 5-3d: Improved Regula Falsi Algorithm (Interactive Standalone Demo)
 *
 * Interactively demonstrate the improved Regula Falsi Algorithm
 * on various functions.  Either single-step or let the program
 * automatically step once each half second.
 */
public class ImprovedRegulaFalsiDemo extends DemoFrame
{
    private static final String TITLE = "Improved Regula Falsi Demo";

    /**
     * Constructor.
     */
    private ImprovedRegulaFalsiDemo()
    {
        super(TITLE, new ImprovedRegulaFalsiPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new ImprovedRegulaFalsiDemo();
        frame.setVisible(true);
    }
}
