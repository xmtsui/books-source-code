package numbercruncher.mathutils;

/**
 * The root finder class that implements the bisection algorithm.
 */
public class BisectionRootFinder extends RootFinder
{
    private static final int   MAX_ITERS = 50;
    private static final float TOLERANCE = 100*Epsilon.floatValue();

    /** x-negative value */         private float xNeg;
    /** x-middle value */           private float xMid = Float.NaN;
    /** x-positive value */         private float xPos;
    /** previous x-middle value */  private float prevXMid;
    /** f(xNeg) */                  private float fNeg;
    /** f(xMid) */                  private float fMid;
    /** f(xPos) */                  private float fPos;

    /**
     * Constructor.
     * @param function the functions whose roots to find
     * @param xMin the initial x-value where the function is negative
     * @param xMax the initial x-value where the function is positive
     * @throws RootFinder.InvalidIntervalException
     */
    public BisectionRootFinder(Function function,
                               float xMin, float xMax)
        throws RootFinder.InvalidIntervalException
    {
        super(function, MAX_ITERS);
        checkInterval(xMin, xMax);

        float yMin = function.at(xMin);
        float yMax = function.at(xMax);

        // Initialize xNeg, fNeg, xPos, and fPos.
        if (yMin < 0) {
            xNeg = xMin; xPos = xMax;
            fNeg = yMin; fPos = yMax;
        }
        else {
            xNeg = xMax; xPos = xMin;
            fNeg = yMax; fPos = yMin;
        }
    }

    //---------//
    // Getters //
    //---------//

    /**
     * Return the current value of x-negative.
     * @return the value
     */
    public float getXNeg() { return xNeg; }

    /**
     * Return the current value of x-middle.
     * @return the value
     */
    public float getXMid() { return xMid; }

    /**
     * Return the current value of x-positive.
     * @return the value
     */
    public float getXPos() { return xPos; }

    /**
     * Return the current value of f(x-negative).
     * @return the value
     */
    public float getFNeg() { return fNeg; }

    /**
     * Return the current value of f(x-middle).
     * @return the value
     */
    public float getFMid() { return fMid; }

    /**
     * Return the current value of f(x-positive).
     * @return the value
     */
    public float getFPos() { return fPos; }

    //-----------------------------//
    // RootFinder method overrides //
    //-----------------------------//

    /**
     * Do the bisection iteration procedure.
     * @param n the iteration count
     */
    protected void doIterationProcedure(int n)
    {
        if (n == 1) return;     // already initialized

        if (fMid < 0) {
            xNeg = xMid;        // the root is in the xPos half
            fNeg = fMid;
        }
        else {
            xPos = xMid;        // the root is in the xNeg half
            fPos = fMid;
        }
    }

    /**
     * Compute the next position of xMid.
     */
    protected void computeNextPosition()
    {
        prevXMid = xMid;
        xMid     = (xNeg + xPos)/2;
        fMid     = function.at(xMid);
    }

    /**
     * Check the position of xMid.
     * @throws PositionUnchangedException
     */
    protected void checkPosition()
        throws RootFinder.PositionUnchangedException
    {
        if (xMid == prevXMid) {
            throw new RootFinder.PositionUnchangedException();
        }
    }

    /**
     * Indicate whether or not the algorithm has converged.
     * @return true if converged, else false
     */
    protected boolean hasConverged()
    {
        return Math.abs(fMid) < TOLERANCE;
    }
}