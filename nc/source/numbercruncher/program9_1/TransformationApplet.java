package numbercruncher.program9_1;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 9-1a: Graphic Transformations (Interactive Applet)
 *
 * Interactively demonstrate the use of graphic transformation matrices.
 */
public class TransformationApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public TransformationApplet()
    {
        super(new TransformationPanel());
    }
}