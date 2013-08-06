package numbercruncher.program10_2;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 10-2a: Polynomial Regression (Interactive Applet)
 *
 * Interactively demonstrate polynomial regression.
 */
public class RegressionApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public RegressionApplet()
    {
        super(new RegressionPanel());
    }
}