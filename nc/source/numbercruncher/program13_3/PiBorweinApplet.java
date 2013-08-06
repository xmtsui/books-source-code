package numbercruncher.program13_3;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 13-3a: The Borwein Pi Algorithm (Interactive Applet)
 *
 * Compute digits of pi by the Borwein algorithm.
 */
public class PiBorweinApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public PiBorweinApplet()
    {
        super(new PiBorweinPanel());
    }
}