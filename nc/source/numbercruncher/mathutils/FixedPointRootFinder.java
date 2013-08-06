package numbercruncher.mathutils;

/**
 * The root finder class that implements
 * the fixed-point iteration algorithm.
 */
public class FixedPointRootFinder extends RootFinder
{
    private static final int   MAX_ITERS = 50;
    private static final float TOLERANCE = 100*Epsilon.floatValue();

    /** x[n] value */           private float xn = Float.NaN;
    /** previous x[n] value */  private float prevXn;
    /** g(x[n]) */              private float gn;

    /**
     * Constructor.
     * @param function the functions whose roots to find
     */
    public FixedPointRootFinder(Function function)
    {
        super(function, MAX_ITERS);
    }

    /**
     * Reset.
     * @param x0 the initial x-value
     */
    public void reset(float x0)
    {
        super.reset();
        gn = x0;
    }

    //---------//
    // Getters //
    //---------//

    /**
     * Return the current value of x[n].
     * @return the value
     */
    public float getXn() { return xn; }

    /**
     * Return the current value of g(x[n]).
     * @return the value
     */
    public float getGn() { return gn; }

    //-----------------------------//
    // RootFinder method overrides //
    //-----------------------------//

    /**
     * Do the fixed point iteration procedure. (Nothing to do!)
     * @param n the iteration count
     */
    protected void doIterationProcedure(int n) {}

    /**
     * Compute the next position of xn.
     */
    protected void computeNextPosition()
    {
        prevXn = xn;
        xn     = gn;
        gn     = function.at(xn);
    }

    /**
     * Check the position of xn.
     * @throws PositionUnchangedException
     */
    protected void checkPosition()
        throws RootFinder.PositionUnchangedException
    {
        if (xn == prevXn) {
            throw new RootFinder.PositionUnchangedException();
        }
    }

    /**
     * Indicate whether or not the algorithm has converged.
     * @return true if converged, else false
     */
    protected boolean hasConverged()
    {
        return Math.abs((gn - xn)/xn) < TOLERANCE;
    }
}