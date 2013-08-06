package numbercruncher.mathutils;

/**
 * A polynomial interpolation function.
 */
public class InterpolationPolynomial implements Evaluatable
{
    /** number of data points */      private int       n;
    /** array of data points */       private DataPoint data[];
    /** divided difference table */   private float     dd[][];

    /**
     * Constructor.
     * @param data the array of data points
     */
    public InterpolationPolynomial(DataPoint data[])
    {
        this.data = data;
        this.dd   = new float[data.length][data.length];

        for (int i = 0; i < data.length; ++i) {
            addDataPoint(data[i]);
        }
    }

    /**
     * Constructor.
     * @param maxPoints the maximum number of data points
     */
    public InterpolationPolynomial(int maxPoints)
    {
        this.data = new DataPoint[maxPoints];
        this.dd   = new float[data.length][data.length];
    }

    /**
     * Return the data points.
     * @return the array of data points
     */
    public DataPoint[] getDataPoints() { return data; }

    /**
     * Return the divided difference table.
     * @return the table
     */
    public float[][] getDividedDifferenceTable() { return dd; }

    /**
     * Return the current number of data points.
     * @return the count
     */
    public int getDataPointCount() { return n; }

    /**
     * Add new data point: Augment the divided difference table
     * by appending a new entry at the bottom of each column.
     * @param dataPoint the new data point
     */
    public void addDataPoint(DataPoint dataPoint)
    {
        if (n >= data.length) return;

        data[n]  = dataPoint;
        dd[n][0] = dataPoint.y;

        ++n;

        for (int order = 1; order < n; ++order) {
            int   bottom      = n - order - 1;
            float numerator   = dd[bottom+1][order-1]
                                    - dd[bottom][order-1];
            float denominator = data[bottom + order].x
                                    - data[bottom].x;

            dd[bottom][order] = numerator/denominator;
        }
    }

    /**
     * Return the value of the polynomial
     * interpolation function at x.
     * (Implementation of Evaluatable.)
     * @param x the value of x
     * @return the value of the function at x
     */
    public float at(float x)
    {
        if (n < 2) return Float.NaN;

        float y = dd[0][0];
        float xFactor = 1;

        // Compute the value of the function.
        for (int order = 1; order < n; ++order) {
            xFactor = xFactor*(x - data[order-1].x);
            y = y + xFactor*dd[0][order];
        }

        return y;
    }

    /**
     * Reset.
     */
    public void reset() { n = 0; }
}