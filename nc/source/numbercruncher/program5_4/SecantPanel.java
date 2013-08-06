package numbercruncher.program5_4;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.RootFinder;
import numbercruncher.mathutils.SecantRootFinder;
import numbercruncher.rootutils.PlotFunction;
import numbercruncher.rootutils.RootFinderPanel;

/**
 * The demo panel for the Secant Algorithm program and applet.
 */
public class SecantPanel extends RootFinderPanel
{
    private static final int MAX_ITERS = 50;

    private static final String FUNCTION_IMAGE_FILE_NAME = "root-finder.gif";
    private static final String FUNCTION_FRAME_TITLE =
                                            "Click to choose a function f(x)";

    /** control panel */    private Panel controlPanel = new Panel();
    /** xnm1 label */       private Label xnm1Label    = new Label("x[n-1]:");
    /** xnm1 test */        private Label xnm1Text     = new Label(" ");
    /** xn label */         private Label xnLabel      = new Label("x[n]:");
    /** xn text */          private Label xnText       = new Label(" ");
    /** xnp1 label */       private Label xnp1Label    = new Label("x[n+1]:");
    /** xnp1 text */        private Label xnp1Text     = new Label(" ");

    /** Secant root finder */
    private SecantRootFinder finder;

    /** Functions whose roots to find */
    private static PlotFunction FUNCTIONS[] = {
        new PlotFunction("x^2 - 4",              -0.25f, 5.25f, -5.5f, 25.25f),
        new PlotFunction("-x^2 + 4x + 5",        -0.5f, 10.25f, -25.5f, 10.25f),
        new PlotFunction("x^3 + 3x^2 - 9x - 10", -6.25f, 4.25f, -20.5f, 20.25f),
        new PlotFunction("x^2 - 2x + 3",         -7.25f, 9.25f, -1.5f, 25.25f),
        new PlotFunction("2x^3 - 10x^2 + 11x - 5",
                                                 -0.5f, 5.25f, -10.5f, 25.25f),
        new PlotFunction("e^-x - x",             -0.5f, 2.25f, -1.75f, 1.75f),
        new PlotFunction("x - e^(1/x)",          -4.25f, 4.25f, -10.25f, 3.25f),
    };

    /** array of plot endpoint columns #1 */    private int cs1[] = new int[3];
    /** array of plot endpoint rows #1 */       private int rs1[] = new int[3];
    /** array of plot endpoint columns #2 */    private int cs2[] = new int[3];
    /** array of plot endpoint rows #2 */       private int rs2[] = new int[3];
    /** line count */                           private int k;

    /** minimum x value */              private float xMin;
    /** maximum x value */              private float xMax;
    /** maximum y value */              private float yMax;
    /** x delta per pixel */            private float xDelta;
    /** y delta per pixel */            private float yDelta;
    /** x[n-1] value */                 private float xnm1;
    /** x[n] value */                   private float xn;
    /** x[n+1] value */                 private float xnp1;
    /** f(x[n-1]) */                    private float fnm1;
    /** f([n]) */                       private float fn;
    /** f(x[n+1]) */                    private float fnp1;
    /** count of starting values */     private int   xStartCount;
    /** x-axis row */                   private int   xAxisRow;

    /** true if OK to set values */     private boolean setOK = true;

    // Constructor
    SecantPanel()
    {
        super(FUNCTIONS, FUNCTION_IMAGE_FILE_NAME, FUNCTION_FRAME_TITLE);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Secant controls
        xnm1Label.setFont(labelFont);
        xnm1Label.setAlignment(Label.RIGHT);
        xnm1Text.setFont(textFont);
        xnm1Text.setAlignment(Label.LEFT);
        xnLabel.setFont(labelFont);
        xnLabel.setAlignment(Label.RIGHT);
        xnText.setFont(textFont);
        xnText.setAlignment(Label.LEFT);
        xnp1Label.setFont(labelFont);
        xnp1Label.setAlignment(Label.RIGHT);
        xnp1Label.setForeground(Color.blue);
        xnp1Text.setFont(textFont);
        xnp1Text.setAlignment(Label.LEFT);

        // Secant panel
        controlPanel.setLayout(new GridLayout(0, 5, 5, 2));
        controlPanel.add(xnm1Label);
        controlPanel.add(xnm1Text);
        controlPanel.add(xnp1Label);
        controlPanel.add(xnp1Text);
        controlPanel.add(runButton);
        controlPanel.add(xnLabel);
        controlPanel.add(xnText);
        controlPanel.add(nLabel);
        controlPanel.add(nText);
        controlPanel.add(stepButton);
        addDemoControls(controlPanel);
    }

    /**
     * Draw the vertical lines and the secant.
     * @param verticalsFlag true to draw the vertical lines
     */
    private void drawLines(boolean verticalsFlag)
    {
        k = 0;

        if (verticalsFlag)  drawVerticalLines();
        if (finder != null) drawSecant();

        plotLines(cs1, rs1, cs2, rs2, k, Color.red);
    }

