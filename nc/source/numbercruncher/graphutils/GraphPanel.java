package numbercruncher.graphutils;

import java.awt.*;
import java.awt.event.*;

/**
 * The base panel for all graph demo panels.
 */
public abstract class GraphPanel extends Panel
                                 implements DemoPanel
{
    public static final Color MAROON = new Color(128, 0, 0);

    /** header panel */     private HeaderPanel headerPanel;
    /** plot panel */       private PlotPanel plotPanel = new PlotPanel(this);
    /** control panel */    private Panel controlPanel  = new Panel();
    /** bounds panel */     private PlotBoundsPanel boundsPanel;

    /** true to set XOR mode */         private boolean xorMode;
    /** true to draw axes */            private boolean drawAxes;
    /** true to draw X=Y line */        private boolean drawXequalsY;

    /** plot properties */              private PlotProperties plotProps;
    /** plot bounds */                  private float xMin, xMax, yMin, yMax;

    /** label font */   private Font labelFont = new Font("Dialog", 1, 12);
    /** text font */    private Font textFont  = new Font("Dialog", 0, 12);

    /**
     * Constructor.
     * @param functions the array of plottable functions
     * @param plotProps the plot properties
     * @param xorMode if true, set XOR mode
     * @param drawXequalsY if true, draw the X=Y line
     */
    protected GraphPanel(Plottable functions[], PlotProperties plotProps,
                         boolean xorMode, boolean drawXequalsY)
    {
        this.plotProps    = plotProps;
        this.xorMode      = xorMode;
        this.drawXequalsY = drawXequalsY;
        this.drawAxes     = true;

        headerPanel = new HeaderPanel(functions, this);
        boundsPanel = new PlotBoundsPanel(this);

        init();
    }

    /**
     * Constructor.
     * @param headerText the text to display in the header panel
     * @param plotProps the plot properties
     * @param drawAxes if true, draw axes
     */
    protected GraphPanel(String headerText, PlotProperties plotProps,
                         boolean drawAxes)
    {
        this.plotProps    = plotProps;
        this.xorMode      = false;
        this.drawXequalsY = false;
        this.drawAxes     = drawAxes;

        headerPanel = new HeaderPanel(headerText);
        init();
    }

    /**
     * Constructor.
     * @param headerText the text to display in the header panel
     * @param plotProps the plot properties
     */
    protected GraphPanel(String headerText, PlotProperties plotProps)
    {
        this.plotProps    = plotProps;
        this.xorMode      = false;
        this.drawXequalsY = false;
        this.drawAxes     = true;

        headerPanel = new HeaderPanel(headerText);
        boundsPanel = new PlotBoundsPanel(this);
        init();
    }

    /**
     * Constructor.
     * @param plotProps the plot properties
     */
    protected GraphPanel(PlotProperties plotProps)
    {
        this.plotProps    = plotProps;
        this.xorMode      = false;
        this.drawXequalsY = false;
        this.drawAxes     = false;

        init();
    }

    /**
     * Initialize the graph panel.
     */
    private void init()
    {
        if (boundsPanel != null) boundsPanel.setTextFields(plotProps);

        // Control panel.
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setLayout(new BorderLayout(5, 0));
        if (boundsPanel != null) controlPanel.add(boundsPanel,
                                                  BorderLayout.WEST);

        // Graph panel.
        setLayout(new BorderLayout());
        if (headerPanel != null) add(headerPanel, BorderLayout.NORTH);
        add(plotPanel,    BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Return the label font.
     * @return the font
     */
    protected Font getLabelFont() { return labelFont; }

    /**
     * Return the text font.
     * @return the font
     */
    protected Font getTextFont() { return textFont; }

    /**
     * Return the plot properties.
     * @return the plot properties
     */
    protected PlotProperties getPlotProperties() { return plotProps; }

    /**
     * Set the plot properties.
     * @param plotProps the plot properties
     */
    protected void setPlotProperties(PlotProperties plotProps)
    {
        this.plotProps = plotProps;
        boundsPanel.setTextFields(plotProps);
    }

    /**
     * Set the header image.
     * @param the header image
     */
    protected void setHeaderImage(Image image)
    {
        headerPanel.setImage(image);
    }

    /**
     * Set the header label text in the default black color.
     * @param text the header label text
     */
    protected void setHeaderLabel(String text)
    {
        headerPanel.setLabel(text);
    }

    /**
     * Set the header label text in color.
     * @param text the header label text
     * @param color the color
     */
    protected void setHeaderLabel(String text, Color color)
    {
        headerPanel.setLabel(text, color);
    }

    /**
     * Set the function to plot.
     * @param function the function to set
     */
    protected void setFunction(Plottable function)
    {
        setPlotProperties(function.getPlotProperties());
        headerPanel.setFunction(function);
    }

    /**
     * Set the header to display the function.
     * @param function the function to display
     */
    protected void setHeaderDisplay(Plottable function)
    {
        headerPanel.setFunction(function);
    }

    /**
     * Add a demo's controls to the control panel.
     * @param the demo's control subpanel
     */
    protected void addDemoControls(Panel demoControlPanel)
    {
        controlPanel.add(demoControlPanel, BorderLayout.CENTER);
    }

    /**
     * Process a user input error.
     * @param message the error message
     */
    protected void processUserError(String message)
    {
        headerPanel.displayError(message);
        userErrorOccurred();
    }

    //------------------//
    // Plotting methods //
    //------------------//

    /**
     * Clear the contents of the plot panel.
     */
    public void clear()
    {
        // Get the plot bounds.
        if (boundsPanel != null) {
            boundsPanel.updatePlotProperties(plotProps);
        }

        // Draw the axes.
        plotPanel.initPlot(plotProps);
        if (drawAxes)     plotPanel.drawAxes();
        if (drawXequalsY) plotPanel.drawXequalsY();
    }

    /**
     * Plot a function.
     */
    protected void plotFunction()
    {
        startPlot(Color.blue);

        // Plot properties.
        int   w      = plotProps.getWidth();
        float xMin   = plotProps.getXMin();
        float deltaX = plotProps.getXDelta();

        // Loop over each pixel of the x axis to plot the function value.
        for (int c = 0; c < w; ++c) {
            float x = xMin + c*deltaX;
            float y = valueAt(x);

            plot(c, y);
        }

        endPlot();
    }

    /**
     * Plot the function.
     * @param function the function to plot
     */
    protected void plotFunction(Plottable plotFunction, Color color)
    {
        startPlot(color);

        // Plot properties.
        int   w      = plotProps.getWidth();
        float xMin   = plotProps.getXMin();
        float deltaX = plotProps.getXDelta();

        // Loop over each pixel of the x axis to plot the function value.
        for (int c = 0; c < w; ++c) {
            float x = xMin + c*deltaX;
            float y = plotFunction.at(x);

            plot(c, y);
        }

        endPlot();
    }

    /**
     * Return whether or not it is OK to plot the function.
     * @return true or false
     */
    protected boolean plotOK() { return true; }

    /**
     * Check to make sure the function is in bounds over the interval [a, b].
     * @param a the lower bound
     * @param b the upper bound
     * @throws Exception if it is not
     */
    protected void checkFunctionInBounds(float a, float b) throws Exception
    {
        // Plot properties.
        int   w      = plotProps.getWidth();
        float xMin   = plotProps.getXMin();
        float yMin   = plotProps.getYMin();
        float yMax   = plotProps.getYMax();
        float deltaX = plotProps.getXDelta();

        int ca = Math.round((a - xMin)/deltaX);
        int cb = Math.round((b - xMin)/deltaX);

        // Loop over each pixel of the x axis to check the function value.
        for (int c = ca; c < cb; ++c) {
            float x = xMin + c*deltaX;
            float y = valueAt(x);

            if ((y < yMin) || (y > yMax)) {
                throw new Exception("Function out of bounds over the " +
                                    "interval [" + a + ", " + b + "]");
            }
        }
    }

    /**
     * Return the value of the plottable function at x.  (Return 0 here.)
     * @param x the value of x
     * @return the value of the function
     */
    protected float valueAt(float x) { return 0; }

    /**
     * Start a function plot.
     * @param color the plot color
     */
    protected void startPlot(Color color) { plotPanel.startPlot(color); }

    /**
     * Plot a function point at column c.
     * @param c the column
     * @param y the function value
     */
    protected void plot(int c, float y) { plotPanel.plot(c, y); }

    /**
     * End a function plot.
     */
    protected void endPlot() { plotPanel.endPlot(); }

    /**
     * Plot a point.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param color the point color
     */
    protected void plotPoint(int x, int y, Color color)
    {
        plotPanel.plotPoint(x, y, color);
    }

    /**
     * Plot a line.
     * @param x1 the x-coordinate of one end of the line
     * @param y1 the y-coordinate of one end of the line
     * @param x2 the x-coordinate of the other end of the line
     * @param y2 the y-coordinate of the other end of the line
     * @param color the line color
     */
    protected void plotLine(int x1, int y1, int x2, int y2, Color color)
    {
        plotPanel.plotLine(x1, y1, x2, y2, color);
    }

    /**
     * Plot multiple lines.
     * @param xs1 the array of x-coordinates of one end of the lines
     * @param ys1 the array of y-coordinates of one end of the lines
     * @param xs2 the array of x-coordinates of the other end of the lines
     * @param ys2 the array of y-coordinates of the other end of the lines
     * @param color the color of the lines
     */
    protected void plotLines(int xs1[], int ys1[], int xs2[], int ys2[],
                             int k, Color color)
    {
        plotPanel.plotLines(xs1, ys1, xs2, ys2, k, color);
    }

    /**
     * Plot a rectangle.
     * @param x the x-coordinate of the upper left corner
     * @param y the y-coordinate of the upper left corner
     * @param w the width
     * @param h the height
     * @param color the rectangle color
     */
    protected void plotRectangle(int x, int y, int w, int h, Color color)
    {
        plotPanel.plotRectangle(x, y, w, h, color);
    }

    /**
     * Plot a dot.
     * @param x the x-coordinate of the dot center
     * @param y the y-coordinate of the dot center
     * @param w the dot width
     * @param color the dot color
     */
    protected void plotDot(int x, int y, int w, Color color)
    {
        plotPanel.plotDot(x, y, w, color);
    }

    /**
     * Plot multiple dots.
     * @param data the array of dot coordinates
     * @param k the number of dots
     * @param w the width
     * @param color the dot color
     */
    protected void plotDots(Point data[], int k, int w, Color color)
    {
        plotPanel.plotDots(data, k, w, color);
    }

    protected void fillPolygon(int xs[], int ys[], int k, Color color)
    {
        plotPanel.fillPolygon(xs, ys, k, color);
    }

    /**
     * Draw the plot.
     */
    protected void drawPlot() { plotPanel.drawPlot(); }

    /**
     * @return the plot panel size
     */
    protected Dimension getPlotPanelSize() { return plotPanel.getSize(); }

    /**
     * Set paint mode.
     */
    protected void setPaintMode() { plotPanel.setPainMode(); }

    /**
     * Set XOR mode.
     */
    protected void setXORMode() { plotPanel.setXORMode(); }

    //--------------------------//
    // DemoPanel implementation //
    //--------------------------//

    /**
     * Initialize the demo.  (Callback from applet.  Do nothing here.)
     */
    public void initializeDemo() {}

    /**
     * Close down the demo.  (Callback from applet.  Do nothing here.)
     */
    public void closeDemo() {}

    /**
     * Draw the contents of the plot panel.
     */
    public void draw()
    {
        clear();
        if (plotOK()) plotFunction();

        // Set XOR paint mode if desired.
        if (xorMode) plotPanel.setXORMode();
    }

    /**
     * Notification that the panel was resized.  (Callback from DemoFrame.)
     */
    public void panelResized()
    {
        draw();
    }

    //----------------//
    // Event handlers //
    //----------------//

    /**
     * Do the header action.
     * (Callback from the header panel.  Do nothing here.)
     */
    public void doHeaderAction() {}

    /**
     * Mouse clicked event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mouseClickedOnPlot (MouseEvent ev) {}

    /**
     * Mouse pressed event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mousePressedOnPlot (MouseEvent ev) {}

    /**
     * Mouse released event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mouseReleasedOnPlot(MouseEvent ev) {}

    /**
     * Mouse dragged event handler.
     * (Callback from the plot panel.  Do nothing here.)
     * @param ev the mouse event
     */
    public void mouseDraggedOnPlot(MouseEvent ev) {}

    /**
     * Choose a function.
     * (Callback from the function frame.  Do nothing here.)
     */
    public void chooseFunction(int index) {}

    /**
     * Notification that the plot bounds changed.
     * (Callback from the plot bounds panel.  Do nothing here.)
     */
    public void plotBoundsChanged() {}

    /**
     * Notification that a user input error occurred.  (Do nothing here.)
     */
    protected void userErrorOccurred() {}
}

