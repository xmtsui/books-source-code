package numbercruncher.mathutils;

/**
 * Implement Kahan's Summation Algorithm for the float type.
 */
public class KahanSummation
{
    /** the current running sum */      private float sum;
    /** the current correction */       private float correction;
    /** the current corrected added */  private float correctedAddend;

    /**
     * Constructor.
     */
    public KahanSummation() {}

    /**
     * Return the current corrected value of the running sum.
     * @return the running sum's value
     */
    public float value() { return sum + correction; }

    /**
     * Return the corrected value of the current addend.
     * @return the corrected addend value
     */
    public float correctedAddend() { return correctedAddend; }

    /**
     * Add the value of an addend to the running sum.
     * @param the addend value
     */
    public void add(float addend)
    {
        // Correct the addend value and add it to the running sum.
        correctedAddend = addend + correction;
        float tempSum   = sum + correctedAddend;

        // Compute the next correction and set the running sum.
        // The parentheses are necessary to compute the high-order
        // bits of the addend.
        correction = correctedAddend - (tempSum - sum);
        sum        = tempSum;
    }

    /**
     * Clear the running sum and the correction.
     */
    public void clear()
    {
        sum        = 0;
        correction = 0;
    }
}