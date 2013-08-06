package numbercruncher.program5_6;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.RootFinder;
import numbercruncher.mathutils.FixedPointRootFinder;
import numbercruncher.rootutils.PlotFunction;
import numbercruncher.rootutils.RootFinderPanel;

/**
 * The demo panel for the Fixed Point Iteration Method program and applet.
 */
public class FixedPointPanel extends RootFinderPanel
{
    private static final int MAX_ITERS = 50;

    private static final String FUNCTION_IMAGE_FILE_NAME = "fixed-point.gif";
    private static final String FUNCTION_FRAME_TITLE =
                    "Click to choose a convergent or a divergent function g(x)";

    /** control panel */    private Panel controlPanel = new Panel();
    /** x0 label */         private Label x0Label      = new Label("x[0]:");
    /** x0 text */          private Label x0Text       = new Label(" ");
    /** xn label */         private Label xnLabel      = new Label("x[n]:");
    /** xn text */          private Label xnText       = new Label(" ");

    /** true if OK to trace */  private boolean traceOK = true;

    /** Fixed-point iteration root finder */
    private FixedPointRootFinder finder;

    /** Functions to plot. */
    private static PlotFunction FUNCTIONS[] = {
        new PlotFunction("(x + 4/x)/2",         -10.5f, 10.5f, -10.5f, 10.5f),
        new PlotFunction("4/x",                 -10.5f, 10.5f, -10.5f, 10.5f),
        new PlotFunction("sqrt(x + 2)",         -3.25f, 5.25f, -0.25f, 3.25f),
        new PlotFunction("2/x + 1",             -3.25f, 5.25f, -5.25f, 5.25f),
        new PlotFunction("x*x - 2",             -2.25f, 5.25f, -2.5f, 10.25f),
        new PlotFunction("exp(-x)",             -0.5f, 2.5f, -0.25f, 1.25f),
        new PlotFunction("-log(x)",             -0.25f, 1.5f, -0.25f, 1.5f),
        new PlotFunction("exp(1/x)",            -3.25f, 4.25f, -0.5f, 5.25f),
        new PlotFunction("(x + exp(1/x))/2",    -3.25f, 5.25f, -2.25f, 4.25f),
        new PlotFunction("1/log(x)",            -0.25f, 4.25f, -3.25f, 3.25f),
        new PlotFunction("sin(x)/2 + 1",        -4.25f, 7.25f, -0.25f, 1.6f),
        new PlotFunction("1 + 1/x + 1/(x*x)",   -2.25f, 4.25f, -0.5f, 5.25f),
        new PlotFunction("20/(x*x + 2*x + 10)", -6.25f, 6.25f, -0.25f, 2.5f),
    };

    /** array of plot
     *  endpoint columns #1 */  private int cs1[] = new int[2*MAX_ITERS + 1];
    /** array of plot
     *  endpoint rows #1 */     private int rs1[] = new int[2*MAX_ITERS + 1];
    /** array of plot
     *  endpoint columns #2 */  private int cs2[] = new int[2*MAX_ITERS + 1];
    /** array of plot
     *  endpoint rows #2 */     private int rs2[] = new int[2*MAX_ITERS + 1];

    /** trace point index */    private int   k;
    /** starting value of x */  private float x0;

