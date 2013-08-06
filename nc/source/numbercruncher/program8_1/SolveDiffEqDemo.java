package numbercruncher.program8_1;

import java.awt.*;
import numbercruncher.graphutils.DemoFrame;

/**
 * PROGRAM 8-1d: Differential Equation Solver (Interactive Standalone Demo)
 *
 * Interactively demonstrate algorithms for solving differential equations.
 */
public class SolveDiffEqDemo extends DemoFrame
{
    private static final String TITLE = "Differential Equation Solver";

    /**
     * Constructor.
     */
    private SolveDiffEqDemo()
    {
        super(TITLE, new SolveDiffEqPanel());
    }

    /**
     * Main.
     */
    public static void main(String args[])
    {
        Frame frame = new SolveDiffEqDemo();
        frame.setVisible(true);
    }
}