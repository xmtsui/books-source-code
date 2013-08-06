package numbercruncher.program14_1;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 14-1a: Normally-Distributed Random Numbers (Interactive Applet)
 *
 * Demonstrate algorithms for generating normally-distributed
 * random numbers.
 */
public class GenerateRandomNormalApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public GenerateRandomNormalApplet()
    {
        super(new RandomNormalPanel());
    }
}