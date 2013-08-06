package numbercruncher.program5_2;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 5-2a: Regula Falsi Algorithm (Interactive Applet)
 *
 * Interactively demonstrate the Regula Falsi Algorithm on various
 * functions.  Either single-step or let the program automatically
 * step once each half second.
 */
public class RegulaFalsiApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public RegulaFalsiApplet()
    {
        super(new RegulaFalsiPanel());
    }
}