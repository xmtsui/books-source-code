package numbercruncher.program16_4;

import java.awt.*;
import numbercruncher.graphutils.*;

/**
 * PROGRAM 16-4: Mandelbrot Set Fractal (Standalone Demo)
 *
 * Graph the Mandelbrot set fractal.  You can zoom into any
 * rectangular region of the graph by using the mouse.
 */
public class MandelbrotSetDemo extends DemoFrame
{
    private static final String TITLE = "Mandelbrot Set Demo";

    /**
     * Constructor.
     */
    private MandelbrotSetDemo()
    {
        super(TITLE, new MandelbrotSetPanel(), 450, 525);
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new MandelbrotSetDemo();
        frame.setVisible(true);
    }
}
