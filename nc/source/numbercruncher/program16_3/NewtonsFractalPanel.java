package numbercruncher.program16_3;

import java.awt.*;
import java.awt.event.*;

import numbercruncher.mathutils.Complex;
import numbercruncher.graphutils.*;

public class NewtonsFractalPanel extends GraphPanel
{
    private static final float INIT_X_MIN = -3.5f;
    private static final float INIT_X_MAX =  3.5f;
    private static final float INIT_Y_MIN = -2;
    private static final float INIT_Y_MAX =  2;

    /** control panel */    private Panel fractalControlPanel = new Panel();
    /** x-minimum label */  private Label xMinLabel           = new Label();
    /** x-minimum text */   private Label xMinText            = new Label();
    /** x-maximum label */  private Label xMaxLabel           = new Label();
    /** x-maximum text */   private Label xMaxText            = new Label();
    /** y-minimum label */  private Label yMinLabel           = new Label();
    /** y-minimum text */   private Label yMinText            = new Label();
    /** y-maximum label */  private Label yMaxLabel           = new Label();
    /** y-maximum text */   private Label yMaxText            = new Label();

    /** iteration counter */    private int        n          = 0;
    /** fractal plot thread */  private PlotThread plotThread = null;

    /** panel width */          private int   w;
    /** panel height */         private int   h;
    /** minimum x value */      private float xMin;
    /** maximum x value */      private float xMax;
    /** minimum y value */      private float yMin;
    /** maximum y value */      private float yMax;
    /** delta per pixel */      private float delta;
    /** previous minimum x */   private float oldXMin;
    /** previous maximun y */   private float oldYMax;

    /** zoom rectangle upper left row */        private int r1;
    /** zoom rectangle upper left column */     private int c1;
    /** zoom rectangle bottom right row */      private int r2;
    /** zoom rectangle bottom right column */   private int c2;

    /** true to stop run thread */  private boolean stopFlag = false;

    /** initial plot properties */
    private static PlotProperties plotProps =
            new PlotProperties(INIT_X_MIN, INIT_X_MAX, INIT_Y_MIN, INIT_Y_MAX);

    /**
     * Constructor.
     */
    NewtonsFractalPanel()
    {
        super(plotProps);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Fractal controls.
        xMinLabel.setFont(labelFont);
        xMinLabel.setAlignment(Label.RIGHT);
        xMinLabel.setText("Min x:");
        xMinText.setFont(textFont);
        xMaxLabel.setFont(labelFont);
        xMaxLabel.setText("Max x:");
        xMaxLabel.setAlignment(Label.RIGHT);
        xMaxText.setFont(textFont);
        yMinLabel.setFont(labelFont);
        yMinLabel.setAlignment(Label.RIGHT);
        yMinLabel.setText("Min y:");
        yMinText.setFont(textFont);
        yMaxLabel.setFont(labelFont);
        yMaxLabel.setText("Max y:");
        yMaxLabel.setAlignment(Label.RIGHT);
        yMaxText.setFont(textFont);

        // Fractal control panel.
        fractalControlPanel.setBackground(Color.lightGray);
        fractalControlPanel.setLayout(new GridLayout(0, 4, 5, 2));
        fractalControlPanel.add(xMinLabel);
        fractalControlPanel.add(xMinText);
        fractalControlPanel.add(yMinLabel);
        fractalControlPanel.add(yMinText);
        fractalControlPanel.add(xMaxLabel);
        fractalControlPanel.add(xMaxText);
        fractalControlPanel.add(yMaxLabel);
        fractalControlPanel.add(yMaxText);
        addDemoControls(fractalControlPanel);
    }

    /**
     * Get the plot properties.
     */
    private void getProperties()
    {
        w     = plotProps.getWidth();
        h     = plotProps.getHeight();
        delta = plotProps.getXDelta();
        xMin  = plotProps.getXMin();
        xMax  = plotProps.getXMax();
        yMin  = yMax - h*delta;
        yMax  = plotProps.getYMax();
    }

    /**
     * Set the plot properties.
     */
    private void setProperties()
    {
        w     = plotProps.getWidth();
        h     = plotProps.getHeight();
        delta = 0.01f;
        xMin  = -(w/2)*delta;
        yMin  = -(h/2)*delta;
        xMax  = -xMin;
        yMax  = -yMin;

        plotProps.update(xMin, xMax, yMin, yMax);
    }

    /**
     * Display the current plot bounds in the text controls.
     */
    private void displayBounds()
    {
        xMinText.setText(Float.toString(xMin));
        xMaxText.setText(Float.toString(xMax));
        yMinText.setText(Float.toString(yMin));
        yMaxText.setText(Float.toString(yMax));
    }

    //------------------//
    // Method overrides //
    //------------------//

