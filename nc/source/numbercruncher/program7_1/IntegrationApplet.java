package numbercruncher.program7_1;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 7-1a: Integration (Interactive Applet)
 *
 * Interactively demonstrate numerical integration algorithms.
 */
public class IntegrationApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public IntegrationApplet()
    {
        super(new IntegrationPanel());
    }
}