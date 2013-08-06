package numbercruncher.program6_2;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 6-2d: Linear Regression (Interactive Standalone Demo)
 *
 * Interactively demonstrate linear regression.
 */
public class LinearRegressionDemo extends DemoFrame
{
    private static final String TITLE = "Linear Regression Demo";

    /**
     * Constructor.
     */
    private LinearRegressionDemo()
    {
        super(TITLE, new LinearRegressionPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new LinearRegressionDemo();
        frame.setVisible(true);
    }
}