    /**
     * Draw the fractal.
     */
    protected void plotFunction()
    {
        // Stop the currently-running thread.
        if ((plotThread != null) && (plotThread.isAlive())) {
            stopFlag = true;
            try {
                plotThread.join();
            }
            catch(InterruptedException ignored) {}
        }

        // First iteration or not?
        if (n == 0) {
            setProperties();
        }
        else {
            getProperties();
        }
        displayBounds();

        // Start a new plot thread.
        stopFlag = false;
        plotThread = new PlotThread();
        plotThread.start();

        ++n;
    }

    /**
     * Mouse pressed on the plot:  Start the zoom rectangle.
     */
    public void mousePressedOnPlot(MouseEvent ev)
    {
        if ((plotThread != null) && (plotThread.isAlive())) return;

        oldXMin = xMin;
        oldYMax = yMax;

        // Starting corner.
        c1 = ev.getX();
        r1 = ev.getY();

        // Ending corner.
        c2 = -1;
        r2 = -1;

        setXORMode();
    }

    /**
     * Mouse dragged on the plot:  Track the mouse to draw the zoom rectangle.
     */
    public void mouseDraggedOnPlot(MouseEvent ev)
    {
        if ((plotThread != null) && (plotThread.isAlive())) return;

        // Erase the previous rectangle.
        if ((c2 != -1) && (r2 != -1)) {
            plotRectangle(Math.min(c1, c2),  Math.min(r1, r2),
                          Math.abs(c1 - c2), Math.abs(r1 - r2),
                          Color.black);
        }

        // Current ending corner.
        c2 = ev.getX();
        r2 = ev.getY();

        // Calculate and display new zoom area bounds.
        xMin = oldXMin + delta*Math.min(c1, c2);
        xMax = oldXMin + delta*Math.max(c1, c2);
        yMin = oldYMax - delta*Math.max(r1, r2);
        yMax = oldYMax - delta*Math.min(r1, r2);
        displayBounds();

        // Draw the new rectangle.
        plotRectangle(Math.min(c1, c2),  Math.min(r1, r2),
                      Math.abs(c1 - c2), Math.abs(r1 - r2),
                      Color.black);
    }

    /**
     * Mouse released on the plot:  End the zoom rectangle and
     * plot the zoomed area.
     */
    public void mouseReleasedOnPlot(MouseEvent ev)
    {
        if ((plotThread != null) && (plotThread.isAlive())) return;

        // Draw the rectangle.
        if ((c2 != -1) && (r2 != -1)) {
            plotRectangle(Math.min(c1, c2),  Math.min(r1, r2),
                          Math.abs(c1 - c2), Math.abs(r1 - r2),
                          Color.black);
        }

        // Plot the area in the rectangle.
        plotProps.update(xMin, xMax, yMin, yMax);
        draw();
    }

    //-----------//
    // Animation //
    //-----------//

    private static final int     MAX_ITERS = 100;
    private static final Complex ONE       = new Complex(1, 0);
    private static final Complex THREE     = new Complex(3, 0);

    /**
     * Graph thread class that applies Newton's Method to z^3 - 1
     * starting at each point in the complex plane.
     */
    private class PlotThread extends Thread
    {
        public void run()
        {
            // Loop over each graph panel pixel.
            for (int r = 0; r < h; ++r) {
                for (int c = 0; c < w; ++c) {

                    if (stopFlag) return;

                    // Convert the pixel coordinates to the
                    // initial (x, y) in the complex plane.
                    float x = xMin + c*delta;
                    float y = yMax - r*delta;

                    Complex z = new Complex(x, y);
                    Complex zOld;

                    // Loop until z converges.
                    // Keep track of the number of iterations.
                    int iters = 0;
                    do {
                        Complex zSquared = z.multiply(z);
                        Complex zCubed   = zSquared.multiply(z);

                        zOld = z;
                        z = z.subtract(zCubed.subtract(ONE)
                                .divide(THREE.multiply(zSquared)));
                    } while ((++iters < MAX_ITERS)
                                && (!z.equal(zOld)));

                    // Set the color intensity based on
                    // the number of iterations.
                    int k = 20*(iters%10);

                    // Set red, green, or blue according to
                    // which root z converged to.
                    if (z.real() > 0) {
                        // 1
                        plotPoint(c, r, new Color(k, k, 255));
                    }
                    else if (z.imaginary() > 0) {
                        // -0.5+0.8660254i
                        plotPoint(c, r, new Color(k, 255, k));
                    }
                    else {
                        // -0.5-0.8660254i
                        plotPoint(c, r, new Color(255, k, k));
                    }
                }

                // Draw a row of the graph.
                drawPlot();
                yield();
            }
        }
    }

    public static void main(String args[])
    {
        Complex one   = new Complex(1, 0);
        Complex three = new Complex(3, 0);
        Complex z     = new Complex(-1, -1);
        Complex zOld;

        do {
            Complex zSquared = z.multiply(z);
            Complex zCubed   = zSquared.multiply(z);

            zOld = z;
            System.out.println(z.toString());
            z = z.subtract(zCubed.subtract(one)
                                .divide(three.multiply(zSquared)));
        } while (!z.equal(zOld));

        System.out.println(z.toString());
    }
}
