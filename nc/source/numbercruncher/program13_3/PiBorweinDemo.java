package numbercruncher.program13_3;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 13-3d: The Borwein Pi Algorithm (Interactive Standalone Demo)
 *
 * Compute digits of pi by the Borwein algorithm.
 */
public class PiBorweinDemo extends DemoFrame
{
    private static final String TITLE = "Computing pi";

    /**
     * Constructor.
     */
    private PiBorweinDemo()
    {
        super(TITLE, new PiBorweinPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new PiBorweinDemo();
        frame.setVisible(true);
    }
}