package numbercruncher.program5_5;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.RootFinder;
import numbercruncher.mathutils.NewtonsRootFinder;
import numbercruncher.rootutils.PlotFunction;
import numbercruncher.rootutils.RootFinderPanel;

/**
 * The demo panel for the Newton's Algorithm program and applet.
 */
class NewtonsPanel extends RootFinderPanel
{
    private static final int MAX_ITERS = 50;

    private static final String FUNCTION_IMAGE_FILE_NAME = "root-finder.gif";
    private static final String FUNCTION_FRAME_TITLE =
                                            "Click to choose a function f(x)";

    /** control panel */    private Panel controlPanel = new Panel();
    /** x[0] label */       private Label x0Label      = new Label("x[0]:");
    /** x[0] text */        private Label x0Text       = new Label(" ");
    /** x[n+1] label */     private Label xnp1Label    = new Label("x[n+1]:");
    /** x[n+1] text */      private Label xnp1Text     = new Label(" ");

    /** Newton's root finder */
    private NewtonsRootFinder finder;

    /** Functions to plot. */
    private static PlotFunction FUNCTIONS[] = {
        new PlotFunction("x^2 - 4",              -3.25f, 5.25f, -5.5f, 25.25f),
        new PlotFunction("-x^2 + 4x + 5",        -5.25f, 10.25f, -25.5f, 10.25f),
        new PlotFunction("x^3 + 3x^2 - 9x - 10", -6.25f, 4.25f, -20.5f, 20.25f),
        new PlotFunction("x^2 - 2x + 3",         -7.25f, 9.25f, -1.5f, 25.25f),
        new PlotFunction("2x^3 - 10x^2 + 11x - 5",
                                                 -0.5f, 5.25f, -10.5f, 25.25f),
        new PlotFunction("e^-x - x",             -0.5f, 2.25f, -1.75f, 1.75f),
        new PlotFunction("x - e^(1/x)",          -4.25f, 4.25f, -10.25f, 3.25f),
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

    /** true if OK to trace */  private boolean traceOK = true;

    /**
     * Constructor.
     */
    NewtonsPanel()
    {
        super(FUNCTIONS, FUNCTION_IMAGE_FILE_NAME, FUNCTION_FRAME_TITLE);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Newton controls.
        x0Label.setFont(labelFont);
        x0Label.setAlignment(Label.RIGHT);
        x0Text.setFont(textFont);
        x0Text.setAlignment(Label.LEFT);
        xnp1Label.setFont(labelFont);
        xnp1Label.setAlignment(Label.RIGHT);
        xnp1Label.setForeground(Color.blue);
        xnp1Text.setFont(textFont);
        xnp1Text.setAlignment(Label.LEFT);

        // Newton control panel.
        controlPanel.setLayout(new GridLayout(0, 4, 5, 2));
        controlPanel.add(x0Label);
        controlPanel.add(x0Text);
        controlPanel.add(xnp1Label);
        controlPanel.add(xnp1Text);
        controlPanel.add(nLabel);
        controlPanel.add(nText);
        addDemoControls(controlPanel);
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Draw the contents of the Newton demo panel.
     */
    public void draw()
    {
        super.draw();

        k = 0;
        traceOK = true;

        nText.setText(" ");
        x0Text.setText(" ");
        xnp1Text.setText(" ");

        PlotFunction plotFunction = getSelectedPlotFunction();

        // Create the Newton's root finder.
        finder = new NewtonsRootFinder((Function) plotFunction.getFunction());
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
     * Animate a Newton trace starting at column c.
     * @param c the column
     */
    private void animate(int c)
    {
        // Erase the previous Newton trace, if any.
        if (k > 0) plotLines(cs1, rs1, cs2, rs2, k, Color.red);

        // Draw a new Newton trace.
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
     * Iterate to create the graphic trace of Newton's algorithm
     * starting at x0.
     * @param x0 the starting value
     */
    private void iterate(float x0)
    {
        // Plot properties.
        PlotProperties props = getPlotProperties();
        int   w        = props.getWidth();
        int   h        = props.getHeight();
        int   xAxisRow = props.getXAxisRow();
        int   yAxisCol = props.getYAxisColumn();
        float xMin     = props.getXMin();
        float yMax     = props.getYMax();
        float deltaX   = props.getXDelta();
        float deltaY   = props.getYDelta();

        float   xn;
        float   fn;
        float   xnp1;
        boolean converged = false;

        k = 0;
        finder.reset(x0);

        // Apply Newton's algorithm. For each iteration, plot the
        // vertical at xn and the tangent at f(xn), i = 0..k.
        do {
            try {
                converged = finder.step();
            }
            catch(RootFinder.IterationCountExceededException ex) {
                iterationLimitExceeded(MAX_ITERS, xnp1Text);
                return;
            }
            catch(RootFinder.PositionUnchangedException ex) {
                // ignore
            }

            boolean inRange = true;

            xn   = finder.getXn();
            fn   = finder.getFn();
            xnp1 = finder.getXnp1();

            // Plot the vertical from (xn, 0) to (xn, f(xn)).
            // Truncate lines that go beyond the graph bounds.
            cs1[k] = Math.round((xn - xMin)/deltaX);
            rs1[k] = xAxisRow;
            cs2[k] = cs1[k];
            float r = (yMax - fn)/deltaY;
            if (r < Short.MIN_VALUE) {
                rs2[k] = 0;
                inRange = false;
            }
            else if (r > Short.MAX_VALUE) {
                rs2[k] = h;
                inRange = false;
            }
            else {
                rs2[k] = Math.round(r);
            }

            if (inRange) {

                // Plot the tangent at (xn, f(xn)).
                // Check for nonconvergence (c == infinity).
                ++k;
                cs1[k] = cs2[k-1];
                rs1[k] = rs2[k-1];
                float c = (xnp1 - xMin)/deltaX;
                if (c < Short.MIN_VALUE) {
                    cs2[k] = 0;
                    rs2[k] = rs1[k];
                    ++k;
                    xn = Float.NEGATIVE_INFINITY;
                    break;
                }
                else if (c > Short.MAX_VALUE) {
                    cs2[k] = w;
                    rs2[k] = rs1[k];
                    ++k;
                    xn = Float.POSITIVE_INFINITY;
                    break;
                }
                else {
                    cs2[k] = Math.round(c);
                    rs2[k] = xAxisRow;
                    ++k;
                }
            }
        } while (!converged);

        // Update the n and xn text controls.
        nText.setText(Integer.toString(finder.getIterationCount()));
        xnp1Text.setText(Float.toString(xnp1));
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
        Frame        test     = new Frame();
        NewtonsPanel demo     = new NewtonsPanel();

        Function     function = new Function()
        {
            public float at(float x) { return x*x*x - 1; }
            public float derivativeAt(float x) { return 3*x*x; }
        };

        PlotFunction plotFunction =
            new PlotFunction(function, -2.25f, 2.25f, -2.25f, 2.25f);
/*
        Function     function = new Function()
        {
            public float at(float x) { return x*x - 4*x + 4; }
            public float derivativeAt(float x) { return 2*x - 4; }
        };

        PlotFunction plotFunction =
            new PlotFunction(function, -1.25f, 5.255f, -0.5f, 5.25f);

        Function     function = new Function()
        {
            public float at(float x) { return x*x*x - 6*x*x + 12*x - 8; }
            public float derivativeAt(float x) { return 3*x*x - 12*x + 12; }
        };

        PlotFunction plotFunction =
            new PlotFunction(function, -0.25f, 5.255f, -5.25f, 5.25f);
*/

        FUNCTIONS[0] = plotFunction;

        test.setTitle("Newton's Test");
        test.setSize(600, 500);

        test.setLayout(new BorderLayout());
        test.add(demo, BorderLayout.CENTER);

        test.setVisible(true);

        demo.chooseFunction(0);
        demo.draw();
    }
//*/
}