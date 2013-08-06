package numbercruncher.program10_2;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.RegressionPolynomial;
import numbercruncher.matrix.MatrixException;
import numbercruncher.pointutils.InterRegressPanel;

/**
 * The demo panel for the Polynomial Regression program and applet.
 */
public class RegressionPanel extends InterRegressPanel
{
    private static final int MAX_POINTS = 100;

    /** regression polynomial degree */     int degree = 1;
    /** regression polynomial function */   RegressionPolynomial poly;

    /**
     * Constructor.
     */
    RegressionPanel()
    {
        super(MAX_POINTS, "Regression poly", "Reset", true);
        poly = new RegressionPolynomial(degree, MAX_POINTS);
    }

    /**
     * The user has added a data point.
     * @param r the dot's row
     * @param c the dot's column
     */
    protected void doDotAction(int r, int c)
    {
        if (n > degree) actionButton1.setEnabled(true);

        PlotProperties props = getPlotProperties();

        float x = props.getXMin() + c*props.getXDelta();
        float y = props.getYMax() - r*props.getYDelta();

        poly.addDataPoint(new DataPoint(x, y));
    }

    /**
     * Button 1 action: Construct and plot the regression polynomial.
     */
    protected void doButton1Action()
    {
        drawDots();
        plotOK = true;

        try {
            poly.computeCoefficients();
            plotFunction();

            String label   = "Regression polynomial of degree " + degree;
            String message = poly.getWarningMessage();
            if (message != null) label += "  (WARNING: " + message;

            setHeaderLabel(label);
        }
        catch(Exception ex) {
            setHeaderLabel("Could not generate polynomial:  " +
                           ex.getMessage(), MAROON);
        }
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
     * Return the value of the regression poly function at x.
     * @param x the value of x
     * @return the value of the function
     */
    public float valueAt(float x) { return poly.at(x); }

    /**
     * Notification that the plot bounds changed.
     * Redraw the panel.
     */
    public void plotBoundsChanged()
    {
        n = 0;
        draw();
    }

    /**
     * The degree has changed.
     * @param degree the new degree
     */
    protected void degreeChanged(int degree)
    {
        this.degree = degree;
        plotOK     = false;

        DataPoint data[] = poly.getDataPoints();

        poly = new RegressionPolynomial(degree, MAX_POINTS);
        for (int i = 0; i < n; ++i) poly.addDataPoint(data[i]);

        actionButton1.setEnabled(n > degree);
    }

    /**
     * Reset.
     */
    protected void reset()
    {
        super.reset();
        plotOK = false;
        poly.reset();
    }
}
