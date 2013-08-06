package numbercruncher.program15_5;

import java.awt.*;
import numbercruncher.graphutils.*;

/**
 * PROGRAM 15-5: Prime Patterns
 */
public class PrimePatternsDemo extends DemoFrame
{
    private static final String TITLE = "Prime Patterns Demo";

    // Constructor
    private PrimePatternsDemo()
    {
        super(TITLE, new PrimePatternsPanel(), 525, 590);
    }

    // Main
    public static void main(String args[])
    {
        Frame frame = new PrimePatternsDemo();
        frame.setVisible(true);
    }
}
