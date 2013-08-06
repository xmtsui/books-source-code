package numbercruncher.program6_2;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.RegressionLine;
import numbercruncher.pointutils.InterRegressPanel;

/**
 * The demo panel for the Linear Regression program and applet.
 */
public class LinearRegressionPanel extends InterRegressPanel
{
    private static final int MAX_POINTS = 100;

    /** regression line function */
    RegressionLine line = new RegressionLine();

    /**
     * Constructor.
     */
    LinearRegressionPanel()
    {
        super(MAX_POINTS, "Regression line", "Reset", false);
    }

    /**
     * The user has added a data point.
     * @param r the dot's row
     * @param c the dot's column
     */
    protected void doDotAction(int r, int c)
    {
        if (n > 1) actionButton1.setEnabled(true);

        PlotProperties props = getPlotProperties();

        float x = props.getXMin() + c*props.getXDelta();
        float y = props.getYMax() - r*props.getYDelta();

        line.addDataPoint(new DataPoint(x, y));
    }

    /**
     * Button 1 action: Construct and plot the regression line.
     */
    protected void doButton1Action()
    {
        drawDots();
        plotOK = true;
        plotFunction();

        setHeaderLabel("Linear regression line:  y = " + line.getA1() +
                       "x + " + line.getA0());
    }

    /**
     * Button 2 action: Reset.
     */
    protected void doButton2Action()
    {
        reset();
        draw();

        setHeaderLabel("");
        actionButton1.setEnabled(false);
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Return the value of the regression line function at x.
     * @param x the value of x
     * @return the value of the function
     */
    public float valueAt(float x) { return line.at(x); }

    /**
     * Reset.
     */
    protected void reset()
    {
        super.reset();
        plotOK = false;
        line.reset();
    }
}
