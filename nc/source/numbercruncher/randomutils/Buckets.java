package numbercruncher.randomutils;

import numbercruncher.mathutils.AlignRight;

/**
 * Counters of random values that fall within each interval.
 */
public class Buckets
{
    private static final int MAX_BAR_SIZE = 50;

    private AlignRight ar = new AlignRight();

    /** number of intervals */      private int n;
    /** counters per interval */    private int counters[];

    /** minimum random value */     private float rMin;
    /** maximum random value */     private float rMax;
    /** from min to max */          private float width;

    /**
     * Constructor.
     * @param n the number of intervals
     */
    public Buckets(int n)
    {
        this.n = n;
        this.counters = new int[n];
        clear();
    }

    /**
     * Return the counter value for interval i.
     * @param i the value of i
     * @return the counter value
     */
    public int get(int i) { return counters[i]; }

    /**
     * Set the minimum and maximum random values.
     * @param rMin the minimum value
     * @param rMax the maximum value
     */
    public void setLimits(float rMin, float rMax)
    {
        this.rMin  = rMin;
        this.rMax  = rMax;
        this.width = (rMax - rMin)/n;
    }

    /**
     * Determine a random value's interval and count it.
     * @param r the random value
     */
    public void put(float r)
    {
        // Ignore the value if it's out of range.
        if ((r < rMin) || (r > rMax)) return;

        // Determine its interval and count it.
        int i = (int) ((r - rMin)/width);
        ++counters[i];
    }

    /**
     * Clear all the interval counters.
     */
    public void clear()
    {
        for (int i = 0; i < counters.length; ++i) counters[i] = 0;
    }

    /**
     * Print the counter values as a horizontal bar chart. Scale the
     * chart so that the longest bar is MAX_BAR_SIZE.
     */
    public void print()
    {
        // Get the longest bar's length.
        int maxCount = 0;
        for (int i = 0; i < n; ++i) {
            maxCount = Math.max(maxCount, counters[i]);
        }

        // Compute the scaling factor.
        float factor = ((float) MAX_BAR_SIZE)/maxCount;

        // Loop to print each bar.
        for (int i = 0; i < n; ++i) {
            int b = counters[i];

            // Interval number.
            ar.print(i, 2);
            ar.print(b, 7);
            System.out.print(": ");

            // Bar.
            int length = Math.round(factor*b);
            for (int j = 0; j < length; ++j) System.out.print("*");
            System.out.println();
        }
    }
}