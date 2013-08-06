package numbercruncher.program7_1;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.Epsilon;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.InterpolationPolynomial;
import numbercruncher.mathutils.Integrator;
import numbercruncher.mathutils.TrapezoidalIntegrator;
import numbercruncher.mathutils.SimpsonsIntegrator;
import numbercruncher.pointutils.UserPointPanel;

/**
 * The demo panel for the numerical integration demo and applet.
 */
public class IntegrationPanel extends UserPointPanel
{
    private static final int   MAX_POINTS    = 10;
    private static final int   MAX_INTERVALS = 512;
    private static final float TOLERANCE     = 100*Epsilon.floatValue();

    private static final float FROM_LIMIT =  2.0f;
    private static final float TO_LIMIT   = 10.0f;

    private static final int   TRAPEZOIDAL   = 0;
    private static final int   SIMPSONS      = 1;

    private static final String ALGORITHMS[] = {"the Trapezoidal", "Simpson's"};

    private static final Color COLOR_0 = new Color(173, 216, 230);
    private static final Color COLOR_1 = new Color(195, 236, 255);

    /** area label */   private Label  areaLabel        = new Label("Area:");
    /** area value */   private Label  areaText         = new Label(" ");
    /** algorithm
        label */        private Label  integratorLabel  = new Label("Algorithm:");
    /** algorithm
        choice */       private Choice integratorChoice = new Choice();

    /** true if OK to plot function */  private boolean plotOK = false;

    /** integrand (interpolation polynomnial function) */
    InterpolationPolynomial integrand = new InterpolationPolynomial(MAX_POINTS);

    /** integrator */                   Integrator integrator;

    /** number of intervals */          private int     intervals = 1;
    /** true if function plotted */     private boolean isPlotted = false;

    /** chosen algorithm */             private int   algorithm;
    /** computed area */                private float area;

