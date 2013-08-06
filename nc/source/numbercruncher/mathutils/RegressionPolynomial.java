package numbercruncher.mathutils;

import numbercruncher.mathutils.IntPower;
import numbercruncher.matrix.LinearSystem;
import numbercruncher.matrix.ColumnVector;
import numbercruncher.matrix.MatrixException;

/**
 * A least-squares regression polynomial function.
 */
public class RegressionPolynomial implements Evaluatable
{
    /** number of data points */        private int     n;
    /** degree of the polynomial */     private int     degree;
    /** maximum no. of data points */   private int     maxPoints;
    /** true if coefficients valid */   private boolean coefsValid;
    /** warning message */              private String  warningMsg;

    /** data points */                  private DataPoint data[];

    /** coefficient matrix A */               private LinearSystem A;
    /** regression coefficients vector a */   private ColumnVector a;
    /** right-hand-side vector b */           private ColumnVector b;

    /**
     * Constructor.
     * @param degree the degree of the polynomial
     * @param maxPoints the maximum number of data points
     */
    public RegressionPolynomial(int degree, int maxPoints)
    {
        this.degree    = degree;
        this.maxPoints = maxPoints;
        this.data      = new DataPoint[maxPoints];
    }

    /**
     * Constructor.
     * @param degree the degree of the polynomial
     * @param data the array of data points
     */
    public RegressionPolynomial(int degree, DataPoint data[])
    {
        this.degree    = degree;
        this.maxPoints = maxPoints;
        this.data      = data;
        this.n         = data.length;
    }

    /**
     * Return the degree of the polynomial.
     * @return the count
     */
    public int getDegree() { return degree; }

    /**
     * Return the current number of data points.
     * @return the count
     */
    public int getDataPointCount() { return n; }

    /**
     * Return the data points.
     * @return the count
     */
    public DataPoint[] getDataPoints() { return data; }

    /**
     * Return the coefficients matrix.
     * @return the A matrix
     * @throws matrix.MatrixException if a matrix error occurred
     * @throws Exception if an overflow occurred
     */
    public LinearSystem getCoefficientsMatrix()
        throws Exception, MatrixException
    {
        validateCoefficients();
        return A;
    }

    /**
     * Return the regression coefficients.
     * @return the a vector
     * @throws matrix.MatrixException if a matrix error occurred
     * @throws Exception if an overflow occurred
     */
    public ColumnVector getRegressionCoefficients()
        throws Exception, MatrixException
    {
        validateCoefficients();
        return a;
    }

    /**
     * Return the right hand side.
     * @return the b vector
     * @throws matrix.MatrixException if a matrix error occurred
     * @throws Exception if an overflow occurred
     */
    public ColumnVector getRHS() throws Exception, MatrixException
    {
        validateCoefficients();
        return b;
    }

    /**
     * Return the warning message (if any).
     * @return the message or null
     */
    public String getWarningMessage() { return warningMsg; }

    /**
     * Add a new data point: Update the sums.
     * @param dataPoint the new data point
     */
    public void addDataPoint(DataPoint dataPoint)
    {
        if (n == maxPoints) return;

        data[n++] = dataPoint;
        coefsValid = false;
    }

    /**
     * Return the value of the regression polynomial function at x.
     * (Implementation of Evaluatable.)
     * @param x the value of x
     * @return the value of the function at x
     */
    public float at(float x)
    {
        if (n < degree + 1) return Float.NaN;

        try {
            validateCoefficients();

            float xPower = 1;
            float y      = 0;

            // Compute y = a[0] + a[1]*x + a[2]*x^2 + ... + a[n]*x^n
            for (int i = 0; i <= degree; ++i) {
                y += a.at(i)*xPower;
                xPower *= x;
            }

            return y;
        }
        catch(MatrixException ex) {
            return Float.NaN;
        }
        catch(Exception ex) {
            return Float.NaN;
        }
    }

    /**
     * Reset.
     */
    public void reset()
    {
        n    = 0;
        data = new DataPoint[maxPoints];
        coefsValid = false;
    }

    /**
     * Compute the coefficients.
     * @throws matrix.MatrixException if a matrix error occurred
     * @throws Exception if an overflow occurred
     */
    public void computeCoefficients()
        throws Exception, MatrixException
    {
        validateCoefficients();
    }

    /**
     * Validate the coefficients.
     * @throws matrix.MatrixException if a matrix error occurred
     * @throws Exception if an overflow occurred
     */
    private void validateCoefficients()
        throws Exception, MatrixException
    {
        if (coefsValid) return;

        A = new LinearSystem(degree + 1);
        b = new ColumnVector(degree + 1);

        // Compute the multipliers of a[0] for each equation.
        for (int r = 0; r <= degree; ++r) {
            float sum = sumXPower(r);
            int   j   = 0;

            if (Float.isInfinite(sum)) {
                throw new Exception("Overflow occurred.");
            }

            // Set the multipliers along the diagonal.
            for (int i = r; i >= 0; --i) A.set(i, j++, sum);

            // Set the right-hand-side value.
            b.set(r, sumXPowerY(r));
        }

        // Compute the multipliers of a[c] for the last equation.
        for (int c = 1; c <= degree; ++c) {
            float sum = sumXPower(degree + c);
            int   i   = degree;

            if (Float.isInfinite(sum)) {
                throw new Exception("Overflow occurred.");
            }

            // Set the multipliers along the diagonal.
            for (int j = c; j <= degree; ++j) A.set(i--, j, sum);
        }

        warningMsg = null;

        // First try solving with iterative improvement.  If that
        // fails, then try solving without iterative improvement.
        try {
            a = A.solve(b, true);
        }
        catch(MatrixException ex) {
            warningMsg = ex.getMessage();
            a = A.solve(b, false);
        }

        coefsValid = true;
    }

    /**
     * Compute the sum of the x coordinates each raised
     * to an integer power.
     * @return the sum
     */
    private float sumXPower(int power)
    {
        float sum = 0;

        for (int i = 0; i < n; ++i) {
            sum += (float) IntPower.raise(data[i].x, power);
        }

        return sum;
    }

    /**
     * Compute the sum of the x coordinates each raised to an integer
     * power and multiplied by the corresponding y coordinate.
     * @return the sum
     */
    private float sumXPowerY(int power)
    {
        float sum = 0;

        for (int i = 0; i < n; ++i) {
            sum += (float) data[i].y*IntPower.raise(data[i].x, power);
        }

        return sum;
    }
}