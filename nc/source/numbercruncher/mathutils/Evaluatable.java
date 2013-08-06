package numbercruncher.mathutils;

/**
 * Interface implement by function classes.
 */
public interface Evaluatable
{
    /**
     * Return the value of the function at x.
     * @param x the value of x
     * @return the value of the function at x
     */
    float at(float x);
}