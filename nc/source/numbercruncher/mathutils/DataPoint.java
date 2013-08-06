package numbercruncher.mathutils;

/**
 * A data point for interpolation and regression.
 */
public class DataPoint
{
    /** the x value */  public float x;
    /** the y value */  public float y;

    /**
     * Constructor.
     * @param x the x value
     * @param y the y value
     */
    public DataPoint(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
}