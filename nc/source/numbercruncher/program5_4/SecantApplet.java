package numbercruncher.program5_4;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 5-4a: Secant Algorithm (Interactive Applet)
 *
 * Interactively demonstrate the Secant Algorithm on various functions.
 */
public class SecantApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public SecantApplet()
    {
        super(new SecantPanel());
    }
}