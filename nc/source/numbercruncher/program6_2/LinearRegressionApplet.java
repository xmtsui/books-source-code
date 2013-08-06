package numbercruncher.program6_2;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 6-2a: Linear Regression (Interactive Applet)
 *
 * Interactively demonstrate linear regression.
 */
public class LinearRegressionApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public LinearRegressionApplet()
    {
        super(new LinearRegressionPanel());
    }
}