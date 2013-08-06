package numbercruncher.program9_1;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 9-1d: Graphic Transformations (Interactive Standalone Demo)
 *
 * Interactively demonstrate the use of graphic transformation matrices.
 */
public class TransformationDemo extends DemoFrame
{
    private static final String TITLE = "Graphic Transformations";

    /**
     * Constructor.
     */
    private TransformationDemo()
    {
        super(TITLE, new TransformationPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new TransformationDemo();
        frame.setVisible(true);
    }
}