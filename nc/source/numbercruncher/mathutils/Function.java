package numbercruncher.mathutils;

/**
 * The base class for functions that can have derivatives.
 */
public abstract class Function implements Evaluatable
{
    /**
     * Return the value of the function at x.
     * (Implementation of Evaluatable.)
     * @param x the value of x
     * @return the function value
     */
    public abstract float at(float x);

    /**
     * Return the value of the function's derivative at x.
     * @param x the value of x
     * @return the derivative value
     */
    public float derivativeAt(float x) { return 0; }
}