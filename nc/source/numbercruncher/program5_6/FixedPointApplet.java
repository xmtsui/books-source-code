package numbercruncher.program5_6;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 5-6a: Fixed-Point Iteration (Interactive Applet)
 *
 * Interactively demonstrate Fixed-Point Iteration Algorithm
 * on various functions.
 */
public class FixedPointApplet extends DemoApplet
{
    // Constructor
    public FixedPointApplet()
    {
        super(new FixedPointPanel());
    }
}