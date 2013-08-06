package numbercruncher.program16_1;

import java.awt.*;
import numbercruncher.graphutils.*;

/**
 * PROGRAM 16-1: Newton's Fractal
 *
 * Graph the application of Newton's Method on the function x^3 - 1,
 * which has three roots in the complex plane: 1, -0.5+0.87i, and
 * -0.5-0.87i.  Plot each point in the plane as follows:
 *
 * (1) Apply Newton's Method using the point as the starting point.
 * (2) Set the point's color (red, green, or blue) according to which
 *     root the method converges to from the point.
 * (3) Set the color intensity according to the number of iterations.
 *
 * The resulting graph is a Julia set fractal.  You can zoom into any
 * rectangular region of the graph by using the mouse.
 */
public class BifurcationDemo extends DemoFrame
{
    private static final String TITLE = "Julia Set Demo";

    /**
     * Constructor.
     */
    private BifurcationDemo()
    {
        super(TITLE, new BifurcationPanel(), 500, 572);
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new BifurcationDemo();
        frame.setVisible(true);
    }
}
