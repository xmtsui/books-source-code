package numbercruncher.program14_3;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 14-3d: Graphic Buffons (Interactive Standalone Demo)
 *
 * Interactively demonstrate the use of graphic transformation matrices.
 */
public class BuffonDemo extends DemoFrame
{
    private static final String TITLE = "Pi by Buffon's Needles and the " +
                                        "Monte Carlo Algorithm";

    /**
     * Constructor.
     */
    private BuffonDemo()
    {
        super(TITLE, new BuffonPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new BuffonDemo();
        frame.setVisible(true);
    }
}