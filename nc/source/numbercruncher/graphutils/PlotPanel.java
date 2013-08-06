package numbercruncher.graphutils;

import java.awt.*;
import java.awt.event.*;

/**
 * The panel that draws a set of axes, and plots points and lines.
 */
class PlotPanel extends Panel
{
    private static final int TICK_SIZE = 5;

    /** image buffer */             private Image       buffer;
    /** buffer graphics context */  private Graphics    bg;
    /** font metrics */             private FontMetrics fontMetrics;

    /** label font */   private Font labelFont = new Font("Dialog", 1, 10);

    /** width */                private int   w;
    /** height */               private int   h;
    /** x-axis row */           private int   xAxisRow;
    /** y-axis column */        private int   yAxisCol;
    /** minimum x value */      private float xMin;
    /** maximum x value */      private float xMax;
    /** minimum y value */      private float yMin;
    /** maximum y value */      private float yMax;
    /** x delta per pixel */    private float xDelta;
    /** y delta per pixel */    private float yDelta;

    /** array of plot endpoint columns */   private int cs[];
    /** array of plot endpoint rows */      private int rs[];
    /** endpoint array index */             private int k;

    /** parent graph panel */   private GraphPanel graphPanel;

    /**
     * Constructor.
     * @param graphPanel the parent graph panel
     */
    PlotPanel(GraphPanel graphPanel)
    {
        this.graphPanel = graphPanel;
        setBackground(Color.white);

        // Plot mouse event handlers.
        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent ev)
            {
                PlotPanel.this.graphPanel.mouseClickedOnPlot(ev);   // callback
            }

            public void mousePressed(MouseEvent ev)
            {
                PlotPanel.this.graphPanel.mousePressedOnPlot(ev);   // callback
            }