    /**
     * Draw the vertical lines.
     */
    private void drawVerticalLines()
    {
        // The vertical line from (x[n-1], 0) to (x[n-1], f(x[n-1]))
        if ((xStartCount == 1) || (finder != null)) {
            int cnm1 = Math.round((xnm1 - xMin)/xDelta);
            int rnm1 = Math.round((yMax - fnm1)/yDelta);

            cs1[k] = cs2[k] = cnm1;
            rs1[k] = xAxisRow;
            rs2[k] = rnm1;
            ++k;
        }

        // The vertical line from (x[n], 0) to (x[n], f(x[n]))
        if ((xStartCount == 2) || (finder != null)) {
            int cn = Math.round((xn   - xMin)/xDelta);
            int rn = Math.round((yMax - fn  )/yDelta);

            cs1[k] = cs2[k] = cn;
            rs1[k] = xAxisRow;
            rs2[k] = rn;
            ++k;
        }
    }

    /**
     * Draw the secant so that it crosses the x axis.
     */
    private void drawSecant()
    {
        int c1, c2, r1, r2;

        // Opposite signs?
        if (fnm1*fn < 0) {
            c1 = Math.round((xnm1 - xMin)/xDelta);
            c2 = Math.round((xn   - xMin)/xDelta);
            r1 = Math.round((yMax - fnm1)/yDelta);
            r2 = Math.round((yMax - fn  )/yDelta);
        }

        // |f(x[n+1])| > |f(x[n])| ?
        else if (Math.abs(fn) > Math.abs(fnm1)) {
            c1 = Math.round((xn   - xMin)/xDelta);
            c2 = Math.round((xnp1 - xMin)/xDelta);
            r1 = Math.round((yMax - fn  )/yDelta);
            r2 = xAxisRow;
        }

        // |f(x[n+1])| <= |f(x[n])| ?
        else {
            c1 = Math.round((xnm1 - xMin)/xDelta);
            c2 = Math.round((xnp1 - xMin)/xDelta);
            r1 = Math.round((yMax - fnm1)/yDelta);
            r2 = xAxisRow;
        }

        // Check the endpoints.
        c1 = Math.min(c1, Short.MAX_VALUE);
        c1 = Math.max(c1, Short.MIN_VALUE);
        r1 = Math.min(r1, Short.MAX_VALUE);
        r1 = Math.max(r1, Short.MIN_VALUE);
        c2 = Math.min(c2, Short.MAX_VALUE);
        c2 = Math.max(c2, Short.MIN_VALUE);
        r2 = Math.min(r2, Short.MAX_VALUE);
        r2 = Math.max(r2, Short.MIN_VALUE);

        // Draw the secant.
        cs1[k] = c1;  rs1[k] = r1;  cs2[k] = c2;  rs2[k] = r2;
        ++k;
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Draw the contents of the secant demo panel.
     */
    public void draw()
    {
        super.draw();

        setOK       = true;
        xStartCount = 0;
        finder      = null;

        nText.setText("0");
        xnm1Text.setText(" ");
        xnText.setText(" ");
        xnp1Text.setText(" ");

        // Plot properties
        PlotProperties props = getPlotProperties();
        xMin     = props.getXMin();
        xMax     = props.getXMax();
        yMax     = props.getYMax();
        xDelta   = props.getXDelta();
        yDelta   = props.getYDelta();
        xAxisRow = props.getXAxisRow();

        // Enable buttons after two starting values are set.
        runButton.setEnabled(false);
        stepButton.setEnabled(false);
    }

    /**
     * Mouse click on the plot: Set a starting x value.
     */
    public void mouseClickedOnPlot(MouseEvent ev)
    {
        if (!setOK || (++xStartCount > 2)) return;

        PlotProperties props        = getPlotProperties();
        PlotFunction   plotFunction = getSelectedPlotFunction();

        int   c = ev.getX();
        float x = props.getXMin() + c*props.getXDelta();

        // First starting value.
        if (xStartCount == 1) {
            xnm1 = x;
            fnm1 = plotFunction.at(xnm1);
            xnm1Text.setText(Float.toString(xnm1));
        }

        // Second starting value.
        else {
            xn = x;
            fn = plotFunction.at(xn);
            xnText.setText(Float.toString(xn));

            runButton.setEnabled(true);
            stepButton.setEnabled(true);
        }

        drawLines(true);

        // Create the secant root finder.
        if (xStartCount == 2) {
            finder = new SecantRootFinder(
                            (Function) plotFunction.getFunction(), xnm1, xn);
        }
    }

    /**
     * Notification that a user input error occurred.
     * Disable user starting points.
     */
    protected void userErrorOccurred() { setOK = false; }

    /**
     * Iterate to compute x[n+1] from the secant through
     * (x[n-1], f(x[n-1])) and (x[n], f(x[n])).
     */
    protected void step()
    {
        try {
            if (finder.step()) {
                successfullyConverged();
            }
        }
        catch(RootFinder.IterationCountExceededException ex) {
            iterationLimitExceeded(MAX_ITERS, xnp1Text);
            return;
        }
        catch(RootFinder.PositionUnchangedException ex) {
            // ignore
        }

        int n = finder.getIterationCount();

        if (n > 1) drawLines(true);     // erase the previous lines

        xnm1 = finder.getXnm1();    fnm1 = finder.getFnm1();
        xn   = finder.getXn();      fn   = finder.getFn();
        xnp1 = finder.getXnp1();    fnp1 = finder.getFnp1();

        drawLines(n > 1);               // draw the new lines

        // Update text controls.
        nText.setText(Integer.toString(n));
        xnm1Text.setText(Float.toString(xnm1));
        xnp1Text.setText(Float.toString(xnp1));
        xnText.setText(Float.toString(xn));
    }
}
