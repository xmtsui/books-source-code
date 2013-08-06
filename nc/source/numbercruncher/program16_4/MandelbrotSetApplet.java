package numbercruncher.program16_4;

import java.awt.*;
import numbercruncher.graphutils.*;

/**
 * PROGRAM 16-4: Mandelbrot Set Fractal (Applet)
 *
 * Graph the Mandelbrot set fractal.  You can zoom into any
 * rectangular region of the graph by using the mouse.
 */
public class MandelbrotSetApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public MandelbrotSetApplet()
    {
        super(new MandelbrotSetPanel());
    }
}
