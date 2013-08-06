package numbercruncher.mathutils;

/**
 * The root finder class that implements Newton's algorithm.
 */
public class NewtonsRootFinder extends RootFinder
{
    private static final int   MAX_ITERS = 50;
    private static final float TOLERANCE = 100*Epsilon.floatValue();

    /** x[n] value */             private float xn;
    /** x[n+1] value */           private float xnp1;
    /** previous x[n+1] value */  private float prevXnp1;
    /** f(x[n]) */                private float fn;
    /** f(x[n+1]) */              private float fnp1;
    /** f'(x[n]) */               private float fpn;

    /**
     * Constructor.
     * @param function the functions whose roots to find
     */
    public NewtonsRootFinder(Function function)
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

        xnp1 = x0;
        fnp1 = function.at(xnp1);
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
     * Return the current value of x[n+1].
     * @return the value
     */
    public float getXnp1() { return xnp1; }

    /**
     * Return the current value of f(x[n]).
     * @return the value
     */
    public float getFn() { return fn; }

    /**
     * Return the current value of f(x[n+1]).
     * @return the value
     */
    public float getFnp1() { return fnp1; }

    /**
     * Return the current value of f'(x[n]).
     * @return the value
     */
    public float getFpn() { return fpn; }

    //-----------------------------//
    // RootFinder method overrides //
    //-----------------------------//

    /**
     * Do Newton's iteration procedure.
     * @param n the iteration count
     */
    protected void doIterationProcedure(int n)
    {
        xn = xnp1;
    }

    /**
     * Compute the next position of x[n+1].
     */
    protected void computeNextPosition()
    {
        fn  = fnp1;
        fpn = function.derivativeAt(xn);

        // Compute the value of x[n+1].
        prevXnp1 = xnp1;
        xnp1     = xn - fn/fpn;

        fnp1 = function.at(xnp1);
    }

    /**
     * Check the position of x[n+1].
     * @throws PositionUnchangedException
     */
    protected void checkPosition()
        throws RootFinder.PositionUnchangedException
    {
        if (xnp1 == prevXnp1) {
            throw new RootFinder.PositionUnchangedException();
        }
    }

    /**
     * Indicate whether or not the algorithm has converged.
     * @return true if converged, else false
     */
    protected boolean hasConverged()
    {
        return Math.abs(fnp1) < TOLERANCE;
    }
}