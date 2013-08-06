package numbercruncher.program5_5;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 5-5d: Newton's Algorithm (Interactive Standalone Demo)
 *
 * Interactively demonstrate Newton's Algorithm on various functions.
 */
public class NewtonsDemo extends DemoFrame
{
    private static final String TITLE = "Newton's Demo";

    /**
     * Constructor.
     */
    private NewtonsDemo()
    {
        super(TITLE, new NewtonsPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new NewtonsDemo();
        frame.setVisible(true);
    }
}