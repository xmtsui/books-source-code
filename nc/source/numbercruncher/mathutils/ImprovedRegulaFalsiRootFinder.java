package numbercruncher.mathutils;

/**
 * The root finder class that implements the
 * improved regula falsi algorithm.
 */
public class ImprovedRegulaFalsiRootFinder
    extends RegulaFalsiRootFinder
{
    /** previous f(xFalse) value */  private float prevFFalse;

    private boolean decreasePos = false;
    private boolean decreaseNeg = false;

    /**
     * Constructor.
     * @param function the functions whose roots to find
     * @param xMin the initial x-value where the function is negative
     * @param xMax the initial x-value where the function is positive
     * @throws RootFinder.InvalidIntervalException
     */
    public ImprovedRegulaFalsiRootFinder(Function function,
                                         float xMin, float xMax)
        throws RootFinder.InvalidIntervalException
    {
        super(function, xMin, xMax);
    }

    //----------------------------------------//
    // Override RegulaFalsiRootFinder methods //
    //----------------------------------------//

    /**
     * Do the improved regula falsi iteration procedure.
     * @param n the iteration count
     */
    protected void doIterationProcedure(int n)
    {
        super.doIterationProcedure(n);

        // Decrease the slope of the secant?
        if (decreasePos) fPos /= 2;
        if (decreaseNeg) fNeg /= 2;
    }

    /**
     * Compute the next position of xFalse.
     */
    protected void computeNextPosition()
    {
        prevXFalse = xFalse;
        prevFFalse = fFalse;
        xFalse     = xPos - fPos*(xNeg - xPos)/(fNeg - fPos);
        fFalse     = function.at(xFalse);

        decreasePos = decreaseNeg = false;

        // If there was no sign change in f(xFalse),
        // or if this is the first iteration step,
        // then decrease the slope of the secant.
        if (Float.isNaN(prevFFalse) || (prevFFalse*fFalse > 0)) {
            if (fFalse < 0) decreasePos = true;
            else            decreaseNeg = true;
        }
    }
}