package numbercruncher.mathutils;

/**
 * A least-squares regression line function.
 */
public class RegressionLine implements Evaluatable
{
    /** sum of x */     private double sumX;
    /** sum of y */     private double sumY;
    /** sum of x*x */   private double sumXX;
    /** sum of x*y */   private double sumXY;

    /** line coefficient a0 */  private float a0;
    /** line coefficient a1 */  private float a1;

    /** number of data points */        private int     n;
    /** true if coefficients valid */   private boolean coefsValid;

    /**
     * Constructor.
     */
    public RegressionLine() {}

    /**
     * Constructor.
     * @param data the array of data points
     */
    public RegressionLine(DataPoint data[])
    {
        for (int i = 0; i < data.length; ++i) {
            addDataPoint(data[i]);
        }
    }

    /**
     * Return the current number of data points.
     * @return the count
     */
    public int getDataPointCount() { return n; }

    /**
     * Return the coefficient a0.
     * @return the value of a0
     */
    public float getA0()
    {
        validateCoefficients();
        return a0;
    }

    /**
     * Return the coefficient a1.
     * @return the value of a1
     */
    public float getA1()
    {
        validateCoefficients();
        return a1;
    }

    /**
     * Return the sum of the x values.
     * @return the sum
     */
    public double getSumX() { return sumX; }

    /**
     * Return the sum of the y values.
     * @return the sum
     */
    public double getSumY() { return sumY; }

    /**
     * Return the sum of the x*x values.
     * @return the sum
     */
    public double getSumXX() { return sumXX; }

    /**
     * Return the sum of the x*y values.
     * @return the sum
     */
    public double getSumXY() { return sumXY; }

    /**
     * Add a new data point: Update the sums.
     * @param dataPoint the new data point
     */
    public void addDataPoint(DataPoint dataPoint)
    {
        sumX  += dataPoint.x;
        sumY  += dataPoint.y;
        sumXX += dataPoint.x*dataPoint.x;
        sumXY += dataPoint.x*dataPoint.y;

        ++n;
        coefsValid = false;
    }

    /**
     * Return the value of the regression line function at x.
     * (Implementation of Evaluatable.)
     * @param x the value of x
     * @return the value of the function at x
     */
    public float at(float x)
    {
        if (n < 2) return Float.NaN;

        validateCoefficients();
        return a0 + a1*x;
    }

    /**
     * Reset.
     */
    public void reset()
    {
        n = 0;
        sumX = sumY = sumXX = sumXY = 0;
        coefsValid = false;
    }

    /**
     * Validate the coefficients.
     */
    private void validateCoefficients()
    {
        if (coefsValid) return;

        if (n >= 2) {
            float xBar = (float) sumX/n;
            float yBar = (float) sumY/n;

            a1 = (float) ((n*sumXY - sumX*sumY)
                            /(n*sumXX - sumX*sumX));
            a0 = (float) (yBar - a1*xBar);
        }
        else {
            a0 = a1 = Float.NaN;
        }

        coefsValid = true;
    }
}