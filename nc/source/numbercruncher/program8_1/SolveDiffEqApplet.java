package numbercruncher.program8_1;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 8_1a: Differential Equation Solver (Interactive Applet)
 *
 * Interactively demonstrate algorithms for solving differential equations.
 */
public class SolveDiffEqApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public SolveDiffEqApplet()
    {
        super(new SolveDiffEqPanel());
    }
}