    /**
     * Constructor.
     */
    FixedPointPanel()
    {
        super(FUNCTIONS, true, true,
              FUNCTION_IMAGE_FILE_NAME, FUNCTION_FRAME_TITLE);
        k = 0;

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Fixed-point iteration controls.
        x0Label.setFont(labelFont);
        x0Label.setAlignment(Label.RIGHT);
        x0Text.setFont(textFont);
        x0Text.setAlignment(Label.LEFT);
        xnLabel.setFont(labelFont);
        xnLabel.setAlignment(Label.RIGHT);
        xnLabel.setForeground(Color.blue);
        xnText.setFont(textFont);
        xnText.setAlignment(Label.LEFT);

        // Fixed-point iteration panel.
        controlPanel.setLayout(new GridLayout(0, 4, 5, 2));
        controlPanel.add(x0Label);
        controlPanel.add(x0Text);
        controlPanel.add(xnLabel);
        controlPanel.add(xnText);
        controlPanel.add(nLabel);
        controlPanel.add(nText);
        addDemoControls(controlPanel);
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Draw the contents of the Fixed-point iteration demo panel.
     */
    public void draw()
    {
        super.draw();
        k = 0;
        traceOK = true;

        // Initialize n, x0, and xn in the control panel.
        nText.setText(" ");
        x0Text.setText(" ");
        xnText.setText(" ");

        PlotFunction plotFunction = getSelectedPlotFunction();

        // Create the fixed-point iteration root finder.
        finder = new FixedPointRootFinder((Function) plotFunction.getFunction());
    }

    /**
     * Mouse pressed event handler:  Animate the Newton trace.
     * (Callback from the plot panel)
     * @param ev the mouse event
     */
    public void mousePressedOnPlot(MouseEvent ev)
    {
        if (traceOK) animate(ev.getX());
    }

    /**
     * Mouse dragged event handler:  Animate the Newton trace.
     * (Callback from the plot panel)
     * @param ev the mouse event
     */
    public void mouseDraggedOnPlot(MouseEvent ev)
    {
        if (traceOK) animate(ev.getX());
    }

    /**
     * Notification that a user input error occurred.
     * Disable the Newton trace.
     */
    protected void userErrorOccurred()
    {
        traceOK = false;
    }

    //-----------------------//
    // Interactive animation //
    //-----------------------//

    /**
     * Animate a fixed-point iteration trace starting at column c.
     * @param c the column
     */
    private void animate(int c)
    {
        // Erase the previous fixed-point iteration trace, if any.
        if (k > 0) plotLines(cs1, rs1, cs2, rs2, k, Color.red);

        // Draw a new fixed-point iteration trace.
        iterate(x0(c));
        plotLines(cs1, rs1, cs2, rs2, k, Color.red);
    }

    /**
     * Return the starting value x0 at column c.
     * @param c the column
     * @return the starting value
     */
    private float x0(int c)
    {
        PlotProperties props = getPlotProperties();

        float x0 = props.getXMin() + c*props.getXDelta();
        x0Text.setText(Float.toString(x0));

        return x0;
    }

    /**
     * Iterate to create the graphic trace of fixed-point iteration
     * starting at x0.
     * @param x0 the starting value
     */
    private void iterate(float x0)
    {
        // Plot properties.
        PlotProperties plotProps = getPlotProperties();
        int   w        = plotProps.getWidth();
        int   h        = plotProps.getHeight();
        int   xAxisRow = plotProps.getXAxisRow();
        int   yAxisCol = plotProps.getYAxisColumn();
        float xMin     = plotProps.getXMin();
        float yMax     = plotProps.getYMax();
        float deltaX   = plotProps.getXDelta();
        float deltaY   = plotProps.getYDelta();

        float   xn;
        float   gn;
        boolean converged = false;

        k = 0;
        finder.reset(x0);

        // Apply fixed-point iteration. For each iteration, plot
        // the vertical at xn and the horizontal to the y = x line.
        do {
            try {
                converged = finder.step();
            }
            catch(RootFinder.IterationCountExceededException ex) {
                iterationLimitExceeded(MAX_ITERS, xnText);
                return;
            }
            catch(RootFinder.PositionUnchangedException ex) {
                // ignore
            }

            xn   = finder.getXn();
            gn   = finder.getGn();

            int c, r;

            // Plot the vertical. The initial vertical is from
            // (x0, 0) to (x0, g(x0)). Subsequent verticals are
            // from (xn, xn) to (xn, g(xn)).
            c = Math.min(Short.MAX_VALUE,
                     Math.max(Short.MIN_VALUE,
                              Math.round((xn - xMin)/deltaX)));
            cs1[k] = c;
            rs1[k] = k == 0 ? xAxisRow : rs1[k-1];
            cs2[k] = cs1[k];
            r = Math.min(Short.MAX_VALUE,
                     Math.max(Short.MIN_VALUE,
                              Math.round((yMax - gn)/deltaY)));
            rs2[k] = r;
            ++k;

            // Plot the horizontal from (xn, g(xn)) to
            // (g(xn), g(xn)).
            cs1[k] = cs2[k-1];
            rs1[k] = rs2[k-1];
            c = Math.min(Short.MAX_VALUE,
                     Math.max(Short.MIN_VALUE,
                              Math.round((gn - xMin)/deltaX)));
            cs2[k] = c;
            rs2[k] = rs1[k];
            ++k;
        } while (!converged);

        // Update the n and xn text controls.
        nText.setText(Integer.toString(finder.getIterationCount()));
        xnText.setText(Float.toString(xn));
    }

    //-----------//
    // Debugging //
    //-----------//

    /**
     * Main for debugging.
     * @param args the array of arguments
     */
//*
    public static void main(String args[])
    {
        Frame           test = new Frame();
        FixedPointPanel demo = new FixedPointPanel();

        Function function = new Function()
        {
            public float at( float x) { return (float) (x*x + 0.5); }
//            public float at( float x) { return (float) Math.sqrt(x); }
        };

        PlotFunction plotFunction =
            new PlotFunction(function, -2.25f, 2.25f, -1.25f, 5.25f);

        FUNCTIONS[0] = plotFunction;

/*
        Function function = new Function()
        {
            public float at( float x) { return (float) Math.exp(1/x); }
        };

        PlotFunction plotFunction =
            new PlotFunction(function, -3.25f, 4.255f, -0.25f, 5.25f);
*/

        test.setTitle("Fixed Point Test");
        test.setSize(600, 500);

        test.setLayout(new BorderLayout());
        test.add(demo, BorderLayout.CENTER);

        test.setVisible(true);

        demo.chooseFunction(0);
        demo.draw();
    }
//*/
}