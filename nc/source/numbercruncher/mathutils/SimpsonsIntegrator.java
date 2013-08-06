package numbercruncher.mathutils;

/**
 * Function integrator that implements
 * Simpson's algorithm with parabolas.
 */
public class SimpsonsIntegrator implements Integrator
{
    /** the function to integrate */  private Evaluatable integrand;

    /**
     * Constructor.
     * @param integrand the function to integrate
     */
    public SimpsonsIntegrator(Evaluatable integrand)
    {
        this.integrand = integrand;
    }

    /**
     * Integrate the function from a to b using Simpson's algorithm,
     * and return an approximation to the area.
     * (Integrator implementation.)
     * @param a the lower limit
     * @param b the upper limit
     * @param intervals the number of equal-width intervals
     * @return an approximation to the area
     */
    public float integrate(float a, float b, int intervals)
    {
        if (b <= a) return 0;

        float h         = (b - a)/intervals/2;  // interval width
                                                //   (split in two)
        float totalArea = 0;

        // Compute the area using the current number of intervals.
        for (int i = 0; i < intervals; ++i) {
            float x1 = a + 2*i*h;
            totalArea += areaOf(x1, h);
        }

        return totalArea;
    }

    /**
     * Compute the area of the ith parabolic region.
     * @param x1 the left bound of the region
     * @param h the interval width
     * @return the area of the region
     */
    private float areaOf(float x1, float h)
    {
        float x2   = x1 + h;               // middle
        float x3   = x2 + h;               // right bound of the region
        float y1   = integrand.at(x1);     // value at left bound
        float y2   = integrand.at(x2);     // value at the middle
        float y3   = integrand.at(x3);     // value at right bound
        float area = h*(y1 + 4*y2+ y3)/3;  // area of the region

        return area;
    }
}