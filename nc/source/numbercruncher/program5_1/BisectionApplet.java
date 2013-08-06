package numbercruncher.program5_1;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 5-1a: Bisection Algorithm (Interactive Applet)
 *
 * Interactively demonstrate the Bisection Algorithm on various functions.
 * Either single-step or let the program automatically step once each
 * half second.
 */
public class BisectionApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public BisectionApplet()
    {
        super(new BisectionPanel());
    }
}