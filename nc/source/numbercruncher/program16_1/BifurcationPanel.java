package numbercruncher.program16_1;

import java.awt.*;
import java.awt.event.*;
import numbercruncher.graphutils.*;

public class BifurcationPanel extends GraphPanel
{
    /** control panel */    private Panel fractalControlPanel = new Panel();

    /** x-minimum label */  private Label xMinLabel = new Label("Min x:");
    /** x-maximum label */  private Label xMaxLabel = new Label("Max x:");
    /** y-minimum label */  private Label yMinLabel = new Label("Min y:");
    /** y-maximum label */  private Label yMaxLabel = new Label("Max y:");

    /** x-minimum text */   private Label xMinText  = new Label();
    /** x-maximum text */   private Label xMaxText  = new Label();
    /** y-minimum text */   private Label yMinText  = new Label();
    /** y-maximum text */   private Label yMaxText  = new Label();

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
                                        new PlotProperties(0, 0, 0, 0);

    /**
     * Constructor.
     */
    BifurcationPanel()
    {
        super("Birfucation Diagram of x^2 + c and the Orbits of x=0",
              plotProps, false);

        Font labelFont = getLabelFont();
        Font textFont  = getTextFont();

        // Fractal controls.
        xMinLabel.setFont(labelFont);
        xMinLabel.setAlignment(Label.RIGHT);
        xMinText.setFont(textFont);
        xMaxLabel.setFont(labelFont);
        xMaxLabel.setAlignment(Label.RIGHT);
        xMaxText.setFont(textFont);
        yMinLabel.setFont(labelFont);
        yMinLabel.setAlignment(Label.RIGHT);
        yMinText.setFont(textFont);
        yMaxLabel.setFont(labelFont);
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
     * Plot a function.
     */
    protected void plotFunction()
    {
        // First iteration or not?
        if (n == 0) setProperties();
        else        getProperties();

        displayBounds();

        // Stop the currently-running thread.
        if ((plotThread != null) && (plotThread.isAlive())) {
            stopFlag = true;

            try {
                plotThread.join();
            }
            catch(Exception ex) {}
        }

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

    private static final Color LIGHT_GRAY = new Color(240, 240, 240);

    private static final int SKIP_ITERS =  50;
    private static final int MAX_ITERS  = 200;

    /**
     * Graph thread class that creates a bifurcation diagram of
     * the function f(x) = x^2 + c.  For each value of c, it plots
     * the values of the orbit of x=0.
     */
    private class PlotThread extends Thread
    {
        public void run()
        {
            // Loop over the horizontal axis.
            for (int col = 0; col < w; ++col) {
                float c = xMin + col*delta;
                float x = 0;

                // Light gray backgound;
                for (int row = 0; row < h; ++row) {
                    if (stopFlag) return;
                    plotPoint(col, row, LIGHT_GRAY);
                }

                for (int i = 0; i < MAX_ITERS; ++i) {
                    if (stopFlag) return;
                    x = x*x + c;

                    if (i >= SKIP_ITERS) {
                        float y = x;
                        int row = Math.round((yMax - y)/delta);
                        plotPoint(col, row, Color.blue);
                    }
                }

                // Draw a row of the graph.
                drawPlot();
                yield();
            }
        }
    }
}
