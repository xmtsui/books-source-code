package numbercruncher.mathutils;

/**
 * Perform basic complex arithmetic.  The complex objects are
 * immutable, and complex operations create new complex objects.
 */
public class Complex
{
    /** the real part */        private float real;
    /** the imaginary part */   private float imaginary;

    /**
     * Constructor.
     * @param real the real part
     * @param imaginary the imaginary part
     */
    public Complex(float real, float imaginary)
    {
        this.real      = real;
        this.imaginary = imaginary;
    }

    /**
     * Return this complex number's real part.
     * @return the real part
     */
    public float real() { return real; }

    /**
     * Return this complex number's imaginary part.
     * @return the imaginary part
     */
    public float imaginary() { return imaginary; }

    /**
     * Compute this complex number's modulus
     */
    public float modulus()
    {
        return (float) Math.sqrt(real*real + imaginary*imaginary);
    }

    /**
     * Return whether or not this complex number
     * is equal to another one.
     * @param z the other complex number
     * @return true if equal, false if not
     */
    public boolean equal(Complex z)
    {
        return (real == z.real()) && (imaginary == z.imaginary());
    }

    /**
     * Add another complex number to this one.
     * @param a the other complex number
     * @return a new complex number that is the sum
     */
    public Complex add(Complex z)
    {
        return new Complex(real + z.real(),
                           imaginary + z.imaginary());
    }

    /**
     * Subtract another complex number from this one.
     * @param a the other complex number
     * @return a new complex number that is the difference
     */
    public Complex subtract(Complex z)
    {
        return new Complex(real - z.real(),
                           imaginary - z.imaginary());
    }

    /**
     * Multiply this complex number by another one.
     * @param a the other complex number
     * @return a new complex number that is the product
     */
    public Complex multiply(Complex z)
    {
        return new Complex(real*z.real() - imaginary*z.imaginary(),
                           real*z.imaginary() + imaginary*z.real());
    }

    /**
     * Divide this complex number by another one.
     * @param a the other complex number
     * @return a new complex number that is the quotient
     */
    public Complex divide(Complex z)
    {
        float denom = z.real()*z.real() + z.imaginary()*z.imaginary();
        float qr    = (real*z.real() + imaginary*z.imaginary())/denom;
        float qi    = (imaginary*z.real() - real*z.imaginary())/denom;

        return new Complex(qr, qi);
    }

    /**
     * Return the string representation of this complex number.
     * @return the string representation
     */
    public String toString()
    {
        String operator = (imaginary >= 0) ? "+" : "-";
        return real + operator + Math.abs(imaginary) + "i";
    }
}