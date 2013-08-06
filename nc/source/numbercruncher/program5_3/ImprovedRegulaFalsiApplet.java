package numbercruncher.program5_3;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 5-3a: Improved Regula Falsi Algorithm (Interactive Applet)
 *
 * Interactively demonstrate the improved Regula Falsi Algorithm
 * on various functions.  Either single-step or let the program
 * automatically step once each half second.
 */
public class ImprovedRegulaFalsiApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public ImprovedRegulaFalsiApplet()
    {
        super(new ImprovedRegulaFalsiPanel());
    }
}