            public void mouseReleased(MouseEvent ev)
            {
                PlotPanel.this.graphPanel.mouseReleasedOnPlot(ev);  // callback
            }
        });
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent ev)
            {
                PlotPanel.this.graphPanel.mouseDraggedOnPlot(ev);   // callback
            }
        });
    }

    /**
     * Initialize the plot with its properties.
     * @param the plot properties
     */
    void initPlot(PlotProperties plotProps)
    {
        if (plotProps == null) return;

        // Compute the plot properties.
        Dimension size = getSize();
        plotProps.compute(size);

        // Extract the plot properties.
        w        = plotProps.getWidth();
        h        = plotProps.getHeight();
        xAxisRow = plotProps.getXAxisRow();
        yAxisCol = plotProps.getYAxisColumn();
        xMin     = plotProps.getXMin();
        xMax     = plotProps.getXMax();
        yMin     = plotProps.getYMin();
        yMax     = plotProps.getYMax();
        xDelta   = plotProps.getXDelta();
        yDelta   = plotProps.getYDelta();

        // Create the image buffer and get its graphics context.
        buffer = createImage(w, h);
        bg     = buffer.getGraphics();

        bg.setFont(labelFont);
        fontMetrics = bg.getFontMetrics();
    }

    /**
     * Set paint mode.
     */
    void setPainMode() { bg.setPaintMode(); }

    /**
     * Set XOR mode.
     */
    void setXORMode() { bg.setXORMode(Color.white); }

    /**
     * Draw the axes onto the image buffer.
     */
    void drawAxes()
    {
        if (bg == null) return;

        bg.setPaintMode();
        bg.setColor(Color.black);

        // X axis.
        if ((xAxisRow >= 0) && (xAxisRow < h)) {
            bg.drawLine(0, xAxisRow, w, xAxisRow);
        }

        // X axis ticks.
        for (int i = Math.round(xMin); i <= Math.round(xMax); ++i) {
            int c = Math.round((i - xMin)/xDelta);
            bg.drawLine(c, xAxisRow - TICK_SIZE, c, xAxisRow + TICK_SIZE);

            if (i != 0) {
                String str = Integer.toString(i);
                int    w   = fontMetrics.stringWidth(str);
                int    x   = c - w/2;
                int    y   = xAxisRow + TICK_SIZE + fontMetrics.getAscent();
                bg.drawString(str, x, y);
            }
        }

        // Y axis.
        if ((yAxisCol >= 0) && (yAxisCol < w)) {
            bg.drawLine(yAxisCol, 0, yAxisCol, h);
        }

        // Y axis ticks.
        for (int i = Math.round(yMin); i <= Math.round(yMax); ++i) {
            int r = Math.round((yMax - i)/yDelta);
            bg.drawLine(yAxisCol - TICK_SIZE, r, yAxisCol + TICK_SIZE, r);

            if (i != 0) {
                String str = Integer.toString(i);
                int    w   = fontMetrics.stringWidth(str);
                int    x   = yAxisCol - TICK_SIZE - w;
                int    y   = r + fontMetrics.getAscent()/2;
                bg.drawString(str, x, y);
            }
        }

        repaint();
    }

    /**
     * Draw the X=Y line.
     */
    void drawXequalsY()
    {
        if (bg == null) return;

        bg.setPaintMode();
        bg.setColor(Color.black);

        float p1 = Math.max(xMin, yMin);
        int   c1 = Math.round((p1 - xMin)/xDelta);
        int   r1 = Math.round((yMax - p1)/yDelta);
        float p2 = Math.min(xMax, yMax);
        int   c2 = Math.round((p2 - xMin)/xDelta);
        int   r2 = Math.round((yMax - p2)/yDelta);

        bg.drawLine(c1, r1, c2, r2);
        repaint();
    }

    /**
     * Start a function plot.
     * @param color the plot color
     */
    void startPlot(Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        cs = new int[w];
        rs = new int[w];
        k  = 0;
    }

    /**
     * Plot a function point at column c.
     * @param c the column
     * @param y the function value
     */
    void plot(int c, float y)
    {
        if (bg == null) return;

        // Plot y if it's within range.
        if ((y >= yMin) && (y <= yMax)) {
            int r = Math.round((yMax - y)/yDelta);
            cs[k] = c;
            rs[k] = r;
            ++k;
        }

        // Otherwise draw what we have so far.
        else if (k > 0) {
            bg.drawPolyline(cs, rs, k);
            k = 0;
        }
    }

    /**
     * End a function plot.
     */
    void endPlot()
    {
        if (bg == null) return;

        // Draw the rest of the plot.
        if (k > 0) bg.drawPolyline(cs, rs, k);
        drawPlot();
    }

    /**
     * Plot a point.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param color the point color
     */
    void plotPoint(int x, int y, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        bg.drawLine(x, y, x, y);
    }

    /**
     * Plot a line.
     * @param x1 the x-coordinate of one end of the line
     * @param y1 the y-coordinate of one end of the line
     * @param x2 the x-coordinate of the other end of the line
     * @param y2 the y-coordinate of the other end of the line
     * @param color the line color
     */
    void plotLine(int x1, int y1, int x2, int y2, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        bg.drawLine(x1, y1, x2, y2);
        drawPlot();
    }

    /**
     * Plot multiple lines.
     * @param xs1 the array of x-coordinates of one end of the lines
     * @param ys1 the array of y-coordinates of one end of the lines
     * @param xs2 the array of x-coordinates of the other end of the lines
     * @param ys2 the array of y-coordinates of the other end of the lines
     * @param color the color of the lines
     */
    void plotLines(int xs1[], int ys1[], int xs2[], int ys2[],
                   int k, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        for (int i = 0; i < k; ++i) {
            bg.drawLine(xs1[i], ys1[i], xs2[i], ys2[i]);
        }

        drawPlot();
    }

    /**
     * Plot a rectangle.
     * @param x the x-coordinate of the upper left corner
     * @param y the y-coordinate of the upper left corner
     * @param w the width
     * @param h the height
     * @param color the rectangle color
     */
    void plotRectangle(int x, int y, int w, int h, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        bg.drawRect(x, y, w, h);
        drawPlot();
    }

    void plotDot(int x, int y, int w, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        int halfW = w/2;
        bg.fillOval(x - halfW, y - halfW, w, w);

        drawPlot();
    }

    void plotDots(Point data[], int k, int w, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        int halfW = w/2;
        for (int i = 0; i < k; ++i) {
            bg.fillOval(data[i].x - halfW, data[i].y - halfW, w, w);
        }

        drawPlot();
    }

    protected void fillPolygon(int xs[], int ys[], int k, Color color)
    {
        if (bg == null) return;
        bg.setColor(color);

        bg.fillPolygon(xs, ys, k);
        drawPlot();
    }

    /**
     * Display the image buffer.
     */
    void drawPlot()
    {
        if (buffer == null) return;

        Graphics g = this.getGraphics();
        if (g != null) g.drawImage(buffer, 0, 0, null);
    }

    /**
     * Update the display without repainting the background.
     * @param g the graphics context
     */
    public void update(Graphics g)
    {
        paint(g);
    }

    /**
     * Display the image buffer.
     * @param g the graphics context
     */
    public void paint(Graphics g)
    {
        drawPlot();
    }
}