    /**
     * Constructor.
     */
    IntegrationPanel()
    {
        super(MAX_POINTS, "# points", "Plot integrand", "Reset");

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Controls.
        areaLabel.setFont(labelFont);
        areaLabel.setAlignment(Label.RIGHT);
        areaLabel.setForeground(Color.blue);
        areaText.setFont(textFont);
        areaText.setAlignment(Label.LEFT);
        integratorLabel.setFont(labelFont);

        integratorChoice.add("Trapezoidal");
        integratorChoice.add("Simpson's");

        // Control panel.
        controlPanel.setLayout(new GridLayout(0, 4, 5, 2));
        controlPanel.add(nLabel);
        controlPanel.add(nText);
        controlPanel.add(integratorLabel);
        controlPanel.add(actionButton1);
        controlPanel.add(areaLabel);
        controlPanel.add(areaText);
        controlPanel.add(integratorChoice);
        controlPanel.add(actionButton2);
        addDemoControls(controlPanel);

        // Algorithm choice handler.
        integratorChoice.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ev)
            {
                algorithm = integratorChoice.getSelectedIndex();
                if (isPlotted) plot();
            }
        });
    }

    /**
     * Plot the function.
     */
    private void plot()
    {
        clear();
        plotOK = true;
        plotFunction();
        isPlotted = true;

        actionButton1.setLabel("Step");
        actionButton1.setEnabled(true);
        actionButton2.setEnabled(true);

        // Check the function.
        try {
            checkFunctionInBounds(FROM_LIMIT, TO_LIMIT);
        }
        catch(Exception ex) {
            processUserError(ex.getMessage());
            return;
        }

        setHeaderLabel("Integration by " + ALGORITHMS[algorithm] +
                       " Algorithm over the interval [" +
                       FROM_LIMIT + ", " + TO_LIMIT + "]");

        nLabel.setText("# intervals");
        nText.setText("0");
        areaText.setText(" ");

        n         = 0;
        intervals = 1;
        area      = 0;

        integrator = (algorithm == TRAPEZOIDAL)
                            ? (Integrator) new TrapezoidalIntegrator(integrand)
                            : (Integrator) new SimpsonsIntegrator(integrand);
    }

    /**
     * Single step an area computation with a higher number of intervals.
     */
    private void step()
    {
        // Draw the function plot.
        clear();
        plotFunction();
        nText.setText(Integer.toString(intervals));

        float h = (TO_LIMIT - FROM_LIMIT)/intervals;   // interval width
        float prevArea = area;
        area = integrator.integrate(FROM_LIMIT, TO_LIMIT, intervals);

        // Show the trapezoidal regions.
        for (int i = 0; i < intervals; ++i) {
            showRegion(i, h);
        }

        intervals *= 2;
        areaText.setText(Float.toString(area));

        // Stop if exceeded the maximum number of intervals
        // or if the computed area is no longer changing.
        float ratio = Math.abs((area - prevArea)/area);
        if ((intervals > MAX_INTERVALS) || (ratio < TOLERANCE)) {
            actionButton1.setEnabled(false);
        }

        // Redraw the function plot.
        plotFunction();
    }

    /**
     * Display the ith region by coloring it.
     * @param i the value of i
     * @param h the width of the region
     */
    private void showRegion(int i, float h)
    {
        if (algorithm == TRAPEZOIDAL) {
            showTrapezoidalRegion(i, h);
        }
        else {
            showParabolicRegion(i, h);
        }
    }

    /**
     * Display the ith trapezoidal region by coloring it.
     * @param i the value of i
     * @param h the width of the region
     */
    private void showTrapezoidalRegion(int i, float h)
    {
        float x1 = FROM_LIMIT + i*h;    // left bound
        float x2 = x1 + h;              // right bound
        float y1 = integrand.at(x1);    // integrand value at left bound
        float y2 = integrand.at(x2);    // integrand value at right bound

        // Plot properties.
        float xMin   = plotProps.getXMin();
        float yMax   = plotProps.getYMax();
        float xDelta = plotProps.getXDelta();
        float yDelta = plotProps.getYDelta();

        // Convert bounds to rows and columns.
        int c1 = Math.round((x1 - xMin)/xDelta);
        int c2 = Math.round((x2 - xMin)/xDelta);
        int r0 = Math.round(yMax/yDelta);
        int r1 = Math.round((yMax - y1)/yDelta);
        int r2 = Math.round((yMax - y2)/yDelta);

        int cs[] = {c1, c1, c2, c2};
        int rs[] = {r0, r1, r2, r0 };

        // Alternate colors.
        Color color = ((i & 1) == 0) ? COLOR_0 : COLOR_1;
        fillPolygon(cs, rs, 4, color);
    }

    /**
     * Display the ith parabolic region by coloring it.
     * @param i the value of i
     * @param h the width of the region
     */
    private void showParabolicRegion(int i, float h)
    {
        // Split the region in half.
        h /= 2;
        i *= 2;

        float x1 = FROM_LIMIT + i*h;        // left bound
        float x2 = x1 + h;                  // middle
        float x3 = x2 + h;                  // right bound
        float y1 = integrand.at(x1);        // integrand value at left bound
        float y2 = integrand.at(x2);        // integrand value at the middle
        float y3 = integrand.at(x3);        // integrand value at right bound

        // Interpolation parabola.
        InterpolationPolynomial parabola =
            new InterpolationPolynomial(new DataPoint[] {new DataPoint(x1, y1),
                                                         new DataPoint(x2, y2),
                                                         new DataPoint(x3, y3)});

        // Plot properties.
        float xMin   = plotProps.getXMin();
        float yMax   = plotProps.getYMax();
        float xDelta = plotProps.getXDelta();
        float yDelta = plotProps.getYDelta();

        // Convert bounds to rows and columns.
        int c1 = Math.round((x1 - xMin)/xDelta);
        int c3 = Math.round((x3 - xMin)/xDelta);
        int r0 = Math.round(yMax/yDelta);
        int r1 = Math.round((yMax - y1)/yDelta);
        int r3 = Math.round((yMax - y3)/yDelta);

        int size = c3 - c1 + 3;
        int cs[] = new int[size];
        int rs[] = new int[size];

        cs[0] = cs[1] = c1;
        cs[size-2] = cs[size-1] = c3;
        rs[0] = rs[size-1] = r0;
        rs[1] = r1;
        rs[size-2] = r3;

        int j = 2;
        for (int c = c1+1; c <= c3; ++c) {
            float x = c*xDelta + xMin;
            float y = parabola.at(x);
            int   r = Math.round((yMax - y)/yDelta);

            cs[j] = c;
            rs[j] = r;
            ++j;
        }

        // Alternate colors.
        Color color = ((i & 3) == 0) ? COLOR_0 : COLOR_1;
        fillPolygon(cs, rs, size, color);
    }

    //------------------//
    // Method overrides //
    //------------------//

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

        integrand.addDataPoint(new DataPoint(x, y));
    }

    /**
     * Button 1 action: Plot the function or
     *                  single step an area computation.
     */
    protected void doButton1Action()
    {
        if (isPlotted) {
            step();
        }
        else {
            plot();
        }
    }

    /**
     * Button 2 action: Reset.
     */
    protected void doButton2Action()
    {
        reset();
        draw();
    }

    /**
     * Return whether or not it is OK to add another data point.
     * @return true if OK, otherwise false
     */
    public boolean dotOK() { return !isPlotted; }

    /**
     * Return whether or not it's OK to plot the function.
     * @return true if OK, otherwise false
     */
    protected boolean plotOK() { return plotOK; }

    /**
     * Return the value of the polynomial interpolation function at x.
     * @param x the value of x
     * @return the value of the function
     */
    public float valueAt(float x) { return integrand.at(x); }

    /**
     * Reset.
     */
    protected void reset()
    {
        super.reset();

        intervals = 1;
        area      = 0;
        isPlotted = false;
        plotOK    = false;

        integrand.reset();

        setHeaderLabel("First plot two to ten points with mouse clicks, " +
                       "then press the 'Plot integrand' button.", Color.blue);

        nLabel.setText("# points");
        areaText.setText(" ");

        actionButton1.setLabel("Plot integrand");
        actionButton1.setEnabled(false);
    }
}