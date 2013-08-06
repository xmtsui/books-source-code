package numbercruncher.mathutils;

/**
 * Abstract base class for the root finder classes.
 */
public abstract class RootFinder
{
    /** the function whose roots to find */ protected Function function;
    /** iteration counter */                private   int      n;
    /** maximum number of iterations */     private   int      maxIters;

    /**
     * Constructor.
     * @param function the function whose roots to find
     * @param maxIters the maximum number of iterations
     */
    public RootFinder(Function function, int maxIters)
    {
        this.function = function;
        this.maxIters = maxIters;
    }

    /**
     * Check the interval.
     * @param xMin x-coordinate of the left of the interval
     * @param xMax x-coordinate of the right end of the interval
     * @throws InvalidIntervalException
     */
    public void checkInterval(float x1, float x2)
        throws InvalidIntervalException
    {
        float y1 = function.at(x1);
        float y2 = function.at(x2);

        // The interval is invalid if y1 and y2 have the same signs.
        if (y1*y2 > 0) throw new InvalidIntervalException();
    }

    /**
     * Return the iteration count.
     * @return the count
     */
    public int getIterationCount() { return n; }

    /**
     * Perform one iteration step.
     * @return true if the algorithm converged, else false
     * @throws IterationCountExceededException
     * @throws PositionUnchangedException
     */
    public boolean step() throws IterationCountExceededException,
                                 PositionUnchangedException
    {
        checkIterationCount();
        doIterationProcedure(n);

        computeNextPosition();
        checkPosition();

        return hasConverged();
    }

    /**
     * Check the iteration count to see if it has exeeded
     * the maximum number of iterations.
     * @throws IterationCountExceededException
     */
    protected void checkIterationCount()
        throws IterationCountExceededException
    {
        if (++n > maxIters) {
            throw new IterationCountExceededException();
        }
    }

    /**
     * Reset.
     */
    protected void reset() { n = 0; }

    //------------------//
    // Subclass methods //
    //------------------//

    /**
     * Do the iteration procedure.
     * @param n the iteration count
     */
    protected abstract void doIterationProcedure(int n);

    /**
     * Compute the next position of x.
     */
    protected abstract void computeNextPosition();

    /**
     * Check the position of x.
     * @throws PositionUnchangedException
     */
    protected abstract void checkPosition()
        throws PositionUnchangedException;

    /**
     * Indicate whether or not the algorithm has converged.
     * @return true if converged, else false
     */
    protected abstract boolean hasConverged();

    //------------------------//
    // Root finder exceptions //
    //------------------------//

    /**
     * Invalid interval exception.
     */
    public class InvalidIntervalException
        extends java.lang.Exception {}

    /**
     * Iteration count exceeded exception.
     */
    public class IterationCountExceededException
        extends java.lang.Exception {}

    /**
     * Position unchanged exception.
     */
    public class PositionUnchangedException
        extends java.lang.Exception {}
}