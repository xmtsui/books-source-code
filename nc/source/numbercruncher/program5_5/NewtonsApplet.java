package numbercruncher.program5_5;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 5-5a: Newton's Algorithm (Interactive Applet)
 *
 * Interactively demonstrate Newton's Algorithm on various functions.
 */
public class NewtonsApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public NewtonsApplet()
    {
        super(new NewtonsPanel());
    }
}