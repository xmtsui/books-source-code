package numbercruncher.program6_1;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.InterpolationPolynomial;
import numbercruncher.pointutils.InterRegressPanel;

/**
 * The demo panel for the Polynomial Interpolation demo and applet.
 */
public class InterpolationPanel extends InterRegressPanel
{
    private static final int MAX_POINTS = 10;

    /** interpolation polynomnial function */
    InterpolationPolynomial p = new InterpolationPolynomial(MAX_POINTS);

    /**
     * Constructor.
     */
    InterpolationPanel() { super(MAX_POINTS, "Interpolate", "Reset"); }

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

        p.addDataPoint(new DataPoint(x, y));
    }

    /**
     * Button 1 action: Construct and plot the interpolation function.
     */
    protected void doButton1Action()
    {
        drawDots();
        plotOK = true;
        plotFunction();

        setHeaderLabel("Polynomial interpolation function of degree " + (n-1));
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
     * Return the value of the polynomial interpolation function at x.
     * @param x the value of x
     * @return the value of the function
     */
    public float valueAt(float x) { return p.at(x); }

    /**
     * Reset.
     */
    protected void reset()
    {
        super.reset();
        plotOK = false;
        p.reset();
    }
}