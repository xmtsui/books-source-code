package numbercruncher.program6_1;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 6-1a: Polynomial Interpolation (Interactive Applet)
 *
 * Interactively demonstrate polynomial interpolation.
 */
public class InterpolationApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public InterpolationApplet()
    {
        super(new InterpolationPanel());
    }
}