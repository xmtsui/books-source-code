package numbercruncher.program5_1;

import java.awt.*;

import numbercruncher.graphutils.PlotProperties;
import numbercruncher.mathutils.Function;
import numbercruncher.mathutils.RootFinder;
import numbercruncher.mathutils.BisectionRootFinder;
import numbercruncher.rootutils.PlotFunction;
import numbercruncher.rootutils.RootFinderPanel;

/**
 * The demo panel for the Bisection Algorithm program and applet.
 */
public class BisectionPanel extends RootFinderPanel
{
    private static final int MAX_ITERS = 50;

    private static final String FUNCTION_IMAGE_FILE_NAME = "root-finder.gif";
    private static final String FUNCTION_FRAME_TITLE =
                                            "Click to choose a function f(x)";

    /** x-negative label */ private Label xNegLabel = new Label("x Neg:");
    /** x-negative text */  private Label xNegText  = new Label(" ");
    /** x-middle label */   private Label xMidLabel = new Label("x Mid:");
    /** x-middle text */    private Label xMidText  = new Label(" ");
    /** x-positive label */ private Label xPosLabel = new Label("x Pos:");
    /** x-positive text */  private Label xPosText  = new Label(" ");

    /** Bisection root finder */
    private BisectionRootFinder finder;

    /** Functions whose roots to find */
    private static PlotFunction FUNCTIONS[] = {
        new PlotFunction("x^2 - 4",              -0.25f, 5.25f, -5.5f, 25.25f),
        new PlotFunction("-x^2 + 4x + 5",        -0.5f, 10.25f, -25.5f, 10.25f),
        new PlotFunction("x^3 + 3x^2 - 9x - 10", -6.25f, 4.25f, -20.5f, 20.25f),
        new PlotFunction("x^2 - 2x + 3",         -7.25f, 9.25f, -1.5f, 25.25f),
        new PlotFunction("2x^3 - 10x^2 + 11x - 5",
                                                 -0.5f, 5.25f, -10.5f, 25.25f),
        new PlotFunction("e^-x - x",             -0.5f, 2.25f, -1.75f, 1.75f),
        new PlotFunction("x - e^(1/x)",          -3.25f, 4.25f, -10.25f, 3.25f),
    };

    /** array of plot endpoint columns #1 */    private int cs1[] = new int[3];
    /** array of plot endpoint rows #1 */       private int rs1[] = new int[3];
    /** array of plot endpoint columns #2 */    private int cs2[] = new int[3];
    /** array of plot endpoint rows #2 */       private int rs2[] = new int[3];
    /** line count */                           private int k;

    /** panel height */         private int   h;
    /** x-axis row */           private int   xAxisRow;
    /** minimum x value */      private float xMin;
    /** maximum x value */      private float xMax;
    /** x delta per pixel */    private float xDelta;
    /** y delta per pixel */    private float yDelta;
    /** x-negative value */     private float xNeg;
    /** x-middle value */       private float xMid;
    /** x-positive value */     private float xPos;

    /**
     * Constructor.
     */
    BisectionPanel()
    {
        super(FUNCTIONS, FUNCTION_IMAGE_FILE_NAME, FUNCTION_FRAME_TITLE);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Bisection controls.
        xNegLabel.setFont(labelFont);
        xNegLabel.setAlignment(Label.RIGHT);
        xNegText.setFont(textFont);
        xNegText.setAlignment(Label.LEFT);
        xMidLabel.setFont(labelFont);
        xMidLabel.setAlignment(Label.RIGHT);
        xMidLabel.setForeground(Color.blue);
        xMidText.setFont(textFont);
        xMidText.setAlignment(Label.LEFT);
        xPosLabel.setFont(labelFont);
        xPosLabel.setAlignment(Label.RIGHT);
        xPosText.setFont(textFont);
        xPosText.setAlignment(Label.LEFT);

        // Bisection control panel.
        controlPanel.setLayout(new GridLayout(0, 5, 5, 2));
        controlPanel.add(xNegLabel);
        controlPanel.add(xNegText);
        controlPanel.add(xMidLabel);
        controlPanel.add(xMidText);
        controlPanel.add(runButton);
        controlPanel.add(xPosLabel);
        controlPanel.add(xPosText);
        controlPanel.add(nLabel);
        controlPanel.add(nText);
        controlPanel.add(stepButton);
        addDemoControls(controlPanel);
    }

    /**
     * Draw the bisection lines.
     */
    private void drawBisectionLines()
    {
        k = 0;

        // Convert xNeg, xPos, and xMid to graph columns.
        int colNeg = Math.round((xNeg - xMin)/xDelta);
        int colPos = Math.round((xPos - xMin)/xDelta);
        int colMid = (colNeg + colPos) >> 1;

        // Draw the boundary lines at xNeg and xPos.
        cs1[k] = cs2[k] = colPos;
        rs1[k] = 0;
        rs2[k] = h;
        ++k;

        if (colNeg != colPos) {
            cs1[k] = cs2[k] = colNeg;
            rs1[k] = 0;
            rs2[k] = h;
            ++k;
        }

        // Draw the midpoint line at xMid.
        if ((colNeg != colMid) && (colMid != colPos)) {
            cs1[k] = cs2[k] = colMid;
            rs1[k] = xAxisRow - 20;
            rs2[k] = xAxisRow + 20;
            ++k;
        }

        plotLines(cs1, rs1, cs2, rs2, k, Color.red);
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Draw the contents of the bisection demo panel.
     */
    public void draw()
    {
        super.draw();

        nText.setText("0");
        xNegText.setText(" ");
        xMidText.setText(" ");
        xPosText.setText(" ");

        // Plot properties
        PlotProperties props = getPlotProperties();
        xMin     = props.getXMin();
        xMax     = props.getXMax();
        xDelta   = props.getXDelta();
        yDelta   = props.getYDelta();
        h        = props.getHeight();
        xAxisRow = props.getXAxisRow();

        // Initialize xNeg and xPos.
        xNeg = xMin + xDelta;
        xPos = xMax - xDelta;

        PlotFunction plotFunction = getSelectedPlotFunction();

        // Make sure f(xNeg) < 0 and f(xPos) > 0.
        if (plotFunction.at(xPos) < 0) {
            float temp = xNeg;
            xNeg = xPos;
            xPos = temp;
        }

        // Create the bisection root finder.
        try {
            finder = new BisectionRootFinder(
                            (Function) plotFunction.getFunction(), xNeg, xPos);
        }
        catch(RootFinder.InvalidIntervalException ex) {
            nText.setText("***");
            xMidText.setText("Bad interval");
            runButton.setEnabled(false);
            stepButton.setEnabled(false);
        }
    }

    /**
     * Do one iteration step by choosing the appropriate half of the interval.
     */
    protected void step()
    {
        try {
            if (finder.step()) {
                successfullyConverged();
            }
        }
        catch(RootFinder.IterationCountExceededException ex) {
            iterationLimitExceeded(MAX_ITERS, xMidText);
            return;
        }
        catch(RootFinder.PositionUnchangedException ex) {
            // ignore
        }

        int n = finder.getIterationCount();
        if (n > 1) drawBisectionLines();    // erase previous lines

        xNeg = finder.getXNeg();
        xMid = finder.getXMid();
        xPos = finder.getXPos();

        drawBisectionLines();               // draw new lines

        // Update text controls.
        nText.setText(Integer.toString(n));
        xNegText.setText(Float.toString(xNeg));
        xMidText.setText(Float.toString(xMid));
        xPosText.setText(Float.toString(xPos));
    }
}
