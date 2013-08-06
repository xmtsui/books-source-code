package numbercruncher.program14_2;

import numbercruncher.graphutils.DemoApplet;

/**
 * PROGRAM 14-2a: Exponentially-Distributed Random Numbers
 *                (Interactive Applet)
 *
 * Demonstrate algorithms for generating exponentially-distributed
 * random numbers.
 */
public class GenerateRandomExponentialApplet extends DemoApplet
{
    /**
     * Constructor.
     */
    public GenerateRandomExponentialApplet()
    {
        super(new RandomExponentialPanel());
    }
}